package com.wangyeming.chatrobot.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.wangyeming.chatrobot.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wang
 * @data 2015/3/15
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    private List<Map<String, Object>> conversationDisplay = new ArrayList<>();
    private LayoutInflater mInflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View left_layout; //左布局
        public View right_layout; //右布局
        public CircularImageView left_imageView;  //左头像
        public CircularImageView right_imageView;  //右头像
        public TextView left_tv1;  //左聊天内容
        public TextView right_tv1;  //右聊天内容
        public TextView left_tv2;  //左日期
        public TextView right_tv2;  //左日期

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public ConversationAdapter(Context context, List<Map<String, Object>> conversationDisplay ) {
        mInflater = LayoutInflater.from(context);
        this.conversationDisplay = conversationDisplay;
    }

    @Override
    public ConversationAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_conversation,
                viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        vh.left_layout = view.findViewById(R.id.left_message);
        vh.right_layout = view.findViewById(R.id.right_message);
        vh.left_imageView = (CircularImageView) view.findViewById(R.id.left_profile_image);
        vh.right_imageView = (CircularImageView) view.findViewById(R.id.right_profile_image);
        vh.left_tv1 = (TextView) view.findViewById(R.id.left_dialog);
        vh.right_tv1 = (TextView) view.findViewById(R.id.right_dialog);
        vh.left_tv2 = (TextView) view.findViewById(R.id.left_date);
        vh.right_tv2 = (TextView) view.findViewById(R.id.right_date);
        return vh;
    }

    @Override
    public void onBindViewHolder(ConversationAdapter.ViewHolder vh, int i) {
        Boolean isRobot = (Boolean) conversationDisplay.get(i).get("isRobot");
        Log.d("wym", "isRobot " + isRobot);
        String message = (String) conversationDisplay.get(i).get("message");
        String date = (String) conversationDisplay.get(i).get("date");
        int avatar = (int) conversationDisplay.get(i).get("avatar");
        if(isRobot) {
            vh.left_layout.setVisibility(View.VISIBLE);
            vh.right_layout.setVisibility(View.GONE);
            vh.left_tv1.setText(message);
            vh.left_tv2.setText(date);
            vh.left_imageView.setImageResource(avatar);
        } else {
            vh.left_layout.setVisibility(View.GONE);
            vh.right_layout.setVisibility(View.VISIBLE);
            vh.right_tv1.setText(message);
            vh.right_tv2.setText(date);
            vh.right_imageView.setImageResource(avatar);
        }
    }

    @Override
    public int getItemCount() {
        return conversationDisplay.size();
    }
}