package com.bthouse.adapter;

import android.content.Context;

import com.bthouse.R;
import com.bthouse.mvp.module.HouseCollectBean;
import com.bthouse.mvp.module.SearchCollectBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;


public class HouseCollectAdapter extends BaseQuickAdapter<HouseCollectBean, BaseViewHolder> {

    public HouseCollectAdapter(Context mContext, ArrayList<HouseCollectBean> data) {
        super(R.layout.item_house, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseCollectBean item) {
//        helper.setText(R.id.tv_item_name,item.getTargetCodeStr());

    }
}
