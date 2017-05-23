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
import com.gdin.analyse.entity.StudentDetailRequestEntity;
import com.gdin.analyse.entity.StudentRankResultEntity;
import com.gdin.analyse.entity.StudentScoreResultEntity;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.listener.OnRecyclerItemClickListener;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentScoreDetailFragment extends BaseFragment {

    @BindView(R.id.s_exam_name_value)
    TextView sExamNameValue;
    @BindView(R.id.s_name_value)
    TextView sNameValue;
    @BindView(R.id.s_num_value)
    TextView sNumValue;
    @BindView(R.id.s_whole_score_value)
    TextView sWholeScoreValue;
    @BindView(R.id.s_ch_score_value)
    TextView sChScoreValue;
    @BindView(R.id.s_math_score_value)
    TextView sMathScoreValue;
    @BindView(R.id.s_en_score_value)
    TextView sEnScoreValue;
    @BindView(R.id.s_whole_g_rank_value)
    TextView sWholeGRankValue;
    @BindView(R.id.s_ch_g_rank_value)
    TextView sChGRankValue;
    @BindView(R.id.s_math_g_rank_value)
    TextView sMathGRankValue;
    @BindView(R.id.s_en_g_rank_value)
    TextView sEnGRankValue;
    @BindView(R.id.s_whole_c_rank_value)
    TextView sWholeCRankValue;
    @BindView(R.id.s_ch_c_rank_value)
    TextView sChCRankValue;
    @BindView(R.id.s_math_c_rank_value)
    TextView sMathCRankValue;
    @BindView(R.id.s_en_c_rank_value)
    TextView sEnCRankValue;

    private int stuNum;
    private View lastView;
    private String examSelected;
    private float[] scoreValues;
    private float[] cRankValues;
    private float[] gRankValues;


    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.s_score_layout, null);
    }

    @Override
    public void initData(View view) {
        getStudentScore();
        getStudentGradeRank();
        getStudentClassRank();
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity) getActivity();
        if (activity == null)
            return;
        activity.getToolbarTitle().setText("个人成绩一览");
        activity.resetMenuItem(-1);
        activity.showBack(true);
        if (activity.getStuNum() == stuNum)
            return;
        setFirst(true);
        this.stuNum = activity.getStuNum();
    }


    private void getStudentScore() {
        HttpMethods.getInstance().getStudentScore(new cSubscriber<HttpResult<StudentScoreResultEntity>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<StudentScoreResultEntity> result, int i) {
                initScoreText(result.getData());
            }
        }, new StudentDetailRequestEntity(CustomApplication.getExamId(), this.stuNum));
    }

    private void getStudentGradeRank() {
        HttpMethods.getInstance().getStudentRank(new cSubscriber<HttpResult<StudentRankResultEntity>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<StudentRankResultEntity> result, int i) {
                initGRankText(result.getData());
            }
        }, new StudentDetailRequestEntity(CustomApplication.getExamId(), "G",this.stuNum));
    }
    private void getStudentClassRank() {
        HttpMethods.getInstance().getStudentRank(new cSubscriber<HttpResult<StudentRankResultEntity>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<StudentRankResultEntity> result, int i) {
                initCRankText(result.getData());
            }
        }, new StudentDetailRequestEntity(CustomApplication.getExamId(), "C",this.stuNum));
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

    private void initScoreText(StudentScoreResultEntity entity) {
        sExamNameValue.setText(CustomApplication.getExamName());
        sNameValue.setText(entity.getStuName());
        sNumValue.setText(String.valueOf(entity.getStuNum()));
        sWholeScoreValue.setText(String.valueOf(entity.getSumScore()));
        sChScoreValue.setText(String.valueOf(entity.getChScore()));
        sMathScoreValue.setText(String.valueOf(entity.getMathScore()));
        sEnScoreValue.setText(String.valueOf(entity.getEnScore()));
        scoreValues = new float[4];
        scoreValues[0] = entity.getSumScore();
        scoreValues[1] = entity.getChScore();
        scoreValues[2] = entity.getMathScore();
        scoreValues[3] = entity.getEnScore();
}
    private void initGRankText(StudentRankResultEntity entity) {
        sWholeGRankValue.setText(String.valueOf(entity.getSumRank()));
        sChGRankValue.setText(String.valueOf(entity.getChRank()));
        sMathGRankValue.setText(String.valueOf(entity.getMathRank()));
        sEnGRankValue.setText(String.valueOf(entity.getEnRank()));
        gRankValues = new float[4];
        gRankValues[0] = entity.getSumRank();
        gRankValues[1] = entity.getChRank();
        gRankValues[2] = entity.getMathRank();
        gRankValues[3] = entity.getEnRank();
    }
    private void initCRankText(StudentRankResultEntity entity) {
        sWholeCRankValue.setText(String.valueOf(entity.getSumRank()));
        sChCRankValue.setText(String.valueOf(entity.getChRank()));
        sMathCRankValue.setText(String.valueOf(entity.getMathRank()));
        sEnCRankValue.setText(String.valueOf(entity.getEnRank()));
        cRankValues = new float[4];
        cRankValues[0] = entity.getSumRank();
        cRankValues[1] = entity.getChRank();
        cRankValues[2] = entity.getMathRank();
        cRankValues[3] = entity.getEnRank();
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
    @OnClick({R.id.s_select_exam, R.id.s_score_detail_label, R.id.s_g_rank_detail_label, R.id.s_c_rank_detail_label})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.s_select_exam:
                getExamData();
                break;
            case R.id.s_score_detail_label:
                ((ClassResultActivity)getContext()).updateStudentChartFragment(0);
                break;
            case R.id.s_g_rank_detail_label:
                ((ClassResultActivity)getContext()).updateStudentChartFragment(1);
                break;
            case R.id.s_c_rank_detail_label:
                ((ClassResultActivity)getContext()).updateStudentChartFragment(2);
                break;
        }
    }

    public float[] getScoreValues() {
        return scoreValues;
    }

    public float[] getcRankValues() {
        return cRankValues;
    }

    public float[] getgRankValues() {
        return gRankValues;
    }

    public static StudentScoreDetailFragment newInstance(int stuNum) {
        Bundle bundle = new Bundle();
        bundle.putInt("stuNum", stuNum);
        StudentScoreDetailFragment fragment = new StudentScoreDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
