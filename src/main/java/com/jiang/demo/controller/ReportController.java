package com.jiang.demo.controller;

import com.jiang.demo.dto.Storeroom.ReportDTO;
import com.jiang.demo.dto.reportForm.ReportForm;
import com.jiang.demo.permission.Login;
import com.jiang.demo.service.StoreroomService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Author: 江云飞
 * Date:   2019/6/6
 */

@RestController
@Api(description = "查看报表" )
@RequestMapping("/report")
public class ReportController {

    private StoreroomService storeroomService;
    @Autowired
    public void setStoreroomService(StoreroomService storeroomService) {
        this.storeroomService = storeroomService;
    }

    //查看报表
    @ApiOperation(value = "查看报表")
    @PostMapping("/selectReport")
    @Login
    @SuppressWarnings("unchecked")
    public Result<PageDTO<ReportDTO>> selectReport(@RequestBody ReportForm reportForm){
        PageDTO<ReportDTO> reportDTOPageDTO = storeroomService.selectReport(reportForm);
        return ResultUtil.success(reportDTOPageDTO);
    }
}
