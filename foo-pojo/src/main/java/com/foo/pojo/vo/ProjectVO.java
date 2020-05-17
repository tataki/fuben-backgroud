package com.foo.pojo.vo;

import java.util.List;

public class ProjectVO {
    private Integer id;
    private String title;
    private String des;
    // 参与用户
    private List<SubUserVO> SubUserList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<SubUserVO> getSubUserList() {
        return SubUserList;
    }

    public void setSubUserList(List<SubUserVO> subUserList) {
        SubUserList = subUserList;
    }
}
