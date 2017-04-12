package com.gdin.analyse.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.present.LoginPresent;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.StringUtils;
import com.gdin.analyse.view.LoginView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompatActivity implements LoginView {
    @BindView(R.id.username_edit)
    EditText userEdit;
    @BindView(R.id.password_edit)
    EditText pwdEdit;
    @BindView(R.id.cb_pwd)
    CheckBox remPwd;
    @BindView(R.id.cb_auto)
    CheckBox autoLogin;


    final int REGISTER_ACTIVITY = 0;
    @BindView(R.id.user_message)
    TextView userMessage;

    private LoginPresent loginPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText(R.string.login_label_signin);
        initWidget();

    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTER_ACTIVITY && resultCode == REGISTER_ACTIVITY) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(data.getStringExtra("schoolName"));
            buffer.append(" -- ");
            buffer.append(data.getStringExtra("gradeName"));
            buffer.append(" -- ");
            buffer.append(data.getStringExtra("className"));

            userMessage.setText(buffer);
            loginPresent.saveRegister(buffer,data);
        }
    }
    @OnClick({R.id.user_message, R.id.login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_message:
                openRegisterDialog();
                break;
            case R.id.login_button:
                onclickLoginBtn();
                break;
        }
    }

    private void initWidget() {

        loginPresent = new LoginPresent(this);
        userMessage.setText(loginPresent.getString("userMessage",""));
        initRegisterString();
        initCheckBoxForRemPwd();
        initCheckBoxForAutoLogin();
        initLoginMessage();
        if (userMessage.getText().equals("")){
            openRegisterDialog();
        }

    }

    //填写学校班级权限等信息
    private void openRegisterDialog() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivityForResult(intent, REGISTER_ACTIVITY);
    }

    //初始化注册标签
    private void initRegisterString() {
        TextView register = (TextView) findViewById(R.id.find);
        String str = getResources().getString(R.string.login_find_pwd);
        int length = str.length();
        SpannableString spa = new SpannableString(str);
        //设置颜色
        spa.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0066CC")), length - 3, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置下划线
        spa.setSpan(new UnderlineSpan(), length - 3, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置背景色
        spa.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                toFindPassword();
                Toast.makeText(LoginActivity.this, "找回", Toast.LENGTH_SHORT).show();
            }
        }, length - 3, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(spa);
        // 设置为可点击状态
        register.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initCheckBoxForRemPwd() {
        //监听记住密码多选框按钮事件
        remPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (remPwd.isChecked()) {
                    loginPresent.updateCheckState("remember", true);
                } else {
                    loginPresent.saveUser("","");
                    loginPresent.updateCheckState("remember", false);
                }

            }
        });

    }

    private void initCheckBoxForAutoLogin() {
        //监听自动登录多选框事件
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (autoLogin.isChecked()) {
                    if (!loginPresent.isChecked("remember")){
                        remPwd.setChecked(true);
                        loginPresent.updateCheckState("remember", true);
                    }
                    loginPresent.updateCheckState("autoLogin", true);
                } else {
                    loginPresent.updateCheckState("autoLogin", false);
                }
            }
        });
    }

    private void initLoginMessage() {
        if (loginPresent.isChecked("remember")) {
            remPwd.setChecked(true);
            userEdit.setText(loginPresent.getString("user", ""));
            pwdEdit.setText(loginPresent.getString("pwd", ""));
            if (loginPresent.isChecked("autoLogin")) {  //记住密码的前提下，自动登录才有效
                autoLogin.setChecked(true);
                onclickLoginBtn();
            }
        } else {   //没有记住密码，则将记住的账号密码清楚，两checkbox状态均需要确保是false
            resetCheckState();
        }
    }

    public void resetCheckState(){
        loginPresent.updateCheckState("remember", false);
        loginPresent.updateCheckState("autoLogin", false);
    }

    //找回密码
    private void toFindPassword() {

    }

    private void onclickLoginBtn(){
        LoginDataEntity entity = new LoginDataEntity(loginPresent.getInt("loginSchoolId",0),
                loginPresent.getInt("loginGradeId",0),loginPresent.getInt("loginClassId",0),loginPresent.getString("loginType",""),
                userEdit.getText().toString(),pwdEdit.getText().toString());

        HttpMethods.getInstance().checkLogin(new cSubscriber<HttpResult<List<LoginDataEntity>>>() {
            @Override
            public void onComplete() {
                if (remPwd.isChecked()) {
                    loginPresent.saveUser(userEdit.getText().toString(),pwdEdit.getText().toString());
                }
                Intent intent = new Intent(LoginActivity.this,TMainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNext(HttpResult<List<LoginDataEntity>> listHttpResult, int i) {
                if (remPwd.isChecked()) {
                    loginPresent.saveUser(userEdit.getText().toString(),pwdEdit.getText().toString());
                }
                Intent intent = new Intent(LoginActivity.this,TMainActivity.class);
                startActivity(intent);
            }
        }, entity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (StringUtils.isEmpty(loginPresent.getString("user", "")) || StringUtils.isEmpty(loginPresent.getString("pwd", ""))){
            resetCheckState();
        }
    }
}
