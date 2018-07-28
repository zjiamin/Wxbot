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
import com.tz.newlife.module.been.News;
import com.tz.newlife.module.ui.activity.NewsDetailsActivity;
import com.tz.newlife.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class News_Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_IMAGE = 0;

    private static final int TYPE_GROUP = 1;

    private Context mContext;

    private List<News> newsList = new ArrayList<>();

    public News_Recycler_Adapter(List<News> list, Context context) {
        this.newsList = list;
        this.mContext = context;
    }

    private boolean isTitle(int pos){
        if(pos == 0) {
            return true;
        }
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }

        LayoutInflater mInflater = LayoutInflater.from(mContext);

        switch ( viewType ) {
            case TYPE_IMAGE:
                ViewGroup vImage = ( ViewGroup ) mInflater.inflate ( R.layout.news_item, parent, false );
                final News_Recycler_Adapter.ImageViewHolder vhImage = new News_Recycler_Adapter.ImageViewHolder( vImage );

                vhImage.llayout_news.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        News news = newsList.get(vhImage.getAdapterPosition());
                        Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                        intent.putExtra("news_data", news);
                        mContext.startActivity(intent);
                    }
                });
                return vhImage;
            case TYPE_GROUP:
                ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.news_header, parent, false );
                News_Recycler_Adapter.GroupViewHolder vhGroup = new News_Recycler_Adapter.GroupViewHolder( vGroup );

                vhGroup.mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        News news = newsList.get(position);
                        Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                        intent.putExtra("news_data", news);
                        mContext.startActivity(intent);
                    }
                });

                return vhGroup;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case TYPE_IMAGE:
                News_Recycler_Adapter.ImageViewHolder imageViewHolder = (News_Recycler_Adapter.ImageViewHolder) holder;

                News news = newsList.get(position);
                imageViewHolder.title.setText(news.getTitle());
                imageViewHolder.content.setText(news.getContent());
                Glide.with(mContext).load(news.getPic()).into(imageViewHolder.pic);
                imageViewHolder.comments.setText(news.getConments());

                break;

            case TYPE_GROUP:

                News_Recycler_Adapter.GroupViewHolder groupViewHolder = (News_Recycler_Adapter.GroupViewHolder) holder;
                groupViewHolder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                //设置图片加载器
                groupViewHolder.mBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                List<Integer> images = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    images.add(newsList.get(i).getPic());
                }
                groupViewHolder.mBanner.setImages(images);
                //设置动画效果
//                groupViewHolder.mTitle.setBannerAnimation(Transformer.DepthPage);
                groupViewHolder.mBanner.setBannerAnimation(Transformer.Default);
                //设置轮播时间
                groupViewHolder.mBanner.setDelayTime(4000);
                //设置自动轮播，默认为true
                groupViewHolder.mBanner.isAutoPlay(true);
                //设置指示器位置（当banner模式中有指示器时）
                groupViewHolder.mBanner.setIndicatorGravity(BannerConfig.CENTER);
                //设置标题集合（当banner样式有显示title时）
                List<String> banner_titles = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    banner_titles.add(newsList.get(i).getTitle());
                }
                groupViewHolder.mBanner.setBannerTitles(banner_titles);

                //banner设置方法全部调用完毕时最后调用
                groupViewHolder.mBanner.start();

                break;
        }
    }

    @Override
    public int getItemViewType ( int position ) {
        int viewType;
        if (!isTitle(position) ) {
            viewType = TYPE_IMAGE;
        } else {
            viewType = TYPE_GROUP;
        }
        return viewType;
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        Banner mBanner;
        public GroupViewHolder ( View itemView ) {
            super(itemView);
            mBanner= (Banner) itemView.findViewById(R.id.banner);
        }

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llayout_news;

        ImageView pic;

        TextView title;

        TextView content;

        TextView comments;

        public ImageViewHolder(View itemView) {
            super(itemView);

            llayout_news = itemView.findViewById(R.id.llayout_news);

            pic = itemView.findViewById(R.id.news_pic);

            title = itemView.findViewById(R.id.news_title);

            content = itemView.findViewById(R.id.news_content);

            comments = itemView.findViewById(R.id.news_comment);

        }
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
