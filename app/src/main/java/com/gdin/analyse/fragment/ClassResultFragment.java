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
import com.gdin.analyse.adapter.DataAdapter;
import com.gdin.analyse.adapter.ExamDialogAdapter;
import com.gdin.analyse.entity.ClassScoreEntity;
import com.gdin.analyse.entity.UserMessage;
import com.gdin.analyse.tools.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ClassResultFragment extends BaseFragment {

    RecyclerView recycle;
    TextView change;

    @Override
    public View initView() {
        return View.inflate(getContext(),R.layout.class_score_layout,null);
    }

    @Override
    public void initData(View view) {
        recycle = (RecyclerView) view.findViewById(R.id.class_score);
        change = (TextView)view.findViewById(R.id.select_exam);
        Bundle bundle = getArguments();
        if (bundle==null)
            return;
        final List<ClassScoreEntity> data = bundle.getParcelableArrayList("data");
        if (data==null)
            return;
        DataAdapter adapter = new DataAdapter(R.layout.class_score_item_layout,data) ;
        adapter.openLoadAnimation();
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle.setAdapter(adapter);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                Toast.makeText(getContext(),"点了",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initDialog(final List<UserMessage> itemList){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        final View dialogView = View.inflate(getContext(),R.layout.exam_dialog_layout,null);
        RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.exam_dialog);
        ExamDialogAdapter adapter = new ExamDialogAdapter(R.layout.exam_dialog_item_layout,itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        builder.setTitle("试卷列表");
        builder.setView(dialogView);

        final AlertDialog dialog = builder.show();     //AlertDialog.Builder 的父类AlertDialog才有dismiss方法可以关闭对话框

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(getContext(),
                        ((TextView)(((BaseViewHolder)holder).getView(R.id.exam_dialog_item))).getText().toString(),
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    private void getData(){
        List<UserMessage> data = new ArrayList<>();
        data.add(new UserMessage("0","试卷1"));
        data.add(new UserMessage("1","试卷2"));
        data.add(new UserMessage("2","试卷3"));
        data.add(new UserMessage("3","试卷4"));
        data.add(new UserMessage("4","试卷5"));
        data.add(new UserMessage("5","试卷6"));
        initDialog(data);
//        final List<String> list = new ArrayList<>();
//        HttpMethods.getInstance().getSchoolData(new cSubscriber<HttpResult<List<SchoolEntity>>>() {
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<List<SchoolEntity>> result, int i) {
//                for (SchoolEntity entity : result.getData()){
//                    list.add(entity.getSchoolName());
//                }
//                initDialog(list);
//            }
//        });
    }

    public static ClassResultFragment newInstance(ArrayList<ClassScoreEntity> data) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data",data);
        ClassResultFragment fragment = new ClassResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
