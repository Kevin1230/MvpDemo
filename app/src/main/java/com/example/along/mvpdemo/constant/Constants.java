package com.example.along.mvpdemo.constant;

/**
 * 说明: 项目常量量类 <br/>
 */
public class Constants {

  /** EventBus相关常量 */
  public static class EventBusConstants {

    public static final int VALUE_FOR_KEY = 200;
    public static final int SET_VALUE = 201;
    public static final int REMOVE_VALUE_FOR_KEY = 202;
    public static final int CARD_SCANNER_FRONT = 203;
    public static final int CARD_SCANNER_BACK = 204;
    public static final int CARD_SCANNER_BANK = 205;
    public static final int FACE_ID_ENTIFY = 206;
    public static final int ADDRES_BOOK = 207;
    public static final int DEVICE_FINGER_INIT = 208;
    public static final int DEVICE_FINGER = 209;
    public static final int EXIT_APP = 210;
    public static final int PAY_NOW = 211;
    public static final int NO_LOGIN = 212;
  }

  /** SDK相关常量 */
  public static class SdkConstants {

    public static final String APP_ID = "5d12462babe147aba8e5d14f4e526a4b";
    public static final String APP_SECRET = "08b4c134ceab46beb766c9035878782e";

    public static final String OUTPUTTYPE = "outputtype";
    public static final String COMPLEXITY = "comlexity";
    public static final String NOTICE = "notice";
    public static final String DETECTLIST = "detectList";
    public static final String DEFAULTDETECTLIST = "BLINK MOUTH NOD YAW";
  }

  /** Bundle传值相关常量 */
  public static class BundleConstants {

    public static final String H5_PATH = "h5_path";
  }

  /** SP传值相关常量 */
  public static class SPConstants {



    public static final String H5_INFO_KEY = "h5_info_key";

    public static final String GESTURE_PASSWORD = "gesturePassword";
    public static final String GESTURE_TIME = "gestureTime";
    public static final String USERID = "userId";
    public static final String TOKEN = "token";
    public static final String TRY_TIMES = "try_times";

  }

  /** RequestCode传值相关常量 */
  public static class RequestCodeConstants {

    public static final int EXAMPLE_REQUEST_CODE_CARD_SCANNER_ID = 300;
    public static final int EXAMPLE_REQUEST_CODE_CARD_SCANNER_BANK = 301;
    public static final int EXAMPLE_REQUEST_CODE_FACE_ID_ENTIFY = 302;
    public static final int EXAMPLE_REQUEST_CODE_CONTACT_TWO = 303;
    public static final int EXAMPLE_REQUEST_CODE_CONTACT_ONE = 304;
  }

  /** 极光推送相关常量 */
  public static class JPushConstants {

    public static final String NOTIFICATION_RECEIVED = "cn.jpush.android.intent.NOTIFICATION_RECEIVED";
  }

  /** 手势密码页面状态相关常量 */
  public static class GesturesConstants {

    public static final String SHOW_PASSWORD_STATUS = "show_password_status";
    public static final String SHOW_PASSWORD_FROM = "show_password_from";

    public static final int SHOW_PASSWORD = 400;
    public static final int SHOW_PASSWORD_MANAGER = 401;
    public static final int FROM_HOME = 402;
    public static final int FROM_REGISTER = 403;
    public static final int FROM_FORGET = 404;
  }
    //访问接口token的key
    public static final String TOKEN_NAME = "Authorization";
    public static final String ACCESS_TOKEN = "access_token";
}
