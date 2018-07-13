package com.bthouse.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bthouse.App;
import com.bthouse.R;
import com.bthouse.mvp.module.House;
import com.bthouse.mvp.presenter.BasePresenter;
import com.bthouse.ui.fragment.ContactFragment;
import com.jude.swipbackhelper.SwipeBackHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import flyn.Eyes;

public class HouseDetailActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    BGABanner mContentBanner;

    @Bind(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @Bind(R.id.vp_house)
    ViewPager mViewpager;

    private ArrayList<String> mDataTypeList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initBanner() {
        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(HouseDetailActivity.this, R.mipmap.banner1, ImageView.ScaleType.FIT_XY));
        views.add(BGABannerUtil.getItemImageView(HouseDetailActivity.this,  R.mipmap.banner2,ImageView.ScaleType.FIT_XY));
        views.add(BGABannerUtil.getItemImageView(HouseDetailActivity.this,  R.mipmap.banner3,ImageView.ScaleType.FIT_XY));
        views.add(BGABannerUtil.getItemImageView(HouseDetailActivity.this,  R.mipmap.banner4,ImageView.ScaleType.FIT_XY));
        mContentBanner.setData(views);
    }


    /**
     * 初始化滑块UI
     */
    private void initMagicIndicator() {
        mDataTypeList.add("合住单间");
        mDataTypeList.add("独立单间");
        mDataTypeList.add("独享空间");
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(HouseDetailActivity.this);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataTypeList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.pager_title_layout, null);
                final TextView titleText = (TextView) customLayout.findViewById(R.id.title_text);
                titleText.setText(mDataTypeList.get(index));
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setBackgroundColor(getResources().getColor(R.color.login_text_color));
                        titleText.setTextColor(getResources().getColor(R.color.white));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setBackgroundColor(getResources().getColor(R.color.white));
                        titleText.setTextColor(getResources().getColor(R.color.text_color1));
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewpager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResources().getColor(R.color.login_text_color));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewpager);
    }
    @Override
    public void initView() {
        Eyes.translucentStatusBar(HouseDetailActivity.this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);
        initBanner();
        initMagicIndicator();
    }
    @OnClick({R.id.iv_back, R.id.iv_share,R.id.iv_like,R.id.btn_contect})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_like:
                break;
            case R.id.btn_contect:
                ContactFragment contactFragment = new ContactFragment().newInstance(this);
                contactFragment.show(getSupportFragmentManager(),"qrcode");
                break;
        }
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_house_detail;
    }

    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), HouseDetailActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

}
