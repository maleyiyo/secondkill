package com.fish.service;

import com.fish.controller.viewobject.UserVO;
import com.fish.error.BusinessException;
import com.fish.service.model.UserModel;

public interface UserService {

    //通过用户id获取用户对象的方法
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     * @param telphone用户注册手机
     * @param encrptPasssword用户加密后的密码
     * @throws BusinessException
     */
    UserModel validteLogin(String telphone, String encrptPasssword) throws BusinessException;
}
