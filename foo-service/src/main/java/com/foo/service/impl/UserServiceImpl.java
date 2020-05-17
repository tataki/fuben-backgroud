package com.foo.service.impl;

import com.foo.common.config.PreReadUploadConfig;
import com.foo.common.utils.FileUtils;
import com.foo.pojo.bo.CenterUserBO;
import com.foo.service.UserService;
import com.foo.common.utils.MD5Utils;
import com.foo.mapper.UserMapper;
import com.foo.pojo.Project;
import com.foo.pojo.User;
import com.foo.pojo.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PreReadUploadConfig uploadConfig;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(User.class);
        Example.Criteria userCriteria =  userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        User result = userMapper.selectOneByExample(userExample);
        return result == null ? false:true;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String username, String password) {
        Example userExample = new Example(User.class);
        Example.Criteria userCriteria =  userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        userCriteria.andEqualTo("password",password);
        User result =  userMapper.selectOneByExample(userExample);
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User createUser(UserBO userBO) {
        User user = new User();
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e){
            e.printStackTrace();
        }
        user.setName(userBO.getUsername());
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public Project getUserInfo(int id) {
        return null;
    }

    @Override
    public void saveUser() {

    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUser(int userId,CenterUserBO centerUserBO) {
        User updateUser = new User();
        BeanUtils.copyProperties(centerUserBO,updateUser);
        updateUser.setId(userId);
        userMapper.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public List<Project> getUserList() {
        return null;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateFrontImage(MultipartFile file, int userId) {
        String fileName = null;
        try{
            fileName = FileUtils.uploadFile(uploadConfig.getUploadPath(),file);
        } catch (Exception e){
            e.printStackTrace();
        }
        User user = new User();
        user.setId(userId);
        user.setFrontImage(fileName);
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id",userId);
        userMapper.updateByExampleSelective(user,userExample);
        return user;
    }
}
