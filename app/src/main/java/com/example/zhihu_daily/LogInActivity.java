package com.example.zhihu_daily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvSinaLogin;
    private TextView mTvTencentLogin;
    private ImageView mImBack;
    private TextView mTvRegister,mTvMyLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mTvSinaLogin = findViewById(R.id.tv_sina_login);
        mTvTencentLogin = findViewById(R.id.tv_tencent_login);
        mImBack = findViewById(R.id.im_back);
            mImBack.setOnClickListener(this);//返回
        mTvRegister = findViewById(R.id.tv_register);
            mTvRegister.setOnClickListener(this);//去到自己写的注册页面
        mTvMyLogin = findViewById(R.id.tv_mylogin);
            mTvMyLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                Intent intent = new Intent(LogInActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mylogin:
                Intent intent1 = new Intent(LogInActivity.this,MyLoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.im_back:
                finish();
                break;

        }
    }
}
