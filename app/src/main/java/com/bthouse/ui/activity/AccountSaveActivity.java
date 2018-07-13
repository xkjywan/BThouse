package com.bthouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.bthouse.App;
import com.bthouse.R;
import com.bthouse.mvp.presenter.BasePresenter;
import com.bthouse.view.CustomTextView;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.Bind;


/**
 * 功能说明:
 */
public class AccountSaveActivity extends BaseActivity {
    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_account_save;
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
    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), AccountSaveActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

}
