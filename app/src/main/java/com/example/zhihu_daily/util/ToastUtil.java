package com.example.zhihu_daily.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static Toast mToast;

    public static void showMsg(Context context,String msg){
        if(null==mToast){
            mToast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        }else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
