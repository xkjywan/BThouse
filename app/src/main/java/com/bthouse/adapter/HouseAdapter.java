package com.bthouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bthouse.R;
import com.bthouse.listener.OnItemClickListener;
import com.bthouse.mvp.module.City;
import com.bthouse.mvp.module.House;

import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<House> mDatas;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public HouseAdapter( Context context,ArrayList<House> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getItemViewType(int position) {
       return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_house, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickLitener != null) {
                        mOnItemClickLitener.onItemClick(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.size() + 1;
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_ad_icon;
        public TextView tv_ad_name;
        public TextView tv_ad_info;

        public MyViewHolder(View itemView) {
            super(itemView);
           /* iv_ad_icon = (ImageView) itemView.findViewById(R.id.iv_ad_icon);
            tv_ad_name = (TextView) itemView.findViewById(R.id.tv_ad_name);
            tv_ad_info = (TextView) itemView.findViewById(R.id.tv_ad_info);*/
        }
    }
}

