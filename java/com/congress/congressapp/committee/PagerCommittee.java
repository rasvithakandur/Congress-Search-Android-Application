package com.congress.congressapp.committee;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerCommittee extends FragmentPagerAdapter {

    //Constructor to the class
    public PagerCommittee(FragmentManager fm, int tabCount) {
        super(fm);

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                HouseCFragment tab1=new HouseCFragment();
                return tab1;
            case 1:
                SenateCFragment tab2=new SenateCFragment();
                return tab2;
            case 2:
                JointCFragment tab3=new JointCFragment();
                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return 3;
    }
}
