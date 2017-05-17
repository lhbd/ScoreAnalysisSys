package com.gdin.analyse.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.activity.ClassResultActivity;
import com.gdin.analyse.adapter.StudentRollAdapter;
import com.gdin.analyse.entity.StudentRollEntity;
import com.gdin.analyse.listener.EditWatcherListener;

import java.util.ArrayList;
import java.util.List;

public class StudentRollFragment extends BaseFragment {
    private EditText et;
    private StudentRollAdapter adapter;
    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.student_roll_layout,null);
    }

    @Override
    public void initData(View view) {
        List<StudentRollEntity> data = getData();
        adapter = new StudentRollAdapter(R.layout.student_roll_item_layout,data);
        adapter.setonClickForDetailListener(new StudentRollAdapter.onClickForDetailListener() {
            @Override
            public void onClickForDetail() {
                ((ClassResultActivity)getContext()).addFragment(StudentScoreDetailFragment.newInstance(),3);
            }
        });
        RecyclerView studentRoll = (RecyclerView)view.findViewById(R.id.student_roll);
        et = (EditText)view.findViewById(R.id.search_et);
        setEditChangeListener(data);
        setEditTextEnterListener(data);
        studentRoll.setLayoutManager(new LinearLayoutManager(getContext()));
        studentRoll.setAdapter(adapter);
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
    }

    private void setEditChangeListener(final List<StudentRollEntity> data){
        et.addTextChangedListener(new EditWatcherListener() {
            @Override
            public void notifyData() {
                if (et.getText().toString().equals("")){
                    data.clear();
                    data.addAll(getData());
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
    private void setEditTextEnterListener(final List<StudentRollEntity> data){
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
                            List<StudentRollEntity> newData = new ArrayList<>();
                            for (StudentRollEntity entity : data){
                                if (entity.getName().equals(search)|| String.valueOf(entity.getId()).equals(search) ) {
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

        Bundle args = new Bundle();
        StudentRollFragment fragment = new StudentRollFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private List<StudentRollEntity> getData(){
        List<StudentRollEntity> data = new ArrayList<>();
        for (int i = 0;i<15;i++){
            data.add(new StudentRollEntity(110110,"哈哈","333","88","77","99","11","1"));
        }
        return data;
    }
}
