package com.foo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foo.common.utils.FileUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class UserVO {
    @Id
    private Integer id;

    private String name;

    @Column(name = "front_image")
    private String frontImage;

    /**
     * 用户会话token
     */
    private String userUniqueToken;

    public String getFrontImage() {
        return frontImage;
    }

    public String getUserUniqueToken() {
        return userUniqueToken;
    }

    public void setUserUniqueToken(String userUniqueToken) {
        this.userUniqueToken = userUniqueToken;
    }



    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}