package com.bthouse.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;


public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    private UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler INSTANCE = new CrashHandler();
    private Context mContext;
    private Map<String, String> infos = new HashMap<String, String>();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(ex);
       /* if (ApplicationInfoUtils.isDebugMode()) {
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, ex);
            }
        }*/
        try {
            Thread.sleep(3000);
            Activity curActivity = getGlobleActivity();
            if (curActivity != null)
                curActivity.finish();
            //异常之后杀死APP后重启
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Activity getGlobleActivity() throws ClassNotFoundException,
            IllegalArgumentException, SecurityException,
            IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, NoSuchFieldException {
        Class activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = activityThreadClass.getMethod(
                "currentActivityThread").invoke(null);
        Field activitiesField = activityThreadClass
                .getDeclaredField("mActivities");
        activitiesField.setAccessible(true);
        Map activities = (Map) activitiesField.get(activityThread);
        for (Object activityRecord : activities.values()) {
            Class activityRecordClass = activityRecord.getClass();
            Field pausedField = activityRecordClass.getDeclaredField("paused");
            pausedField.setAccessible(true);
            if (!pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass
                        .getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField
                        .get(activityRecord);
                return activity;
            }
        }
        return null;
    }

    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        saveCrashInfo2Service(ex);
        return true;
    }

    private String saveCrashInfo2Service(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        //生成日志上传
        /*ErrorLogInfo errorLogInfo = new ErrorLogInfo();
        errorLogInfo.setDeviceNumber(DeviceInfo.getUniquePsuedoID());//设备标识符
        errorLogInfo.setDeviceType(Build.BRAND + "-" + Build.MODEL);
        errorLogInfo.setErrContent(ex.toString());
        errorLogInfo.setLoginPhone(SysApplication.getInstance().sp.getString(Constant.SPConstant.PHONE, ""));
        errorLogInfo.setNetType(NetWorkUtils.getNetWorkType());
        errorLogInfo.setSystemVersion(DeviceInfo.getAndroidVersion());
        errorLogInfo.setVersionNumbern(Tool.getAppVersion());
        errorLogInfo.setUpTime(Tool.getNowTime());
        errorLogInfo.setChannel("Android");

        ArrayList<ErrorLogInfo> errorLogInfos = new ArrayList<ErrorLogInfo>();
        errorLogInfos.add(errorLogInfo);

        MessageDao.instance().SendErorLogToSetver(errorLogInfos, mContext.getApplicationContext(), new GenericsCallback<User.Head>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(User.Head response, int id) {
            }
        });*/

        return null;
    }


}