package com.congress.congressapp.bills;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerBill extends FragmentPagerAdapter {

    //Constructor to the class
    public PagerBill(FragmentManager fm, int tabCount) {
        super(fm);
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                ActiveBillFragment tab1=new ActiveBillFragment();
                return tab1;
            case 1:
                NewBillFragment tab2=new NewBillFragment();
                return tab2;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return 2;
    }
}
