package com.gdin.analyse.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.gdin.analyse.R;
import com.gdin.analyse.tools.CustomApplication;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    public static final int EXPLODE = 0;       //分解动画
    public static final int FADE = 1;          //淡入淡出
    public static final int SLIDE = 2;         //滑进滑出
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private MenuItem menuItem;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        bindView();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        sp = CustomApplication.getContext().getSharedPreferences("loginUser", MODE_PRIVATE); //context类中使用getSharedPreferences，第一个参数为文件名
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if(null != getToolbar() &&  isShowBacking()){
            showBack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        menuItem = menu.findItem(R.id.select_btn);
        menuItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.select_btn:
                setDialog();
                break;
            case R.id.user_message:
                startActivity(UserMessageActivity.class);
                break;
            case R.id.log_off:
                sp.edit().putBoolean("autoLogin", false).apply();
                startActivity(LoginActivity.class);
                finish();
                break;
            case R.id.help:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private void startActivity(Class toStart){
        Intent intent = new Intent(this,toStart);
        startActivity(intent);
    }

    public void startActivity(Context cur, Class toStart) {
        Intent intent = new Intent();
        intent.setClass(cur, toStart);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    public SharedPreferences getSp(){
        if (sp == null){
            sp = CustomApplication.getContext().getSharedPreferences("loginUser", MODE_PRIVATE); //context类中使用getSharedPreferences，第一个参数为文件名
        }
        return sp;
    }
    /**
     * 设置转场动画
     */

    public void setAnimation(int type){
        switch (type){
            case EXPLODE:
                getWindow().setEnterTransition(new Explode().setDuration(300));
                getWindow().setExitTransition(new Explode().setDuration(300));
                break;
            case FADE:
                getWindow().setEnterTransition(new Fade().setDuration(300));
                getWindow().setExitTransition(new Fade().setDuration(300));
                break;
            case SLIDE:
                getWindow().setEnterTransition(new Slide().setDuration(300));
                getWindow().setExitTransition(new Fade().setDuration(200));
                break;

        }

    }

    /**
     * 获取头部标题的TextView
     * @return
     */
    public TextView getToolbarTitle(){
        return mToolbarTitle;
    }

    protected MenuItem getItem(){
        return menuItem;
    }


    /**
     * 设置头部标题
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if(mToolbarTitle != null){
            mToolbarTitle.setText(title);
        }else{
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack(){
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.icon_back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowBacking(){
        return true;
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    /**
     * 用于butterknife的绑定
     */
    protected abstract void bindView();

    protected abstract void setDialog();
//


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}