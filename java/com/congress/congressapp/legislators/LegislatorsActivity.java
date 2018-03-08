package com.congress.congressapp.legislators;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.congress.congressapp.AboutmeActivity;
import com.congress.congressapp.R;
import com.congress.congressapp.bills.BillsActivity;
import com.congress.congressapp.committee.CommitteeActivity;
import com.congress.congressapp.favorites.FavoritesActivity;

public class LegislatorsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView = null;

    //This is our tablayout
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislators);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(LegislatorsActivity.this.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
        }else{
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Check Your Internet Connection!!");
            adb.setCancelable(true);
            adb.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                } });
            adb.show();
        }

        //Show status bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Legislators");


        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("BY STATES"));
        tabLayout.addTab(tabLayout.newTab().setText("HOUSE"));
        tabLayout.addTab(tabLayout.newTab().setText("SENATE"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        PagerLegislators adapter = new PagerLegislators(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.item1) {

            Intent in1 = new Intent(getApplicationContext(), LegislatorsActivity.class);
            LegislatorsActivity.this.startActivity(in1);
        }
        if (id == R.id.item2) {

            Intent in1 = new Intent(getApplicationContext(), BillsActivity.class);
            LegislatorsActivity.this.startActivity(in1);
        }
        if (id == R.id.item3) {

            Intent in1 = new Intent(getApplicationContext(), CommitteeActivity.class);
            LegislatorsActivity.this.startActivity(in1);
        }
        if (id == R.id.item4) {

            Intent in1 = new Intent(getApplicationContext(), FavoritesActivity.class);
            LegislatorsActivity.this.startActivity(in1);
        }
        if (id == R.id.aboutus) {

            Intent in1 = new Intent(getApplicationContext(), AboutmeActivity.class);
            LegislatorsActivity.this.startActivity(in1);
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void exitAppMethod()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    //for exit app
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.logout)
                .setTitle("Close Application")
                .setMessage("You want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exitAppMethod();
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
