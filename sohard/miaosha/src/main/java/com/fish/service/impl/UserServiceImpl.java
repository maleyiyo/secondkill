package com.fish.service.impl;

import com.fish.dao.UserDOMapper;
import com.fish.dao.UserPasswordDOMapper;
import com.fish.dataobject.UserDO;
import com.fish.dataobject.UserPasswordDO;
import com.fish.error.BusinessException;
import com.fish.error.EmBusinessError;
import com.fish.service.UserService;
import com.fish.service.model.UserModel;
import com.fish.validator.ValidationResult;
import com.fish.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.EmbeddedFrame;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDOMapper userDOMapper;
//
//    @Autowired
//    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    @Transactional
    public UserModel validteLogin(String telphone, String encrptPasssword) throws BusinessException {
        //通过用户的手机获取用户信息
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);

        //比对用户信息内加密密码是否和传输进来的密码相匹配
        if (!StringUtils.equals(encrptPasssword, userModel.getEncptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException{
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAM_VALIDATION_ERROR);
        }
//        if (StringUtils.isEmpty(userModel.getName()) ||
//                userModel.getGender() == null ||
//                userModel.getAge() == null ||
//                StringUtils.isEmpty(userModel.getTelphone())) {
//            throw new BusinessException(EmBusinessError.PARAM_VALIDATION_ERROR);
//        }

        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAM_VALIDATION_ERROR, result.getErrMsg());
        }
        UserDO userDO = convertFromUserModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException e) {
            throw new BusinessException(EmBusinessError.PARAM_VALIDATION_ERROR, "手机号已重复注册");
        }

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromUserModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

        return;
    }

    //实现model->dataobject方法
    public UserDO convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    //实现model->passwordDO方法
    public UserPasswordDO convertPasswordFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    @Override
    public UserModel getUserById(Integer id) {
        //调用userdomapper获取到对应的用户dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

        if (userDO == null) {
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setEncptPassword(userPasswordDOMapper.selectByUserId(id).getEncrptPassword());
        return userModel;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }

//    private UserVO convertFromUserModel(UserModel userModel) {
//        if (userModel == null) {
//            return null;
//        }
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(userModel, userVO);
//        return userVO;
//    }
}
