package com.gdin.analyse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.CompleteTextViewAdapter;
import com.gdin.analyse.present.RegisterPresent;
import com.gdin.analyse.tools.CustomAutoCompleteTextView;
import com.gdin.analyse.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.register)
    RelativeLayout register;
    @BindView(R.id.register_school)
    CustomAutoCompleteTextView registerSchool;
    @BindView(R.id.register_grade)
    Spinner gradeSpinner;
    @BindView(R.id.register_class)
    Spinner classSpinner;
    @BindView(R.id.register_confirm_btn)
    Button registerConfirmBtn;
    @BindView(R.id.register_cancel_btn)
    Button registerCancelBtn;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private final int CONFIRM = 0;  //确认返回码
    private final int CANCEL = 1;   //取消返回码


    private RegisterPresent registerPresent;
    private Bundle data = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);
        initWidget();
        this.setFinishOnTouchOutside(false);
    }

    @OnClick({R.id.register_confirm_btn, R.id.register_cancel_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_confirm_btn:
                returnResult();
                break;
            case R.id.register_cancel_btn:
                RegisterActivity.this.setResult(CANCEL, getIntent());
                RegisterActivity.this.finish();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setFocusOnRootLayout();
        return false;
    }

    private void returnResult() {
        String schoolName = registerSchool.getText().toString();
        if (!registerPresent.inputEffective(schoolName)) {
            Toast.makeText(RegisterActivity.this, R.string.register_input_school, Toast.LENGTH_SHORT).show();
            return;
        } else if (!hasCheckedDegree()) {
            Toast.makeText(RegisterActivity.this, R.string.register_check_degree, Toast.LENGTH_SHORT).show();
            return;
        }
        data.putString("schoolName",schoolName);

        if (data==null)
            return;
        Intent intent = getIntent();
        intent.putExtras(data);

        // 设置该SelectCityActivity结果码，并设置结束之后退回的Activity
        RegisterActivity.this.setResult(CONFIRM, intent);
        // 结束SelectCityActivity
        RegisterActivity.this.finish();
    }

    private boolean hasCheckedDegree() {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private void initWidget() {

        registerPresent = new RegisterPresent(this);
        registerPresent.initData();

        //学校
        CompleteTextViewAdapter<String> schoolAdapter = new CompleteTextViewAdapter<>(registerPresent.getSchoolData());
        registerSchool.setAdapter(schoolAdapter);
        registerSchool.setDropDownVerticalOffset(10);
        setFocusOnRootLayout();
        //年级
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, registerPresent.getGradeData());
        gradeSpinner.setAdapter(gradeAdapter);
        gradeSpinner.setDropDownVerticalOffset(70);
        //班级
        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, registerPresent.getClassData());
        classSpinner.setAdapter(classAdapter);
        classSpinner.setDropDownVerticalOffset(70);

        setSpinnerDropDownWidth(gradeSpinner);
        setSpinnerDropDownWidth(classSpinner);

        initSpinnerListener();
        initRadioGroupListener();
    }
    //把焦点设置到父view上
    private void setFocusOnRootLayout(){
        register.setFocusable(true);
        register.setFocusableInTouchMode(true);
        register.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(register.getWindowToken(), 0);//从控件所在的窗口中隐藏
    }


    //注册一个ViewTreeObserver的监听回调，通过监听绘图,在spinner绘制后再调用setDropDownWidth
    private void setSpinnerDropDownWidth(final Spinner spinner) {
        ViewTreeObserver vto = spinner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                spinner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                spinner.setDropDownWidth(spinner.getWidth());
            }
        });
    }

    //获取老师/学生权限
    private void initRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                setFocusOnRootLayout();
                if (id == R.id.degree_teacher) {
                    data.putString("loginType","t");
                } else {
                    data.putString("loginType","s");
                }

            }
        });
    }

    //获取年级班级
    private void initSpinnerListener() {
        registerSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data.putInt("loginSchoolId",registerPresent.getSchoolId(position));
                setFocusOnRootLayout();
            }
        });

        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                data.putString("gradeName",gradeSpinner.getSelectedItem().toString());
                data.putInt("loginGradeId",registerPresent.getGradeId(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setFocusOnRootLayout();
            }
        });
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                data.putString("className",classSpinner.getSelectedItem().toString());
                data.putInt("loginClassId",registerPresent.getClassId(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setFocusOnRootLayout();
            }
        });
    }
}
