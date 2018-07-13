package com.bthouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bthouse.App;
import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.mvp.presenter.BasePresenter;
import com.bthouse.view.CustomTextView;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 功能说明: 闪屏
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void initView() {
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);
        customTextView.setOnTextViewClickListener(new CustomTextView.OnTextViewClickListener(){
            @Override
            public void OnLeftImgClick() {
                super.OnLeftImgClick();
                finish();
            }
        });
    }
    @OnClick({R.id.ll_account, R.id.ll_language,R.id.ll_clear_cache,R.id.tv_logout})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.ll_account:
                AccountSaveActivity.startActivity();
                break;

            case R.id.ll_language:

                break;
            case R.id.ll_clear_cache:
                HouseDetailActivity.startActivity();
                break;
            case R.id.tv_logout:
                LoginActivity.startActivity();
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;


        }
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), SettingActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

}
