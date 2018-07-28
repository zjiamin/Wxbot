package com.tz.newlife.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.tz.newlife.R;
import com.tz.newlife.module.ui.fragment.FriendsFragment;
import com.tz.newlife.module.ui.fragment.FruitsFragment;
import com.tz.newlife.module.ui.fragment.HotTopicsFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();

    public final int PAGE_COUNT = 3;

    private int[] imageResId = {
            /*自定义TabLayout可以用写好的drawable/selector
            * 来做出选中效果和默认效果*/

            R.drawable.tab_item_selector_0,
            R.drawable.tab_item_selector_1,
            R.drawable.tab_item_selector_2};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(new HotTopicsFragment());
        mFragments.add(new FruitsFragment());
        mFragments.add(new FriendsFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public View getTabView(int position, Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        ImageView tab_imgv = view.findViewById(R.id.tab_item_imgv);
        tab_imgv.setImageResource(imageResId[position]);
        return view;
    }
}
