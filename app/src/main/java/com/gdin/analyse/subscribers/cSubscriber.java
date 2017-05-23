package com.gdin.analyse.subscribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gdin.analyse.progress.ProgressCancelListener;
import com.gdin.analyse.progress.ProgressDialogHandler;
import com.gdin.analyse.tools.CustomApplication;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by liukun on 16/3/10.
 */
public abstract class cSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public cSubscriber() {
        this.context = CustomApplication.getContext();
//        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }
//
//    private void showProgressDialog(){
//        if (mProgressDialogHandler != null) {
//            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
//        }
//    }
//
//    private void dismissProgressDialog(){
//        if (mProgressDialogHandler != null) {
//            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
//            mProgressDialogHandler = null;
//        }
//    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
//        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
//        dismissProgressDialog();
        onComplete();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "连接超时，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(context, "error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Analyse",e.getMessage());
//            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
//        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        onNext(t,0);
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    public abstract void onComplete();
    public abstract void onNext(T t,int i);

}