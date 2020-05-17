package com.foo.service;

import com.foo.pojo.Project;
import com.foo.pojo.User;
import com.foo.pojo.bo.CenterUserBO;
import com.foo.pojo.bo.UserBO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param user
     * @return
     */
    public User createUser(UserBO user);

    /**
     * 检索用户名及密码 用于登录
     * @param username
     * @param password
     * @return
     */
    public User queryUserForLogin(String username,String password);
    public Project getUserInfo(int id);
    public void saveUser();
    public void updateUser(int userId, CenterUserBO centerUserBO);
    public void deleteUser(int id);
    public List<Project> getUserList();
    public User updateFrontImage(MultipartFile file, int userId);

}
