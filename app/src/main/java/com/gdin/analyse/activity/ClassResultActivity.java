package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.QuickFragmentPageAdapter;
import com.gdin.analyse.fragment.ClassChartFragment;
import com.gdin.analyse.fragment.ClassResultFragment;
import com.gdin.analyse.fragment.StudentRollFragment;
import com.gdin.analyse.fragment.StudentScoreChartFragment;
import com.gdin.analyse.fragment.StudentScoreDetailFragment;
import com.gdin.analyse.present.ClassResultPresent;
import com.gdin.analyse.transformer.DepthPageTransformer;
import com.gdin.analyse.view.ClassResultView;

import java.util.ArrayList;
import java.util.List;

public class ClassResultActivity extends BaseAppCompatActivity implements ClassResultView {

    List<Fragment> fragments = new ArrayList<>();
    ClassResultPresent present;
    QuickFragmentPageAdapter<Fragment> adapter;
    ViewPager pager;

    @Override
    protected int getLayoutId() {
        return R.layout.class_result_layout;
    }

    @Override
    protected void bindView() {
    }

    @Override
    protected void setDialog() {
        Toast.makeText(this,"弹出对话框",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("");
        init();
    }

    private void init(){
        present = new ClassResultPresent(this);
        ClassResultFragment resultFragment = ClassResultFragment.newInstance(present.getScoreData());
        fragments.add(resultFragment);
        adapter = new QuickFragmentPageAdapter<>(getSupportFragmentManager(),fragments);
        pager = (ViewPager) findViewById(R.id.class_contain);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        pager.setPageTransformer(true,new DepthPageTransformer());
    }


    public void addStudentScoreChartFragment(){
        if (pager.getChildCount() > 4) {
            pager.setCurrentItem(pager.getCurrentItem()+1,true);
            return;
        }
        StudentScoreChartFragment fragment = StudentScoreChartFragment.newInstance();
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);
    }

    public void addStudentScoreDetailFragment(){
        if (pager.getChildCount() > 3) {
            pager.setCurrentItem(pager.getCurrentItem()+1,true);
            return;
        }
        StudentScoreDetailFragment fragment = StudentScoreDetailFragment.newInstance();
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);
    }
    public void addStudentRollFragment(){
        if (pager.getChildCount() > 2) {
            pager.setCurrentItem(pager.getCurrentItem()+1,true);
            return;
        }
        StudentRollFragment fragment = StudentRollFragment.newInstance();
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);
    }
    public void addClassChartFragment(){
        if (pager.getChildCount() > 1) {
            pager.setCurrentItem(pager.getCurrentItem()+1,true);
            return;
        }
        ClassChartFragment fragment = ClassChartFragment.newInstance(present.getRankData());
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);
    }
    public void showItem(boolean value){
        if (getItem() == null)
            return;
       getItem().setVisible(value);
    }

}
