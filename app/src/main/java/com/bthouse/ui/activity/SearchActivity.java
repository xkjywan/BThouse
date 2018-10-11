package com.bthouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bthouse.App;
import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.adapter.CityAdapter;
import com.bthouse.adapter.SearchResultAdapter;
import com.bthouse.listener.OnItemClickListener;
import com.bthouse.mvp.module.City;
import com.bthouse.mvp.module.SearchCollectBean;
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.mvp.presenter.LoginPresenter;
import com.bthouse.mvp.view.IloginView;
import com.bthouse.util.NetUtil;
import com.bthouse.util.PreferenceUtils;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.bthouse.view.LoadingDialog;
import com.jude.swipbackhelper.SwipeBackHelper;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.OnClick;
import static com.bthouse.App.getContext;

public class SearchActivity extends BaseActivity<LoginPresenter> implements IloginView {
    @Bind(R.id.result)
    RecyclerView result;

    @Bind(R.id.bt_back)
    Button bt_back;
    public LoadingDialog mLoadingDialog;

    //记录默认的帐号
    private static String USER_NAME = "user_name_key";

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);

        ArrayList<City> citys = new ArrayList<City>();
        for (int i=0;i<5;i++){
            City city = new City();
            citys.add(city);
        }

        CityAdapter cityAdapter = new CityAdapter(getContext(), citys);
        cityAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HouseSearchResultActivity.startActivity();
            }
        });
        result.setLayoutManager(new LinearLayoutManager(this));
        result.setAdapter(cityAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(SearchActivity.this, "");
        String userName = PreferenceUtils.getPrefString(this, USER_NAME, "");
    }

    @OnClick({R.id.result,R.id.bt_back})
    public void Onclick(View v) {
        switch (v.getId()) {
//            case R.id.result:
//                HouseSearchResultActivity.startActivity();
//                break;
            case R.id.bt_back:
                finish();
        }
    }

    @Override
    public void onFinish() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    @Override
    public void onError() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
        if(!NetUtil.isConnected(SearchActivity.this)){
            ToastUtil.show(SearchActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(SearchActivity.this, "登陆失败");
    }

    @Override
    public void onLoginSuc(UserResponse response) {

    }

    public static void startActivity() {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }


}
