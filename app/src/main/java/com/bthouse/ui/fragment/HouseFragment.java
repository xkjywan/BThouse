package com.bthouse.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bthouse.R;
import com.bthouse.adapter.HouseAdapter;
import com.bthouse.listener.OnItemClickListener;
import com.bthouse.mvp.module.House;
import com.bthouse.mvp.presenter.BasePresenter;
import com.bthouse.ui.activity.HouseDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 房屋界面
 */
public class HouseFragment extends BaseFragment {

    @Bind(R.id.house)
    RecyclerView house;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public HouseFragment(Context context) {
        this.context = context;
    }

    @Override
    protected View initContentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpage, container, false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }

    @Override
    protected void loadData() {
        ArrayList<House> houseList = new ArrayList<House>();
        for (int i=0;i<5;i++){
            House house = new House();
            houseList.add(house);
        }
        HouseAdapter houseAdapter = new HouseAdapter(context,houseList);
        houseAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HouseDetailActivity.startActivity();
            }
        });
        house.setLayoutManager(new LinearLayoutManager(context));
        house.setAdapter(houseAdapter);
    }
}
