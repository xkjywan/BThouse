package com.bthouse.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.bthouse.ui.fragment.HouseFragment;
import java.util.List;

public class HouseResultAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;
    private Context context;

    public HouseResultAdapter(FragmentManager fm, List<String> Titles, Context context) {
        super(fm);
        this.mTitles = Titles;
        this.context = context;
}

    @Override
    public Fragment getItem(int position) {
            return new HouseFragment(context);
    }

    @Override
    public int getCount() {
        return mTitles.size()  ;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}
