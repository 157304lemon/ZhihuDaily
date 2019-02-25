package com.example.zhihu_daily.weight;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.zhihu_daily.R;

public class ShareDialog extends Dialog {

    private TextView mTvSina,mTvWeChat,mTvFriends,mTvEverNote,mTvYouDao,mTvQQ,mTvMore;

    public ShareDialog(@NonNull Context context) {
        super(context);
    }

    public ShareDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layou_share_dialog);
        //设置宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x*0.9);//设置dialog的宽度为屏幕的宽度的90%；
        getWindow().setAttributes(p);

        mTvSina = findViewById(R.id.tv_share_sina);
        mTvWeChat = findViewById(R.id.tv_share_wechat);
        mTvFriends = findViewById(R.id.tv_share_frinds);
        mTvEverNote = findViewById(R.id.tv_share_evernote);
        mTvYouDao = findViewById(R.id.tv_share_youdao);
        mTvQQ = findViewById(R.id.tv_share_qq);
        mTvMore = findViewById(R.id.tv_share_more);


    }
}
