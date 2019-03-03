package com.example.zhihu_daily;

import android.annotation.SuppressLint;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhihu_daily.util.ToastUtil;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private MyfragmentA myfragmentlast;
    private TextView mTvDate,mTvHeaderSignin;
    private ImageView mImAvatar;
    private static long length=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //每次运行前检查缓存文件夹，当缓存过多，自动清除
        @SuppressLint("SdCardPath")
        File file = new File("/data/data/com.example.zhihu_daily/shared_prefs");
        checkSharedPreferences(file);
        isDelete(file);
        Button mBtnSearch = findViewById(R.id.btn_search);
        mTvDate = findViewById(R.id.tv_date);
        final EditText mEtHistory = findViewById(R.id.et_history);

        //设置Toolbar，并设置图标
        Toolbar mToolbar = findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }


        //侧滑栏左侧的点击动作实现后，相应的反应
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView.setCheckedItem(R.id.nav_home);
        //获取NavigationView中控件的实例
        View headerView = mNavigationView.getHeaderView(0);
        mImAvatar = headerView.findViewById(R.id.im_avatar);//基础用户头像
        mImAvatar.setOnClickListener(this);
        mTvHeaderSignin = headerView.findViewById(R.id.tv_header_signin);//请登录的实例
        mTvHeaderSignin.setOnClickListener(this);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //实例化该一个Fragment，该实例加载的是最新的新闻列表
        MyfragmentA myfragment = new MyfragmentA();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, myfragment).commitAllowingStateLoss();

        //1.点击share图片，弹出分享窗口
        //2.点击查询按钮，查找以前的新闻
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String data = mEtHistory.getText().toString();
                int dataValue = 0;
                if(!data.equals("")){
                    dataValue = Integer.parseInt(data);
                }
                //testDate算法检测输入的日期是否正确
                    if(!testDate(data)||dataValue<20130520){
                        ToastUtil.showMsg(MainActivity.this,"请重新输入正确的日期");
                    } else {
                    //对指定日期的新闻列表进行加载
                        mTvDate.setText(data+"新闻");
                        myfragmentlast = MyfragmentA.newInstance("https://news-at.zhihu.com/api/4/news/before/",data,1);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main,myfragmentlast).commitAllowingStateLoss();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_page_right,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return  true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_avatar:
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_header_signin:
                Intent intent1 = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //检验输入的日期格式是否正确
    private boolean testDate(String data){
        boolean translationFlag = true;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = dateFormat.parse(data);

        } catch (ParseException e) {
            e.printStackTrace();
            translationFlag = false;
        }
        return translationFlag;
    }

    private void checkSharedPreferences(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File temp : files) {
                checkSharedPreferences(temp);
            }
        } else {
            length += file.length();
        }
    }

    private void isDelete(File file){
            if(length > 1024*1024*1024){
                //获取目录名
                File[] files = file.listFiles();
                //遍历删除文件
                for(File mFile: files){
                    mFile.delete();
                }
            }
        }


}
