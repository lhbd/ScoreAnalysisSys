package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.QuickFragmentPageAdapter;
import com.gdin.analyse.fragment.ClassChartDetailFragment;
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

    private int actionType = 0;
    private int detailType = -1;
    private int sChartType = -1;
    private int stuNum = -1;
    private long exitTime = 0;
    private float[] subjectsValues;
    private int limitNum=-1;
    private String orderBy="";
    private String sortBy="";
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
        pager.setCurrentItem(pager.getCurrentItem() - 1, true);
    }

    @Override
    protected void isShowMenuItem() {

    }

    @Override
    protected void startAction() {
        if (actionType == 0) {
            actionType = 1;
            resetChartFragment();
        } else if (actionType == 1) {
            actionType = 0;
            resetTextFragment();
        } else {
            showPopupWindow();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("");
        init();
    }

    private void init() {
        present = new ClassResultPresent(this);
        ClassResultFragment textFragment = ClassResultFragment.newInstance();
        fragments.add(textFragment);
        adapter = new QuickFragmentPageAdapter<>(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.class_contain);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new DepthPageTransformer());
    }

    private void showPopupWindow() {
        // 一个自定义的布局，作为显示的内容
        View contentView = View.inflate(getApplicationContext(), R.layout.student_roll_pop_layout, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getDrawable(R.drawable.bg_rv_item));
        // 设置好参数之后再show
        popupWindow.showAsDropDown(getToolbar());
        final TextView ok = (TextView)contentView.findViewById(R.id.pop_ok);
        final RadioGroup rankRg = (RadioGroup)contentView.findViewById(R.id.rank_rg);
        final RadioGroup scoreRg = (RadioGroup)contentView.findViewById(R.id.score_rg);
        final RadioGroup sortRg = (RadioGroup)contentView.findViewById(R.id.sort_rg);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<rankRg.getChildCount();i++){
                    RadioButton radioButton = (RadioButton)rankRg.getChildAt(i);
                    if(radioButton.isChecked()){
                        setLimitNum(radioButton.getText()+"");
                    }
                }
                for (int i=0;i<scoreRg.getChildCount();i++){
                    RadioButton radioButton = (RadioButton)scoreRg.getChildAt(i);
                    if(radioButton.isChecked()){
                        setOrderBy(radioButton.getText()+"");
                    }
                }
                for (int i=0;i<sortRg.getChildCount();i++){
                    RadioButton radioButton = (RadioButton)sortRg.getChildAt(i);
                    if(radioButton.isChecked()){
                        setSortBy(radioButton.getText()+"");
                    }
                }
                pager.setCurrentItem(pager.getCurrentItem() - 1, true);
                updateRollFragment(limitNum,orderBy,sortBy);
                popupWindow.dismiss();
            }
        });
    }

    private void setLimitNum(String str){
        if (str.equals("年级前10") || str.equals("班级前10")){
            limitNum = 10;
        } else if (str.equals("年级前50") || str.equals("班级前20")){
            limitNum = 20;
        } else {
            limitNum = 100;
        }
    }
    private void setOrderBy(String str){
        //orderBy 按什么排序 可选：ch_rank  math_rank en_rank sum_rank（总分） stu_num(学号)
        if (str.equals("总分") ){
            orderBy = "sum_rank";
        }
        if (str.equals("语文") ){
            orderBy = "ch_rank";
        }
        if (str.equals("数学") ){
            orderBy = "math_rank";
        }
        if (str.equals("英语") ){
            orderBy = "en_rank";
        }
    }
    private void setSortBy(String str){
        if (str.equals("升序") ){
            sortBy = "asc";
        }
        if (str.equals("降序") ){
            sortBy = "desc";

        }
        if (str.equals("按学号升序") ){
            sortBy = "asc";
            orderBy = "stu_num";
        }
        if (str.equals("按学号降序") ){
            sortBy = "desc";
            orderBy = "stu_num";
        }
    }

    public void resetChartFragment() {
        ClassResultFragment resultFragment = (ClassResultFragment) fragments.get(0);
        ClassChartDetailFragment fragment = ClassChartDetailFragment.newInstance(resultFragment.getSubjectValues());
        adapter.removeFlags(fragments.size());
        adapter.notifyDataSetChanged();
        fragments.clear();
        fragments.add(fragment);
        adapter.updateFlags();
        adapter.notifyDataSetChanged();
    }

    public void resetTextFragment() {

        ClassResultFragment fragment = ClassResultFragment.newInstance();
        adapter.removeFlags(fragments.size());
        adapter.notifyDataSetChanged();
        fragments.clear();
        fragments.add(fragment);
        adapter.updateFlags();
        adapter.notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment, int pageCount) {
        if (pager.getChildCount() > pageCount) {
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            return;
        }
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem() + 1, true);

    }

    public void updateStudentChartFragment(int sChartType) {
        if (this.sChartType == sChartType) {
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            return;
        }
        this.sChartType = sChartType;
        StudentScoreDetailFragment detailFragment = (StudentScoreDetailFragment) fragments.get(pager.getCurrentItem());
        updateSubjectValues(detailFragment, sChartType);
        StudentScoreChartFragment fragment = StudentScoreChartFragment.newInstance();
        if (fragment == null)
            return;
        if (fragments.size() == 4) {
            fragments.add(fragment);
        } else {
            List<Fragment> curFragments = new ArrayList<>();
            for (int i = 0; i < pager.getCurrentItem() + 1; i++) {
                curFragments.add(fragments.get(i));
            }
            fragments.clear();
            fragments.addAll(curFragments);
            fragments.add(fragment);
        }
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
    }

    public void updateStudentScoreFragment(int stuNum) {
        if (this.stuNum == stuNum) {
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            return;
        }
        this.stuNum = stuNum;
        StudentScoreDetailFragment fragment = StudentScoreDetailFragment.newInstance(stuNum);
        if (fragments.size() == 3) {
            fragments.add(fragment);
        } else {
            List<Fragment> curFragments = new ArrayList<>();
            for (int i = 0; i < pager.getCurrentItem() + 1; i++) {
                curFragments.add(fragments.get(i));
            }
            fragments.clear();
            fragments.addAll(curFragments);
            fragments.add(fragment);
        }
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
    }

    public void updateFragment(int type) {
        if (type == detailType) {
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            return;
        }
        detailType = type;
        ClassChartFragment fragment = ClassChartFragment.newInstance();
        Fragment firstFragment = fragments.get(0);
        fragments.clear();
        fragments.add(firstFragment);
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem() + 1, true);

    }

    public  void updateRollFragment(int limitNum,String orderBy,String sortBy){
        if (this.orderBy.equals(orderBy) && this.sortBy.equals(sortBy)&& this.limitNum == limitNum){
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            return;
        }
        this.orderBy = orderBy;
        this.sortBy = sortBy;
        this.limitNum = limitNum;

        StudentRollFragment fragment = StudentRollFragment.newInstance();

        if (fragments.size() == 2) {
            fragments.add(fragment);
        } else {
            List<Fragment> curFragments = new ArrayList<>();
            for (int i = 0; i < pager.getCurrentItem() + 1; i++) {
                curFragments.add(fragments.get(i));
            }
            fragments.clear();
            fragments.addAll(curFragments);
            fragments.add(fragment);
        }
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
    }

    private void updateSubjectValues(StudentScoreDetailFragment detailFragment, int sChartType) {
        switch (sChartType) {
            case 0:
                this.subjectsValues = detailFragment.getScoreValues();
                break;
            case 1:
                this.subjectsValues = detailFragment.getgRankValues();
                break;
            case 2:
                this.subjectsValues = detailFragment.getcRankValues();
                break;
            default:
                this.subjectsValues = detailFragment.getcRankValues();
                break;
        }
    }

    public int getStuNum() {
        return stuNum;
    }

    public int getDetailType() {
        return detailType;
    }

    public int getsChartType() {
        return sChartType;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public float[] getSubjectsValues() {
        return subjectsValues;
    }

    public void resetMenuItem(int type) {
        MenuItem menuItem = getMenuItem();
        if (menuItem == null)
            return;
        switch (type) {
            case 0:
                this.actionType = 0;
                menuItem.setIcon(R.mipmap.ic_action_chart);
                menuItem.setVisible(true);
                break;
            case 1:
                this.actionType = 1;
                this.detailType = -1;
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
