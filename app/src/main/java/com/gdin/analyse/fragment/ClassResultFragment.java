package com.gdin.analyse.fragment;

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
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.model.teacher.ClassResultScoreEntity;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;
import com.gdin.analyse.listener.OnRecyclerItemClickListener;

import java.util.ArrayList;
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

    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.class_score_layout, null);
    }

    @Override
    public void initData(View view) {
        final Bundle bundle = getArguments();
        if (bundle == null)
            return;
        final List<ClassResultScoreEntity> data = bundle.getParcelableArrayList("data");
        if (data == null)
            return;

    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity) getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("班级成绩一览");
        activity.showItem(false);
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

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(getContext(),
                        ((TextView) (((BaseViewHolder) holder).getView(R.id.exam_dialog_item))).getText().toString(),
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
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
        }, new ExamDataEntity(CustomApplication.getSchoolId(), CustomApplication.getGradeId(), CustomApplication.getClassId()));
    }

    public static ClassResultFragment newInstance(ArrayList<ClassResultScoreEntity> data) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", data);
        ClassResultFragment fragment = new ClassResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.score_confirm_label, R.id.ch_confirm_label, R.id.math_confirm_label, R.id.en_confirm_label, R.id.t_select_exam})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.score_confirm_label:
                ((ClassResultActivity)getContext()).addClassChartFragment();
                break;
            case R.id.ch_confirm_label:
                ((ClassResultActivity)getContext()).addClassChartFragment();
                break;
            case R.id.math_confirm_label:
                ((ClassResultActivity)getContext()).addClassChartFragment();
                break;
            case R.id.en_confirm_label:
                ((ClassResultActivity)getContext()).addClassChartFragment();
                break;
            case R.id.t_select_exam:
                getExamData();
                break;
            default:
                break;
        }
    }
}
