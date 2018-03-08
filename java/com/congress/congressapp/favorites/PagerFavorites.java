package com.congress.congressapp.favorites;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerFavorites extends FragmentPagerAdapter {

    //Constructor to the class
    public PagerFavorites(FragmentManager fm, int tabCount) {
        super(fm);
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                LegislatorFavFragment tab1=new LegislatorFavFragment();
                return tab1;
            case 1:
                BillsFavFragment tab2=new BillsFavFragment();
                return tab2;
            case 2:
                CommitteesFavFragment tab3=new CommitteesFavFragment();
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
