package com.example.jennifer.prueba3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private OnClickNew onClickNew;

    public HomePagerAdapter(FragmentManager fm,OnClickNew onClickNew) {
        super(fm);
        this.onClickNew = onClickNew;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            //return NewsListFragment.newInstance(onClickNew);
        }else if(position == 1){
            return FavoritesFragment.newInstance(onClickNew);
        }
        return NewsListFragment.newInstance(onClickNew);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
