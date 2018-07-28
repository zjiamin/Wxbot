package com.tz.newlife.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tz.newlife.R;
import com.tz.newlife.module.been.Fruit;
import com.tz.newlife.module.ui.activity.ConversationActivity;
import com.tz.newlife.module.ui.activity.FriendsActivity;

import java.util.List;

public class Friends_Recycler_Adapter extends RecyclerView.Adapter<Friends_Recycler_Adapter.ViewHolder> {

    private Context mContext;

    private List<Fruit> friends_list;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llayout;

        ImageView photo;

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            llayout = itemView.findViewById(R.id.llayout_friend);

            photo = itemView.findViewById(R.id.friend_img);

            name = itemView.findViewById(R.id.friend_name);
        }
    }

    public Friends_Recycler_Adapter(List<Fruit> list) {
        this.friends_list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.friends_item,
                parent,false);

        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();

                Fruit friend = friends_list.get(position);

                Intent intent = new Intent(mContext, ConversationActivity.class);

                intent.putExtra(FriendsActivity.FRIEND_DATA, friend);

                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = friends_list.get(position);
        holder.name.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return friends_list.size();
    }

}
