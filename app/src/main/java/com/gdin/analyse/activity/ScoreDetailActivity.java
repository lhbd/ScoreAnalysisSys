package com.gdin.analyse.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.adapter.ScoreDetailAdapter;
import com.gdin.analyse.entity.CourseScoreDetailEntity;
import com.gdin.analyse.present.ScoreDetailPresent;
import com.gdin.analyse.tools.OnRecyclerItemClickListener;
import com.gdin.analyse.view.ScoreDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreDetailActivity extends BaseAppCompatActivity implements ScoreDetailView {


    ScoreDetailPresent present;
    @BindView(R.id.s_score_detail)
    RecyclerView sScoreDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation(BaseAppCompatActivity.SLIDE);
        getToolbarTitle().setText(R.string.show_all_score);
        initWidget();
    }

    public void initWidget() {
        present = new ScoreDetailPresent(this);
        final ScoreDetailAdapter adapter = new ScoreDetailAdapter(present.getData());
        adapter.openLoadAnimation();
        sScoreDetail.setLayoutManager(new LinearLayoutManager(this));
        sScoreDetail.setAdapter(adapter);
        sScoreDetail.addOnItemTouchListener(new OnRecyclerItemClickListener(sScoreDetail) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                if (holder.getItemViewType()!=ScoreDetailAdapter.COURSE_SCORE)
                {
                    Intent i = new Intent(ScoreDetailActivity.this,HelloChartsDemo.class);
                    startActivity(i);
                }else{
                    int pos = holder.getAdapterPosition();
                    ImageButton drop = ((BaseViewHolder)holder).getView(R.id.course_score_drop);
                    if (((CourseScoreDetailEntity)adapter.getItem(pos)).isExpanded()) {
                        rotation(adapter,pos,drop,true);
                        adapter.collapse(pos);
                    } else {
                        rotation(adapter,pos,drop,false);
                        adapter.expand(pos);
                    }
                }

            }
        });
    }

    private  void rotation(final ScoreDetailAdapter adapter, final int pos, ImageButton drop, final boolean isExpanded){
        ObjectAnimator animator;
        if (isExpanded){        //展开，则图片需要转动朝上
           animator = ObjectAnimator.ofFloat(drop, "rotationX", 180.0f, 0.0f);
            animator.setDuration(200).start();
        }else {
            animator = ObjectAnimator.ofFloat(drop, "rotationX", 0.0f, 180.0f);
            animator.setDuration(200).start();
        }
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isExpanded){
                    adapter.collapse(pos);
                }else {
                    adapter.expand(pos);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.student_score_detail;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }
}
