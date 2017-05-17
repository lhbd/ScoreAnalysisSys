package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.QuickFragmentPageAdapter;
import com.gdin.analyse.fragment.ClassChartDetailFragment;
import com.gdin.analyse.fragment.ClassChartFragment;
import com.gdin.analyse.fragment.ClassResultFragment;
import com.gdin.analyse.present.ClassResultPresent;
import com.gdin.analyse.transformer.DepthPageTransformer;
import com.gdin.analyse.view.ClassResultView;

import java.util.ArrayList;
import java.util.List;

public class ClassResultActivity extends BaseAppCompatActivity implements ClassResultView {

    private int actionType = 0;
    private int detailType = -1;
    private long exitTime = 0;
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
    protected void isShowBacking() {
        showBack(false);
    }

    @Override
    protected void onClickBack() {
        if (pager == null)
            return;
        pager.setCurrentItem(pager.getCurrentItem()-1,true);
    }

    @Override
    protected void isShowMenuItem() {

    }

    @Override
    protected void startAction() {
        if (actionType == 0){
            actionType = 1;
            resetChartFragment();
        }else if(actionType ==1){
            actionType = 0;
            resetTextFragment();
        }else {
            Toast.makeText(this,"弹出对话框",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("");
        init();
    }

    private void init(){
        present = new ClassResultPresent(this);
        ClassResultFragment textFragment = ClassResultFragment.newInstance();
        fragments.add(textFragment);
        adapter = new QuickFragmentPageAdapter<>(getSupportFragmentManager(),fragments);
        pager = (ViewPager) findViewById(R.id.class_contain);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        pager.setPageTransformer(true,new DepthPageTransformer());
    }

    public void resetChartFragment(){
        ClassResultFragment resultFragment = (ClassResultFragment)fragments.get(0);
        fragments.clear();
        ClassChartDetailFragment fragment = ClassChartDetailFragment.newInstance(resultFragment.getSubjectValues());
        fragments.add(fragment);
        adapter.updateFlags(0);
        adapter.notifyDataSetChanged();
    }
    public void resetTextFragment(){
        fragments.clear();
        ClassResultFragment fragment = ClassResultFragment.newInstance();
        fragments.add(fragment);
        adapter.updateFlags(0);
        adapter.notifyDataSetChanged();
    }

    public void updateFragment(int type){
        if (type == detailType){
            pager.setCurrentItem(pager.getCurrentItem()+1,true);
            return;
        }
        detailType = type;
        ClassChartFragment fragment = ClassChartFragment.newInstance();
        Fragment firstFragment = fragments.get(0);
        fragments.clear();
        fragments.add(firstFragment);
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);
    }

    public int getDetailType() {
        return detailType;
    }

    public void addFragment(Fragment fragment, int pageCount){
        if (pager.getChildCount() > pageCount) {
            pager.setCurrentItem(pager.getCurrentItem()+1,true);
            return;
        }
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(fragments.size()-1,true);
    }
   public void resetMenuItem(int type){
       MenuItem menuItem = getMenuItem();
        if (menuItem == null)
            return;
        switch (type){
            case 0:
                this.actionType = 0;
                menuItem.setIcon(R.mipmap.ic_action_chart);
                menuItem.setVisible(true);
                break;
            case 1:
                this.actionType = 1;
                menuItem.setIcon(R.mipmap.ic_action_book);
                menuItem.setVisible(true);
                break;
            case 2:
                this.actionType = 2;
                menuItem.setIcon(R.mipmap.ic_action_filter);
                menuItem.setVisible(true);
                break;
            default:
                menuItem.setVisible(false);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
