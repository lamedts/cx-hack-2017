package com.yo.cx.cxyo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NetworkFragment extends Fragment {

    private android.support.design.widget.TabLayout mTabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_network, container, false);

        mTabs = (android.support.design.widget.TabLayout) rootView.findViewById(R.id.tabs);
        mTabs.addTab(mTabs.newTab().setText("Explorer"));
        mTabs.addTab(mTabs.newTab().setText("Recent"));
        mTabs.addTab(mTabs.newTab().setText("Game"));

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.network_content_frame, new ExplorerFragment()).commit();

        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragmentManager.beginTransaction().replace(R.id.network_content_frame, new ExplorerFragment()).commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction().replace(R.id.network_content_frame, new RecentFragment()).commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.network_content_frame, new GameFragment()).commit();
                        break;
                    default:
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
