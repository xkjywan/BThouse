package com.bthouse.adapter;

import android.content.Context;

import com.bthouse.R;
import com.bthouse.mvp.module.SearchCollectBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;


public class SearchCollectAdapter extends BaseQuickAdapter<SearchCollectBean, BaseViewHolder> {

    public SearchCollectAdapter(Context mContext, ArrayList<SearchCollectBean> data) {
        super(R.layout.item_search_collect, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchCollectBean item) {
//        helper.setText(R.id.tv_item_name,item.getTargetCodeStr());



    }
}
