package com.jiang.demo.utils;

import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/10
 */


public class PageDTO<T> implements Serializable {

    @ApiModelProperty(value = "总记录数")
    private Long totalElements;

    @ApiModelProperty(value = "总页数")
    private Integer totalPages;

    @ApiModelProperty(value = "是否是第一页")
    private Boolean first;

    @ApiModelProperty(value = "是否是最后一页")
    private Boolean last;

    @ApiModelProperty(value = "当前页上的元素数")
    private Integer numberOfElements;

    @ApiModelProperty(value = "返回数据是否有内容")
    private Boolean hasContent;

    @ApiModelProperty(value = "第几页")
    private Integer number;

    @ApiModelProperty(value = "每页大小")
    private Integer size;

    @ApiModelProperty(value = "列表")
    private List<T> content=new ArrayList<T>();

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Boolean getHasContent() {
        return hasContent;
    }

    public void setHasContent(Boolean hasContent) {
        this.hasContent = hasContent;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
