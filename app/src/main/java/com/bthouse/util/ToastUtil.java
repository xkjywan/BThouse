package com.bthouse.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Toast显示
 *
 * @author hbl
 */
public class ToastUtil {

    public  static final   int TIME_LONG= Toast.LENGTH_LONG;
    public  static final   int TIME_SHORT= Toast.LENGTH_SHORT;
    /**
     * @param context
     * @param paramInt1 要显示字符串资源的ID
     * @param paramInt2 时间长短
     */
    public static void show(Context context, int paramInt1, int paramInt2) {
        if(null==context)
            return;
        Toast.makeText(context, context.getResources().getString(paramInt1), paramInt2).show();


    }

    /**
     * @param context
     * @param charSequence 要显示的字符
     * @param paramInt     时间;
     * @return
     */
    public static void show(Context context, CharSequence charSequence, int paramInt) {
        if(null==context)
            return;
        Toast.makeText(context, charSequence, paramInt).show();
    }

    /**
     * @param context
     * @param
     */

    public static void show(Context context, CharSequence charSequence) {
        if(null==context)
            return;
        Toast.makeText(context, charSequence, TIME_SHORT).show();

    }





    public static void showNetWorkError(Context context) {
        if(null==context)
            return;
        Toast.makeText(context, "当前网络不可用，请检查网络", TIME_LONG).show();
    }

    /**
     * @param context
     * @param paramInt 要显示字符的资源ID
     */
    public static void show(Context context, int paramInt) {
        if(null==context)
            return;
        Toast.makeText(context, context.getResources().getString(paramInt),TIME_SHORT)
                .show();
    }

}
