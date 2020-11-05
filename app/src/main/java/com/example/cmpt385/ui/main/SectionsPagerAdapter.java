/*********************************************************************************
 * SectionsPagerAdapter
 *
 * Description:
 * This class acts as an adapter for the tab function, and controls fragment interactions
 *
 *Team Name: Team 10+10
 * Authors: Amy Campbell
 * Date: October 10 2020
 *
 * Input: none
 * Output: none
 *
 ********************************************************************************/

package com.example.cmpt385.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cmpt385.ImageSearch;
import com.example.cmpt385.R;
import com.example.cmpt385.Tab1;
import com.example.cmpt385.Tab2;
import com.example.cmpt385.Tab3;

/**
 * A FragmentPagerAdapter that returns a fragment corresponding to one of the tabs
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * getItem() controls fragment interactions, and assigns
     * @param position of the item
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Tab1();
                break;
            case 1:
                fragment = new Tab2();
                break;
            case 2:
                fragment = new Tab3();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    //return corresponding tab title
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}