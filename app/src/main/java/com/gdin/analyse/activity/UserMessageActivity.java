package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.UserMessageAdapter;
import com.gdin.analyse.present.UserMessagePresent;
import com.gdin.analyse.tools.StringUtils;
import com.gdin.analyse.view.UserMessageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserMessageActivity extends BaseAppCompatActivity implements UserMessageView {

    @BindView(R.id.user_message)
    RecyclerView messageView;
    @BindView(R.id.input_cur_pwd)
    EditText inputCurPwd;
    @BindView(R.id.input_new_pwd)
    EditText inputNewPwd;
    @BindView(R.id.input_new_pwd_again)
    EditText inputNewPwdAgain;
    @BindView(R.id.cur_pwd)
    TextView curPwd;
    @BindView(R.id.new_pwd)
    TextView newPwd;
    @BindView(R.id.new_pwd_again)
    TextView newPwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation(BaseAppCompatActivity.FADE);
        getToolbarTitle().setText(R.string.show_user_message);
        init();
    }

    private void init() {
        UserMessagePresent present = new UserMessagePresent(this);
        UserMessageAdapter adapter = new UserMessageAdapter(R.layout.user_message_item_layout, present.init());
        adapter.openLoadAnimation();
        messageView.setLayoutManager(new LinearLayoutManager(this));
        messageView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_message_layout;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text_card)
    public void onClick() {
        final int RESET = 0;    //开始修改
        final int CUR_NULL = 1;  //原密码不能为空
        final int NEW_NULL = 2;  //新密码不能为空
        final int NEW_AGAIN_NULL = 3; //再次输入的新密码不能为空
        final int NEW_ERROR = 4;  //两次输入的新密码不相同
        switch (getAction()){
            case RESET:
                Toast.makeText(this, "点点点", Toast.LENGTH_SHORT).show();
                break;
            case CUR_NULL:
                curPwd.setText(R.string.cur_pwd_null);
                break;
            case NEW_NULL:
                newPwd.setText(R.string.new_pwd_null);
                break;
            case NEW_AGAIN_NULL:
                newPwdAgain.setText(R.string.new_pwd_again_null);
                break;
            case NEW_ERROR:
                newPwd.setText(R.string.new_pwd_error);
                newPwdAgain.setText(R.string.new_pwd_error);
                break;
        }
    }

    private int getAction() {
        if (StringUtils.isEmpty(inputCurPwd.getText().toString()))
            return 1;
        if (StringUtils.isEmpty(inputNewPwd.getText().toString()))
            return 2;
        if (StringUtils.isEmpty(inputNewPwdAgain.getText().toString()))
            return 3;
        if (!inputNewPwd.getText().toString().equals(inputNewPwdAgain.getText().toString()))
            return 4;
        return 0;
    }

    @Override
    public void show() {
    }


}
