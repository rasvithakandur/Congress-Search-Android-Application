package com.congress.congressapp.legislators;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerLegislators extends FragmentPagerAdapter {

    //Constructor to the class
    public PagerLegislators(FragmentManager fm, int tabCount) {
        super(fm);
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
               ByStateLFragment tab1=new ByStateLFragment();
                return tab1;
            case 1:
               HouseLFragment tab2=new HouseLFragment();
                return tab2;
            case 2:
                SenateLFragment tab3=new SenateLFragment();
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
