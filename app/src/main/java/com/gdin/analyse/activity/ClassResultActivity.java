package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.QuickFragmentPageAdapter;
import com.gdin.analyse.fragment.ClassChartFragment;
import com.gdin.analyse.fragment.ClassResultFragment;
import com.gdin.analyse.present.ClassScorePresent;
import com.gdin.analyse.transformer.DepthPageTransformer;
import com.gdin.analyse.view.ClassScoreView;

import java.util.ArrayList;
import java.util.List;

public class ClassResultActivity extends BaseAppCompatActivity implements ClassScoreView{

    List<Fragment> fragments = new ArrayList<>();
    ClassScorePresent present;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("班级成绩一览");
        init();
    }
    private void init(){

        present = new ClassScorePresent(this);

        ClassResultFragment resultFragment = ClassResultFragment.newInstance(present.getScoreData());
        ClassChartFragment chartFragment = ClassChartFragment.newInstance(present.getRankData());

        fragments.add(resultFragment);
        fragments.add(chartFragment);
        adapter = new QuickFragmentPageAdapter<>(getSupportFragmentManager(),fragments);
        pager = (ViewPager) findViewById(R.id.class_contain);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        pager.setPageTransformer(true,new DepthPageTransformer());
    }

    public void addFragment(){
        ClassChartFragment chartFragment = ClassChartFragment.newInstance(present.getRankData());
        fragments.add(chartFragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);

    }

}
