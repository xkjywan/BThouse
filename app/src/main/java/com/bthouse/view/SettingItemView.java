package com.bthouse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bthouse.R;


/**
 * Created by maning on 16/6/17.
 * 设置界面的组合控件
 */
public class SettingItemView extends FrameLayout {

    private Context context;

    //声明的控件
    private TextView tv_left;  //标题
    private TextView tv_right;  //右侧文字
    private ImageView iv_red_dot;   //小红点
    private ImageView iv_left;   //左侧图标
    private ImageView iv_right;   //右侧图标
    private TextView line_view;   //线


    private String leftText;
    private Drawable leftImage;
    private float leftTextSize;
    private int leftTextColor;

    private String rightText;
    private Drawable rightImage;
    private float rightTextSize;
    private int rightTextColor;

    /*是否显示*/
    private boolean isShowLine;
    private boolean isShowLeftIcon;
    private boolean isShowRightIcon;
    private boolean isShowRedDot;


    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        /*左边文案*/
        leftText = typedArray.getString(R.styleable.SettingItemView_setting_left_text);
        leftTextSize = typedArray.getInteger(R.styleable.SettingItemView_setting_left_text_size_sp, 15);
        leftTextColor = typedArray.getColor(R.styleable.SettingItemView_setting_left_text_color, context.getResources().getColor(R.color.colorText333));
        leftImage = typedArray.getDrawable(R.styleable.SettingItemView_setting_left_image);

        /*右边文案*/
        rightText = typedArray.getString(R.styleable.SettingItemView_setting_right_text);
        rightTextSize = typedArray.getInteger(R.styleable.SettingItemView_setting_right_text_size_sp, 12);
        rightTextColor = typedArray.getColor(R.styleable.SettingItemView_setting_right_text_color, context.getResources().getColor(R.color.colorText666));
        rightImage = typedArray.getDrawable(R.styleable.SettingItemView_setting_right_image);

        /*显示隐藏相关*/
        isShowLine = typedArray.getBoolean(R.styleable.SettingItemView_setting_show_bottom_line, true);
        isShowLeftIcon = typedArray.getBoolean(R.styleable.SettingItemView_setting_show_left_icon, true);
        isShowRedDot = typedArray.getBoolean(R.styleable.SettingItemView_setting_show_red_dot, false);
        isShowRightIcon = typedArray.getBoolean(R.styleable.SettingItemView_setting_show_right_icon, true);

        //销毁
        typedArray.recycle();

        //初始化View
        getViews();

    }

    private void getViews() {

        View.inflate(context, R.layout.setting_item_view, this);
        tv_left = (TextView) findViewById(R.id.setting_tv_title);
        tv_right = (TextView) findViewById(R.id.setting_tv_right);
        iv_red_dot = (ImageView) findViewById(R.id.setting_iv_red);
        iv_left = (ImageView) findViewById(R.id.setting_left_icon);
        iv_right = (ImageView) findViewById(R.id.setting_right_icon);
        line_view = (TextView) findViewById(R.id.setting_line_view);

        initViews();
    }

    private void initViews() {
        //左边
        setLeftText(leftText);
        setLeftTextSize(leftTextSize);
        setLeftTextColor(leftTextColor);
        setLeftImage(leftImage);
        //右边
        setRightText(rightText);
        setRightTextSize(rightTextSize);
        setRightTextColor(rightTextColor);
        setRightImage(rightImage);

        //显示相关
        setRedDot(isShowRedDot);
        setLineView(isShowLine);
    }

    //----------------------------------
    public void showRedDot() {
        isShowRedDot = true;
        initViews();
    }

    public void hideRedDot() {
        isShowRedDot = false;
        initViews();
    }

    public void showLeftIcon() {
        isShowLeftIcon = true;
        initViews();
    }

    public void hideLeftIcon() {
        isShowLeftIcon = false;
        initViews();
    }

    public void showRightIcon() {
        isShowRightIcon = true;
        initViews();
    }

    public void hideRightIcon() {
        isShowRightIcon = false;
        initViews();
    }

    public void showBottomLine() {
        isShowLine = true;
        initViews();
    }

    public void hideBottomLine() {
        isShowLine = false;
        initViews();
    }
    //----------------------------------

    private void setRedDot(boolean flag) {
        if (flag) {
            iv_red_dot.setVisibility(View.VISIBLE);
        } else {
            iv_red_dot.setVisibility(View.GONE);
        }
    }

    private void setLineView(boolean flag) {
        if (flag) {
            line_view.setVisibility(View.VISIBLE);
        } else {
            line_view.setVisibility(View.GONE);
        }
    }
    //-------------------

    //左边
    public void setLeftImage(Drawable drawable) {
        if (isShowLeftIcon && drawable != null) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageDrawable(drawable);
        } else {
            iv_left.setVisibility(View.GONE);
        }
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        tv_left.setText(leftText);
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
        tv_left.setTextColor(leftTextColor);
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
        tv_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftTextSize);
    }

    //右边
    public void setRightImage(Drawable rightImage) {
        this.rightImage = rightImage;
        if (isShowRightIcon && rightImage != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageDrawable(rightImage);
        } else {
            iv_right.setVisibility(View.GONE);
        }
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        if (TextUtils.isEmpty(rightText)) {
            tv_right.setVisibility(View.GONE);
        } else {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(rightText);
        }

    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        tv_right.setTextColor(rightTextColor);
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
        tv_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightTextSize);
    }
}
