package com.bthouse.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.bthouse.MainActivity;
import com.bthouse.R;


/**
 * 功能说明: 闪屏
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        handleWelcome();
    }

    private void handleWelcome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                MainActivity.startActivity(SplashActivity.this);
                //判断是否有用户登录，如果有，直接到主界面，否则去登录界面;
//                if(App.getInstance().getUserBean()!=null &&App.getInstance().getUserBean().getName()!=null&& App.getInstance().getUserBean().getUserId()!=null){
              /*  if(App.getInstance().getaCache().getAsString("IsLogin").equals("true")){
                //打开主页并关闭欢迎页面
                    MainActivity.startActivity(SplashActivity.this);

                }else{*/

//                    LoginActivity.startActivity();
//                }

                finish();
            }
        }, 2000);
    }
}
