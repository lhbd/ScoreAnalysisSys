package com.gdin.analyse.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.adapter.StudentRollSortAdapter;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.StudentRollSortRequestEntity;
import com.gdin.analyse.entity.StudentRollSortResultEntity;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.listener.EditWatcherListener;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.ArrayList;
import java.util.List;

public class StudentRollFragment extends BaseFragment {
    private EditText et;
    private StudentRollSortAdapter adapter;
    private RecyclerView studentRoll;
    private SharedPreferences sp;
    private List<StudentRollSortResultEntity> data = new ArrayList<>();
    private int limitNum=-1;
    private String orderBy="";
    private String sortBy="";

    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.student_roll_layout,null);
    }

    @Override
    public void initData(View view) {
        if (sp == null){
            sp = ((ClassResultActivity)getActivity()).getSp();
        }
        studentRoll = (RecyclerView)view.findViewById(R.id.student_roll);
        et = (EditText)view.findViewById(R.id.search_et);
        getStudentRollSortData();
    }

    @Override
    public void showBtn() {
        ClassResultActivity activity = (ClassResultActivity)getActivity();
        if (activity == null) {
            return;
        }
        activity.getToolbarTitle().setText("学生名单一览");
        activity.resetMenuItem(2);
        activity.showBack(true);
        if (this.orderBy.equals(activity.getOrderBy()) && this.sortBy.equals(activity.getSortBy()) && this.limitNum == activity.getLimitNum())
            return;
        setFirst(true);
        this.orderBy = activity.getOrderBy();
        this.sortBy = activity.getSortBy();
        this.limitNum = activity.getLimitNum();
        if (data == null)
            return;
        data.clear();
    }

    private void getStudentRollSortData() {
        HttpMethods.getInstance().getStudentRollSort(new cSubscriber<HttpResult<List<StudentRollSortResultEntity>>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<List<StudentRollSortResultEntity>> result, int i) {
                data.addAll(result.getData());
                continueInitData();
            }
        }, new StudentRollSortRequestEntity(sp.getInt("loginSchoolId",0),sp.getInt("loginGradeId",0),
                sp.getInt("loginClassId",0),limitNum,CustomApplication.getExamName(),orderBy,sortBy));
    }
    private void continueInitData(){
        if (data == null || data.size() == 0)
            return;
        if (adapter == null){
            adapter = new StudentRollSortAdapter(R.layout.student_roll_item_layout,data);
            adapter.setonClickForDetailListener(new StudentRollSortAdapter.onClickForDetailListener() {
                @Override
                public void onClickForDetail(int pos) {
                    ((ClassResultActivity)getContext())
                            .updateStudentScoreFragment(Integer.valueOf(adapter.getItem(pos).getStuNum()));
                }
            });
            setEditChangeListener();
            setEditTextEnterListener();
            studentRoll.setLayoutManager(new LinearLayoutManager(getContext()));
            studentRoll.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }

    }

    private void setEditChangeListener(){
        et.addTextChangedListener(new EditWatcherListener() {
            @Override
            public void notifyData() {
                if (et.getText().toString().equals("")){
                    data.clear();
                   getStudentRollSortData();
                }
            }
        });

    }
    private void setEditTextEnterListener(){
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    switch (event.getAction()){
                        case KeyEvent.ACTION_DOWN:
                            break;
                        case KeyEvent.ACTION_MULTIPLE:
                            break;
                        case KeyEvent.ACTION_UP:
                            String search = et.getText().toString();
                            List<StudentRollSortResultEntity> newData = new ArrayList<>();
                            for (StudentRollSortResultEntity entity : data){
                                if (entity.getStuName().equals(search)|| String.valueOf(entity.getStuNum()).equals(search) ) {
                                    newData.add(entity);
                                    break;
                                }
                            }
                            if (newData.size()==0){
                                Toast.makeText(getContext(),"查询无结果，请检查是否输入有误",Toast.LENGTH_SHORT).show();
                            }else{
                                data.clear();
                                data.addAll(newData);
                                adapter.notifyDataSetChanged();
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }else {
                    return false;
                }
            }
        });
    }

    public static StudentRollFragment newInstance() {

        Bundle bundle = new Bundle();
        StudentRollFragment fragment = new StudentRollFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
