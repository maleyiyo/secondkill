package com.fish.error;

public enum EmBusinessError implements CommonError{
    //通用错误类型10001
    PARAM_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOW_ERROR(10002, "未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码不正确"),
    USER_NOT_LOGIN(20003, "用户还未登陆"),

    //30000开头为交易信息错误定义
    STOCK_NOT_ENOUGH(30001, "库存不足"),
    ;

    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String msg) {
        this.errCode = errCode;
        this.errMsg = msg;
    }

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String msg) {
        this.errMsg = msg;
        return this;
    }
}
