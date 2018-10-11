package com.bthouse.config;

import android.os.Environment;

import java.util.HashMap;

/**
 * @description: 网络地址常量
 */

public class AppConfig {
    /**
     * 接口根地址
     */

    public static String MY_CAMERA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera/";

    public static final String BASE_SERVER_URL = "http://wsmallapp.islistings.com/";//BT测试地址
    //public static final String BASE_SERVER_URL = "http://117.48.214.30:18080/evaluate/";//京东云
//    public static final String BASE_SERVER_URL = "http://192.168.2.103:8080/evaluate/";
    public static final String UPDATE_VERSION_URL = "http://117.48.214.30:18080/evaluate/app/updateVersion";

    public static final String MEDAL_DETAIL_WEB_URL = BASE_SERVER_URL + "appapi/wapPage/medal/detail?awardMedalId=";

    public static final String SELF_PROVE_WEB_URL = BASE_SERVER_URL + "appapi/wapPage/selfProve/detail?studentSelfId=";

    public static HashMap<String, String> CODE_MAP = new HashMap<String, String>();

    static {


        CODE_MAP.put("001", "无此用户信息");
        CODE_MAP.put("002", "用户名错误");
        CODE_MAP.put("003", "密码错误");
        CODE_MAP.put("004", "重复密码错误");
        CODE_MAP.put("005", "appid,appkey验证失败");
        CODE_MAP.put("007", "无此用户信息");
        CODE_MAP.put("008", "token失效，需要重新登录");
        CODE_MAP.put("009", "缺少必要参数");
        CODE_MAP.put("300", "手机号码格式有误");
        CODE_MAP.put("301", "短信发送失败");
        CODE_MAP.put("302", "验证码错误");

        CODE_MAP.put("303", "密码格式有误,正确的密码为6-12位包含字母和小数");
        CODE_MAP.put("304", "新旧密码重复");
        CODE_MAP.put("305", "该手机号码已经绑定过该用户");

        CODE_MAP.put("306", "验证码已失效，请重新获取！");
        CODE_MAP.put("307", "该手机号当日操作次数达到上线！");
        CODE_MAP.put("400", "所选勋章不存在或已失效!");

        CODE_MAP.put("401", "该教师未拥有撤销该勋章颁发记录的权限!");
        CODE_MAP.put("402", "该勋章颁发记录不存在或已失效!");
        CODE_MAP.put("403", "勋章颁发已超过五分钟,无法撤回!");
        CODE_MAP.put("404", "该自证材料不存在或已失效!");
        CODE_MAP.put("405", "该自证材料所属的考察要点下没有勋章,无法进行鼓励操作!");



    }

    /**
     * app_id是从微信官网申请到的合法APPid
     */
    public static final String APP_ID_WX = "wxb7d455178d4c8057";

}
