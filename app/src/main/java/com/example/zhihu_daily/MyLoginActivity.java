package com.example.zhihu_daily;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zhihu_daily.util.ToastUtil;
/**
 * 该类用于实现本人自己写的api，实现注册功能。
 * 但是，实现该功能需要本地服务器打开时方能使用，故先行注释网络请求部分
 */
public class MyLoginActivity extends AppCompatActivity {

    private EditText mEtUsername,mEtPassword;
    private Button mBtnMylogin;
    private ImageView mImBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        mEtUsername = findViewById(R.id.et_mylogin_username);
        mEtPassword = findViewById(R.id.et_mylogin_password);
        mBtnMylogin = findViewById(R.id.btn_mylogin);
        mImBack = findViewById(R.id.im_mylogin_back);

        mBtnMylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mEtPassword.getText().toString().isEmpty()&&!mEtUsername.getText().toString().isEmpty()){
                    //当EditorText不为空时执行登陆活动
                    String username = mEtUsername.getText().toString();
                    String password = mEtPassword.getText().toString();
                    GetNetConnect connect = new GetNetConnect("http://irving.natapp1.cc/testIdeau_war_exploded/login?sign=login&username="+username+"&password="+password);
                    connect.HttpConnect(new GetNetConnect.Callback() {
                        @Override
                        public void finsh(String response) {
                            final String context = response;
                            if(response.equals("Login successfully!")){
                            Handler mainhander = new Handler(Looper.getMainLooper());
                            mainhander.post(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showMsg(MyLoginActivity.this,context);
                                }
                            });
                                Intent intent = new Intent(MyLoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else {
                                Handler mainhander = new Handler(Looper.getMainLooper());
                                mainhander.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showMsg(MyLoginActivity.this,context);
                                    }
                                });
                            }
                        }
                    });
                }else {
                    ToastUtil.showMsg(MyLoginActivity.this,"请正确填写资料");
                }
            }
        });

        mImBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
