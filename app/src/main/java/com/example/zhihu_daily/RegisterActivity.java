package com.example.zhihu_daily;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zhihu_daily.Util.PostNetConnect;
import com.example.zhihu_daily.Util.ToastUtil;

/**
 * 该类用于实现本人自己写的api，实现登陆功能
 * 但是，实现该功能需要本地服务器打开时方能使用，故先行注释网络请求部分
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mEtUsername,mEtPassword;
    private Button mBtnRegister;
    private ImageView mImback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtnRegister = findViewById(R.id.btn_register);
        mImback = findViewById(R.id.im_register_back);
        Toolbar mTbRegister = findViewById(R.id.tb_register);
        setSupportActionBar(mTbRegister);


        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEtPassword.getText().toString().isEmpty()&&!mEtUsername.getText().toString().isEmpty()){
                    //执行注册活动
                    String username = mEtUsername.getText().toString();
                    String password = mEtPassword.getText().toString();
                    GetNetConnect connect = new GetNetConnect("http://irving.natapp1.cc/testIdeau_war_exploded/register?sign=register&username="+username+"&password="+password);
                    connect.HttpConnect(new GetNetConnect.Callback() {
                        @Override
                        public void finsh(String response) {
                               final String context = response;
                            if(response.equals("Congratulations on your successful registration!")){
                            Handler mainhander = new Handler(Looper.getMainLooper());
                            mainhander.post(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showMsg(RegisterActivity.this,context);
                                }
                            });
                                ToastUtil.showMsg(RegisterActivity.this,response);
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                            Handler mainhander = new Handler(Looper.getMainLooper());
                            mainhander.post(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showMsg(RegisterActivity.this,context);
                                }
                            });

                            }
                        }
                    });
                }else {
                    ToastUtil.showMsg(RegisterActivity.this,"请正确填写资料");
                }
            }
        });

        mImback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
