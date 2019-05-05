package com.jiang.demo.service.impl;

import com.jiang.demo.dto.sysRole.SysRoleDTO;
import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.dto.userInfo.UserInfoForm;
import com.jiang.demo.entity.SysRole;
import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.SysRoleRepository;
import com.jiang.demo.repository.UserInfoRepository;
import com.jiang.demo.service.UserInfoService;
import com.jiang.demo.utils.PageDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 江云飞
 * Date:   2019/4/11
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    // 通过set方法注入
    private UserInfoRepository userInfoRepository;
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    private SysRoleRepository sysRoleRepository;
    @Autowired
    public void setSysRoleRepository(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }


    @Override
    public UserInfo login(String username,String password) {

        return userInfoRepository.findByUsernameAndPassword(username, password);

    }


    public UserInfoDTO findByUsername(String username){
        UserInfo byUsername = userInfoRepository.findByUsername(username);

        UserInfoDTO userInfoDTO=UserInfoDTO.convert(byUsername);
        System.out.println(byUsername);
        return userInfoDTO;
    }

    @Override
    public UserInfoDTO insertUser(UserInfoForm userInfoForm) {
        /*将前者赋值给后者*/
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoForm, userInfo);

        if(userInfoRepository.findByUsername(userInfoForm.getUsername())!=null){
            throw new MyException(-2,"用户名已经存在");
        }

        List<Integer> roleListId = userInfoForm.getRoleListId();
        List<SysRole> sysRoles = new ArrayList<>();
        for (Integer i:roleListId) {
            SysRole sysRole = sysRoleRepository.findById(i).orElse(null);
            sysRoles.add(sysRole);
        }
        userInfo.setRoleList(sysRoles);
        //保存  返回值封装
        UserInfo save = userInfoRepository.save(userInfo);

        List<SysRoleDTO> sysRoleDTOS = new ArrayList<>();
        List<SysRole> roleList = save.getRoleList();
        for (SysRole s:roleList) {
            SysRoleDTO convert = SysRoleDTO.convert(s);
            sysRoleDTOS.add(convert);
        }

        UserInfoDTO convert = UserInfoDTO.convert(save);
        convert.setRoleDTOS(sysRoleDTOS);
        return convert;
    }

    @Override
    public void deleteUser(Integer id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public UserInfoDTO updateUser(UserInfoForm userInfoForm, Integer id) {
        //根据id查出用户
        UserInfo userInfo = userInfoRepository.findById(id).orElse(null);
        if(userInfo==null){
            throw new MyException(-1,"用户不存在");
        }
        //判断数据的名字和穿过来的名字是否相同  如果相同就修改其他值
        if(userInfoForm.getUsername().equals(userInfo.getUsername())){
            userInfo.setName(userInfoForm.getName());
            userInfo.setPassword(userInfoForm.getPassword());
            //如果用户名不同  再去检查是否重名
        }else if(userInfoRepository.findByUsername(userInfoForm.getUsername())!=null){
            throw new MyException(-2,"用户名已经存在");
        }else{
            userInfo.setUsername(userInfoForm.getUsername());
        }

        //修改角色
        List<Integer> roleListId = userInfoForm.getRoleListId();
        List<SysRole> sysRoles = new ArrayList<>();
        for (Integer i:roleListId) {
            SysRole sysRole = sysRoleRepository.findById(i).orElse(null);
            sysRoles.add(sysRole);
        }
        userInfo.setRoleList(sysRoles);

        //更新  返回值封装
        UserInfo save = userInfoRepository.save(userInfo);

        List<SysRoleDTO> sysRoleDTOS = new ArrayList<>();
        List<SysRole> roleList = save.getRoleList();
        for (SysRole s:roleList) {
            SysRoleDTO convert = SysRoleDTO.convert(s);
            sysRoleDTOS.add(convert);
        }

        UserInfoDTO convert = UserInfoDTO.convert(save);
        convert.setRoleDTOS(sysRoleDTOS);
        return convert;
    }

    @Override
    public UserInfoDTO selectByName(String username) {

        return  UserInfoDTO.convert(userInfoRepository.findByUsername(username));
    }
    @Override
    public PageDTO<UserInfoDTO> selectUser(Integer pageNum, Integer pageSize) {
        System.out.println("进controller  IMPL");
        Sort sort = new Sort(Sort.Direction.DESC, "uid");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<UserInfo> userInfoRepositoryAll = userInfoRepository.findAll(pageable);

        //封装分页
        PageDTO<UserInfoDTO> pageDTO =new PageDTO<>();
        //赋值给封装的page
        BeanUtils.copyProperties(userInfoRepositoryAll, pageDTO);

        List<UserInfoDTO> userInfoDTOS=new ArrayList<>();
        List<UserInfo> content = userInfoRepositoryAll.getContent();
        for (UserInfo g:content) {
            userInfoDTOS.add(UserInfoDTO.convert(g));
        }
        pageDTO.setContent(userInfoDTOS);

        return pageDTO;

    }


}
