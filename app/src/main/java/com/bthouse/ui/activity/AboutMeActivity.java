package com.bthouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bthouse.App;
import com.bthouse.R;
import com.bthouse.mvp.presenter.BasePresenter;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 功能说明: 闪屏
 */
public class AboutMeActivity extends BaseActivity {
    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @OnClick({R.id.ll_secret, R.id.ll_about_me,R.id.ll_service,R.id.ll_contact_me,R.id.ll_feedback})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.ll_secret:

                break;

            case R.id.ll_about_me:

                break;
            case R.id.ll_contact_me:

                break;
            case R.id.ll_feedback:

                break;
            case R.id.ll_service:

                break;


        }
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
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
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about_me;
    }
    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), AboutMeActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

}
