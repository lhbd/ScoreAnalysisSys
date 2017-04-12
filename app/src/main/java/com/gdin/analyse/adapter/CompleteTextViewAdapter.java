package com.gdin.analyse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.gdin.analyse.R;
import com.gdin.analyse.tools.CustomApplication;

import java.util.ArrayList;
import java.util.List;

public class CompleteTextViewAdapter<T> extends BaseAdapter implements Filterable {

    private Context context;
    private List<T> mOriginalValues;
    private List<T> data;
    private final Object mLock = new Object();
    private MyFilter fiter = null;

    public CompleteTextViewAdapter(List<T> data)
    {
        //防止OOM
        this.context = CustomApplication.getContext();
        this.data = data;
        fiter = new MyFilter();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getViewFromResource(position,convertView,parent);
    }
    private View getViewFromResource(int position, View convertView,
                                    ViewGroup parent) {
        if(convertView == null)
        {
           convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item_layout,parent,false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.spinner_item);
        T item = getItem(position);
        tv.setText(item.toString());
        View view = convertView.findViewById(R.id.line);
        if (position<data.size()-1){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return fiter;
    }
    //自定义过滤器
    private class MyFilter extends Filter
    {
        //得到过滤结果
        @Override
        protected FilterResults performFiltering(CharSequence element) {
            FilterResults results = new FilterResults();
            if(mOriginalValues == null)
            {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<T>(data);
                }
            }

            int count = mOriginalValues.size();
            ArrayList<T> values = new ArrayList<T>();
            for(int i = 0;i < count;i++)
            {
                T value = mOriginalValues.get(i);
                String valueText = value.toString();
                //自定义匹配规则
                if(valueText != null && element != null && hasResult(element.toString(),valueText))
                {
                    values.add(value);
                }
            }
            results.values = values;
            results.count = values.size();
            return results;
        }

        private boolean hasResult(String element,String valueText){
            int length = element.length();
            if (length==1 && valueText.contains(element)){
                return true;
            }else if (length>1){
                String arr[] = element.split("");
                for (String str : arr){
                    if (!valueText.contains(str)){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        //发布过滤结果
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence element,
                                      FilterResults results) {
            //把搜索结果赋值给data,这样每次输入字符串的时候就不必从所有的字符串中查找，从而提高了效率
            data = (List<T>)results.values;
            if(results.count > 0)
            {
                notifyDataSetChanged();
            }
            else
            {
                notifyDataSetInvalidated();
            }
        }

    }

}
