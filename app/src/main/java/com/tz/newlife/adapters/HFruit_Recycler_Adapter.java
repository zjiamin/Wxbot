package com.tz.newlife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tz.newlife.R;
import com.tz.newlife.module.been.Fruit;

import java.util.ArrayList;
import java.util.List;

public class HFruit_Recycler_Adapter extends RecyclerView.Adapter<HFruit_Recycler_Adapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mfruitList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView himgv;
        TextView htv;

        public ViewHolder(View itemView) {
            super(itemView);
            himgv = itemView.findViewById(R.id.hfruit_img);
            htv = itemView.findViewById(R.id.hfruit_tv);
        }
    }

    public HFruit_Recycler_Adapter(List<Fruit> mfruitList) {
        this.mfruitList = mfruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.horizontal_fruit_item,
                parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.himgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = mfruitList.get(position);
                Toast.makeText(mContext,
                        "U Clicked " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mfruitList.get(position);
        holder.htv.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.himgv);
    }

    @Override
    public int getItemCount() {
        return mfruitList.size();
    }
}
