package com.foo.controller;

import com.foo.common.config.PreReadUploadConfig;
import com.foo.common.utils.*;
import com.foo.pojo.User;
import com.foo.pojo.bo.CenterUserBO;
import com.foo.pojo.bo.UserBO;
import com.foo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户相关", tags = {"用户相关接口"})
@RestController
@RequestMapping("user")
public class UserController {
    private final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PreReadUploadConfig uploadConfig;
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username){
        //判断用户名为空
        if(StringUtils.isBlank(username)){
            return JSONResult.errorMsg("用户名为空");
        }
        //查找用户名是否存在
        boolean isExist =  userService.queryUsernameIsExist(username);
        if(isExist){
            return JSONResult.errorMsg("用户名已存在");
        }
        return JSONResult.ok();
    }
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBO userBO, HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassWord = userBO.getConfirmPassword();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)||
        StringUtils.isBlank(confirmPassWord)){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        boolean isExist =  userService.queryUsernameIsExist(username);
        if(isExist){
            return JSONResult.errorMsg("用户名已存在");
        }
        if(password.length()<3){
            return JSONResult.errorMsg("密码长度不得小于3");
        }
        if(!password.equals(confirmPassWord)){
            return JSONResult.errorMsg("两次密码不一致");
        }
        CookieUtils.setCookie(httpServletRequest,httpServletResponse
                ,"user", JsonUtils.objectToJson(userBO));
        userService.createUser(userBO);
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO, HttpServletRequest httpServletRequest
    , HttpServletResponse httpServletResponse){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        User result = null;
        try {
            result = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result == null){
            return JSONResult.errorMsg("用户名或密码不正确");
        }
        result.setCreateTime(null);
        result.setPassword(null);
        result.setFrontImage(FileUtils.getUrl("127.0.0.1","8088",result.getFrontImage()));
        CookieUtils.setCookie(httpServletRequest,httpServletResponse
        ,"user", JsonUtils.objectToJson(result),true);
        return JSONResult.ok(result);
    }
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(@RequestParam String userId, HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse){
        //清楚用户cookie
        CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,"user");
        //TODO 用户退出登录需要清除购物车
        //TODO 分布式会话需要清除用户数据
        return JSONResult.ok();
    }
    @ApiOperation(value = "上传用户头像", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/upLoadFrontImage")
    public JSONResult upLoadFrontImage(@RequestParam(value = "userImg", required = false) MultipartFile file
            , @RequestParam(value = "userId", required = false) int userId){
        User user =  userService.updateFrontImage(file,userId);
        return JSONResult.ok(user);
    }
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/updateUserInfo")
    public JSONResult updateUserInfo(@RequestParam int userId,
                                     @RequestBody @Valid CenterUserBO centerUserBO,
                                     BindingResult result){
        // 判断是否有错误
        if(result.hasErrors()){
           Map<String,String> errorMap = getErrors(result);
           return JSONResult.errorMap(errorMap);
        }
        return JSONResult.ok();
    }

    private Map<String,String> getErrors(BindingResult result){
        Map<String,String> map = new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList){
            // 发生验证错误的属性
            String errorField = error.getField();
            // 错误信息
            String errorMsg = error.getDefaultMessage();
            map.put(errorField,errorMsg);
        }
        return map;
    }
}
