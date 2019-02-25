package com.example.zhihu_daily.MyAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihu_daily.R;
import com.example.zhihu_daily.datas.DataMain;

import java.util.ArrayList;
import java.util.List;

public class RecycerAdapter_main extends RecyclerView.Adapter <RecycerAdapter_main.ViewHolder> implements View.OnClickListener{

    private List<DataMain> datas = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public RecycerAdapter_main(List<DataMain> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTvTitle;
        ImageView mIvTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvTitle = itemView.findViewById(R.id.im_title);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_rec_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.mTvTitle.setText(datas.get(i).getTitle());
        Glide.with(mContext).load(datas.get(i).getImageUrl()).into(viewHolder.mIvTitle);
        if(null!=mOnItemClickListener){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public interface  OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v,((int) v.getTag()));
    }

}
