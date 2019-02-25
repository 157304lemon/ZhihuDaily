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
import com.example.zhihu_daily.datas.DataComments;
import java.util.ArrayList;
import java.util.List;

public class RVAdapterComments extends RecyclerView.Adapter<RVAdapterComments.ViewHolder> {

    private List<DataComments> dataComments = new ArrayList<>();
    private List<DataComments> replyComments = new ArrayList<>();
    private Context mContext;

    public RVAdapterComments(List<DataComments> dataComments,List<DataComments> replyComments, Context mContext) {
        this.dataComments = dataComments;
        this.mContext = mContext;
        this.replyComments = replyComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comments_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(dataComments.get(i).getAvatarUrl()).into(viewHolder.mImAvatar);
        viewHolder.mTvauthorName.setText(dataComments.get(i).getAuthorName());
        viewHolder.mTvLikes.setText(dataComments.get(i).getLikes());
        viewHolder.mTvTime.setText(dataComments.get(i).getTime());
        //评论内容设置，其中包含回复的评论
        String reply = null;
        if(((replyComments.get(i) != null))&&(!replyComments.get(i).getReplyAuthorName().equals(""))) {
                reply = dataComments.get(i).getComment() + "\n//" + replyComments.get(i).getReplyAuthorName() + ":"
                        + replyComments.get(i).getReplyContent();
            } else {
                reply = dataComments.get(i).getComment();
            }
        viewHolder.mTvComment.setText(reply);
    }

    @Override
    public int getItemCount() {
        return dataComments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImAvatar;
        TextView mTvauthorName;
        TextView mTvComment;
        TextView mTvTime;
        TextView mTvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImAvatar = itemView.findViewById(R.id.im_comment_avatar);
            mTvauthorName = itemView.findViewById(R.id.tv_comment_author_name);
            mTvComment = itemView.findViewById(R.id.tv_comments);
            mTvLikes = itemView.findViewById(R.id.tv_comment_likes);
            mTvTime = itemView.findViewById(R.id.tv_comment_time);
        }
    }

}
