package com.bthouse.adapter;

import android.content.Context;
import com.bthouse.R;
import com.bthouse.mvp.module.City;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;

public class SearchResultAdapter extends BaseQuickAdapter<City, BaseViewHolder> {

    public SearchResultAdapter(Context mContext, ArrayList<City> data) {
        super(R.layout.item_city_search, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, City item) {

    }
}
