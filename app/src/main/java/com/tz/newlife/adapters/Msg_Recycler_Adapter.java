package com.tz.newlife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tz.newlife.R;
import com.tz.newlife.module.been.Msg;

import java.util.ArrayList;
import java.util.List;

public class Msg_Recycler_Adapter extends RecyclerView.Adapter<Msg_Recycler_Adapter.ViewHolder> {

    private Context mContext;

    private List<Msg> msgList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout left_Layout;

        LinearLayout right_Layout;

        TextView left_msg;

        TextView right_msg;

        public ViewHolder(View itemView) {
            super(itemView);
            left_Layout = itemView.findViewById(R.id.left_linearLayout);

            right_Layout = itemView.findViewById(R.id.right_linearLayout);

            left_msg = itemView.findViewById(R.id.left_msg);

            right_msg = itemView.findViewById(R.id.right_msg);
        }
    }

    public Msg_Recycler_Adapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.msg_item,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (msg.getType() == Msg.TYPE_RECIEVED) {
            //收到的消息显示左边的布局
            holder.left_Layout.setVisibility(View.VISIBLE);
            holder.right_Layout.setVisibility(View.GONE);
            holder.left_msg.setText(msg.getContent());
        } else if(msg.getType() == Msg.TYPE_SENT) {
            //发出的消息显示右边的布局
            holder.right_Layout.setVisibility(View.VISIBLE);
            holder.left_Layout.setVisibility(View.GONE);
            holder.right_msg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

}
