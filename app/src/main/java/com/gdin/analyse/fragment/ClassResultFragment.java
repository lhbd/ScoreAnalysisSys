package com.gdin.analyse.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.adapter.ExamDialogAdapter;
import com.gdin.analyse.entity.ClassResultEntity;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.listener.OnRecyclerItemClickListener;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassResultFragment extends BaseFragment {

    @BindView(R.id.school_value)
    TextView schoolValue;
    @BindView(R.id.grade_class_value)
    TextView gradeClassValue;
    @BindView(R.id.exam_name_label_value)
    TextView examNameLabelValue;
    @BindView(R.id.exam_number_value)
    TextView examNumberValue;
    @BindView(R.id.no_exam_number_value)
    TextView noExamNumberValue;
    @BindView(R.id.grade_score_max_value)
    TextView gradeScoreMaxValue;
    @BindView(R.id.grade_score_avg_value)
    TextView gradeScoreAvgValue;
    @BindView(R.id.class_max_value)
    TextView classMaxValue;
    @BindView(R.id.class_avg_value)
    TextView classAvgValue;
    @BindView(R.id.grade_ch_max_value)
    TextView gradeChMaxValue;
    @BindView(R.id.grade_ch_avg_value)
    TextView gradeChAvgValue;
    @BindView(R.id.class_ch_max_value)
    TextView classChMaxValue;
    @BindView(R.id.class_ch_avg_value)
    TextView classChAvgValue;
    @BindView(R.id.grade_math_max_value)
    TextView gradeMathMaxValue;
    @BindView(R.id.grade_math_avg_value)
    TextView gradeMathAvgValue;
    @BindView(R.id.class_math_max_value)
    TextView classMathMaxValue;
    @BindView(R.id.class_math_avg_value)
    TextView classMathAvgValue;
    @BindView(R.id.grade_en_max_value)
    TextView gradeEnMaxValue;
    @BindView(R.id.grade_en_avg_value)
    TextView gradeEnAvgValue;
    @BindView(R.id.class_en_max_value)
    TextView classEnMaxValue;
    @BindView(R.id.class_en_avg_value)
    TextView classEnAvgValue;

    SharedPreferences sp;
    private View lastView;
    private String examSelected;

    float[] subjectValues;

    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.class_score_layout, null);
    }

    @Override
    public void initData(View view) {
        if (sp == null){
            sp = ((ClassResultActivity)getActivity()).getSp();
        }
        schoolValue.setText(sp.getString("schoolName",""));
        gradeClassValue.setText(new StringBuffer().append(sp.getString("gradeName",""))
                .append(sp.getString("className","")));
        getGradeData();
        getClassData();
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity) getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("班级成绩一览");
        activity.resetMenuItem(0);
        activity.showBack(false);

    }

    private void initDialog(final List<ExamDataEntity> itemList) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        final View dialogView = View.inflate(getContext(), R.layout.exam_dialog_layout, null);
        RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.exam_dialog);
        ExamDialogAdapter adapter = new ExamDialogAdapter(R.layout.exam_dialog_item_layout, itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        builder.setTitle("试卷列表");
        builder.setView(dialogView);

        final AlertDialog dialog = builder.show();     //AlertDialog.Builder 的父类AlertDialog才有dismiss方法可以关闭对话框
        TextView ok = (TextView)dialogView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), examSelected,
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                if (lastView != null){
                    lastView.setVisibility(View.INVISIBLE);
                }
                BaseViewHolder viewHolder = ((BaseViewHolder)holder);
                viewHolder.getView(R.id.tick).setVisibility(View.VISIBLE);
                lastView =  viewHolder.getView(R.id.tick);
                examSelected = ((TextView)viewHolder.getView(R.id.exam_dialog_item)).getText().toString();
            }
        });
    }

    private void getClassData(){
        HttpMethods.getInstance().getClassScore(new cSubscriber<HttpResult<ClassResultEntity>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<ClassResultEntity> result, int i) {
                setClassResult(result.getData());
            }
        },new ClassResultEntity(sp.getInt("loginSchoolId",0),sp.getInt("loginGradeId",0),
                sp.getInt("loginClassId",0),CustomApplication.getExamName()));
    }
    private void getGradeData(){
        HttpMethods.getInstance().getClassScore(new cSubscriber<HttpResult<ClassResultEntity>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<ClassResultEntity> result, int i) {
                setGradeResult(result.getData());
            }
        },new ClassResultEntity(sp.getInt("loginSchoolId",0),sp.getInt("loginGradeId",0),
                0,CustomApplication.getExamName()));
    }
    private void setClassResult(ClassResultEntity entity ){
        examNameLabelValue.setText(CustomApplication.getExamName());
        examNumberValue.setText(String.valueOf(entity.getNumber()));
        classMaxValue.setText(String.valueOf(entity.getSumBest()));
        classAvgValue.setText(String.valueOf(entity.getSumAvg()));
        classChMaxValue.setText(String.valueOf(entity.getChBest()));
        classChAvgValue.setText(String.valueOf(entity.getChAvg()));
        classMathMaxValue.setText(String.valueOf(entity.getMathBest()));
        classMathAvgValue.setText(String.valueOf(entity.getMathAvg()));
        classEnMaxValue.setText(String.valueOf(entity.getEnBest()));
        classEnAvgValue.setText(String.valueOf(entity.getEnAvg()));
        subjectValues = new float[4];
        subjectValues[0] = (float)entity.getSumBest();
        subjectValues[1] = (float)entity.getChBest();
        subjectValues[2] = (float)entity.getMathBest();
        subjectValues[3] = (float)entity.getEnBest();
    }
    private void setGradeResult(ClassResultEntity entity ){
        gradeScoreMaxValue.setText(String.valueOf(entity.getSumBest()));
        gradeScoreAvgValue.setText(String.valueOf(entity.getSumAvg()));
        gradeChMaxValue.setText(String.valueOf(entity.getChBest()));
        gradeChAvgValue.setText(String.valueOf(entity.getChAvg()));
        gradeMathMaxValue.setText(String.valueOf(entity.getMathBest()));
        gradeMathAvgValue.setText(String.valueOf(entity.getMathAvg()));
        gradeEnMaxValue.setText(String.valueOf(entity.getEnBest()));
        gradeEnAvgValue.setText(String.valueOf(entity.getEnAvg()));
    }

    private void getExamData() {
        HttpMethods.getInstance().getExam(new cSubscriber<HttpResult<List<ExamDataEntity>>>() {
            @Override
            public void onComplete() {
            }
            @Override
            public void onNext(HttpResult<List<ExamDataEntity>> result, int i) {
                initDialog(result.getData());
            }
        }, new ExamDataEntity(sp.getInt("loginSchoolId",0),sp.getInt("loginGradeId",0),
                sp.getInt("loginClassId",0)));
    }

    public static ClassResultFragment newInstance() {
        Bundle bundle = new Bundle();
        ClassResultFragment fragment = new ClassResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public float[] getSubjectValues() {
        return subjectValues;
    }

    @OnClick({R.id.score_confirm_label, R.id.ch_confirm_label, R.id.math_confirm_label, R.id.en_confirm_label, R.id.t_select_exam})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.score_confirm_label:
                ((ClassResultActivity)getContext()).updateFragment(0);
                break;
            case R.id.ch_confirm_label:
                ((ClassResultActivity)getContext()).updateFragment(1);
                break;
            case R.id.math_confirm_label:
                ((ClassResultActivity)getContext()).updateFragment(2);
                break;
            case R.id.en_confirm_label:
                ((ClassResultActivity)getContext()).updateFragment(3);
                break;
            case R.id.t_select_exam:
                getExamData();
                break;
            default:
                break;
        }
    }
}
