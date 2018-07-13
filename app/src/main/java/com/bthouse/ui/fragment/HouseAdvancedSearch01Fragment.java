package com.bthouse.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bthouse.R;

public class HouseAdvancedSearch01Fragment extends DialogFragment {
    private static Context mContext;

    View view;
    private TextView wechat,text,phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //隐藏title
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);

        //设置dialog的 进出 动画
//        getDialog().getWindow().setWindowAnimations(R.style.qrcode_animate_dialog);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.transparent));
        view = inflater.inflate(R.layout.dialog_advanced_search01, null);

        initEvent();

        setScreenBgDarken();
        return view;
    }

    //变暗
    private void setScreenBgDarken() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        lp.dimAmount = 0.5f;
        getActivity().getWindow().setAttributes(lp);
    }

    //变亮
    private void setScreenBgLight() {
        WindowManager.LayoutParams lp =  getActivity().getWindow().getAttributes();
        lp.alpha = 1.0f;
        lp.dimAmount = 1.0f;
        getActivity().getWindow().setAttributes(lp);
    }

    public static HouseAdvancedSearch01Fragment newInstance(Context context) {
        Bundle args = new Bundle();
        mContext = context;
        HouseAdvancedSearch01Fragment fragment = new HouseAdvancedSearch01Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void initEvent() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setScreenBgLight();
    }
}
