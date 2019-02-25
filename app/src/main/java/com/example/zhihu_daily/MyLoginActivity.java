package com.example.zhihu_daily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zhihu_daily.Util.PostNetConnect;
import com.example.zhihu_daily.Util.ToastUtil;

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
                    //执行登陆活动
                    String username = mEtUsername.getText().toString();
                    String password = mEtPassword.getText().toString();
//                    PostNetConnect connect = new PostNetConnect(   );
//                    connect.HttpConnect(new PostNetConnect.Callback() {
//                        @Override
//                        public void finsh(String response) {
//                            ToastUtil.showMsg(MyLoginActivity.this,response);
//                        }
//                    });
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
