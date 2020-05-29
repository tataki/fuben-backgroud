package com.foo.controller;

import com.foo.common.config.PreReadUploadConfig;
import com.foo.common.utils.*;
import com.foo.pojo.User;
import com.foo.pojo.bo.UserBO;
import com.foo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {
    private final Logger logger = Logger.getLogger(BaseController.class);
    public static final Integer PAGE_SIZE = 10;
    public static final String REDIS_USER_TOKEN = "redis_user_token";
}
