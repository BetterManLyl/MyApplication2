package com.example.lyl.myapplication.tablayout.tablayout_viewpage_fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyl
 * @date 2017/12/8.
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private List<MyFragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<Integer> imageList = new ArrayList<>();
    private Context context;

    public void addFragment(int position) {
        fragmentList.add(BaseLazyFragment._instanceMyFragment(position));
        titles.add("第" + position + "个");
        imageList.add(R.drawable.tab_image_selector);
    }

    public MyFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 返回标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
//        Drawable image = context.getResources().getDrawable(imageList.get(position));
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return null;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(imageList.get(position));
        return view;
    }
}
