package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gdin.analyse.R;
import com.gdin.analyse.adapter.ScoreAdapter;
import com.gdin.analyse.present.TPresent;
import com.gdin.analyse.tools.OnRecyclerItemClickListener;
import com.gdin.analyse.view.TMainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TMainActivity extends BaseAppCompatActivity implements TMainView {

    private long mPressedTime = 0;
    private TPresent tPresent;

    @BindView(R.id.show_score)
    RecyclerView tMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation(BaseAppCompatActivity.EXPLODE);
        getToolbarTitle().setText(R.string.show_all_score);
        initWidget();
    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.t_mainview_layout;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    public void initWidget() {
        tPresent = new TPresent(this);
        ScoreAdapter adapter = new ScoreAdapter(tPresent.initData());
        adapter.openLoadAnimation();
        tMain.setLayoutManager(new LinearLayoutManager(this));
        tMain.setAdapter(adapter);
        tMain.addOnItemTouchListener(new OnRecyclerItemClickListener(tMain) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                startActivity(TMainActivity.this,ScoreDetailActivity.class);
            }
        });
    }

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        }
        else{//退出程序
            this.finish();
            System.exit(0);
        }
    }
}
