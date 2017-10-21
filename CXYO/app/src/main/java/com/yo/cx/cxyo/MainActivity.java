package com.yo.cx.cxyo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import aero.panasonic.inflight.services.InFlight;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ConnectFragment Connectfragment = new ConnectFragment();
    private FlightInfoFragment FlightInfofragment = new FlightInfoFragment();
    private FlightAttendanceFragment FlightAttendancefragment = new FlightAttendanceFragment();

    private BottomNavigationView navigation;
    private ViewPager viewPager;

    private InFlight mInFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.addOnPageChangeListener(this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_flight_info);

        navigation.setBackgroundColor(Color.parseColor("#1f504b"));

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return Connectfragment;
                    case 1:
                        return FlightInfofragment;
                    case 2:
                        return FlightAttendancefragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        viewPager.setCurrentItem(1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            viewPager.setCurrentItem(item.getOrder());
            return true;
        }

    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navigation.getMenu().getItem(position).setChecked(true);
    }

    public InFlight getIFE() {
        mInFlight = new InFlight();
        // mInFlight.setAppId(this, "c4ae8d6b48299d9a5fb607a4086faa15b4f433ff79a26685abe61268817d~1p8c2fb02f59f01c30e7af4cac36c4cf3f1");
        // ed app key
        mInFlight.setAppId(this, "c0a147fb1f3f7dfda334c9b7d8cd6d8a5482c1da17e5cb810f73a12d3525~0p7796dbabde190bfc894acb24b15098197");
        return mInFlight;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
