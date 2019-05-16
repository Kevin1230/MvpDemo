package com.example.along.mvpdemo.base;

public class BaseEntity<T> {

  public static final int CODE = 200;
  public static final int LOGOUT_CODE = 405;
  public static final int PAY_2_VERIFY_PHONE = 409;
  public static final int PAY_2_VERIFY_EMIAL = 410;
  public static final int PAY_2_VERIFY_PIN = 411;//（pin密码未设置）
  public static final int PAY_2_VERIFY_FINGER = 412;//（指纹密码未设置）
  public static final int PAY_REQEST_PHONT_OR_EMAIL = 402;//（邮箱未认证，或者手机未认证）
  public static final int TOO_MANY_PSD = 414;//（密码输入次数过多）

  public int code;
  public String msg;
  public T data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "BaseEntity{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", data=" + data +
            '}';
  }
}
