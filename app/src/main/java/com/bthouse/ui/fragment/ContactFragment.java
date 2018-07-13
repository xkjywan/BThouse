package com.bthouse.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bthouse.R;

public class ContactFragment extends DialogFragment {
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
        view = inflater.inflate(R.layout.dialog_contact, null);
        wechat = (TextView) view.findViewById(R.id.wechat);
        text = (TextView) view.findViewById(R.id.text);
        phone = (TextView) view.findViewById(R.id.phone);

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

    public static ContactFragment newInstance(Context context) {
        Bundle args = new Bundle();
        mContext = context;
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void initEvent() {

    }

    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        setScreenBgLight();
    }
}
