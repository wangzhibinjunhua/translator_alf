package com.jachat.translateor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jachat.translateor.fragment.TranslaterLngFragment;
import com.jachat.translateor.fragment.TranslateFragment;

/**
 * Created by Perryn on 2017/12/20.
 */

public class MainAdapter extends FragmentPagerAdapter {

    int newposition;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        newposition = position % 2;
        if (position == 0) {
            return new TranslaterLngFragment();//TranslateFragment / Translate
        } else if (position == 1) {
            return new TranslateFragment();
        }
//      else if (newposition == 2) {
//            return new CameraActivity();//new FragmentApps()
//      }
        else {
            return null;
        }

    }

    @Override
    public int getCount() {
        //return Integer.MAX_VALUE;
        return 2;
    }


}
