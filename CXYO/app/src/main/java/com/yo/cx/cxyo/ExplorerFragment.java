package com.yo.cx.cxyo;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ExplorerFragment extends Fragment {

    ListView UserInfoList;

    List<UserInfo> UserInfos = new ArrayList<UserInfo>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_explorer, container, false);

        UserInfoList = (ListView)rootView.findViewById(R.id.user_info_list);

        UserInfos.add(new UserInfo("Mary","F","Same Company",getActivity().getResources().getIdentifier("user_girl", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Sam","M","Live near you",getActivity().getResources().getIdentifier("user_boy_1", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Tom","M","Mutual Friend",getActivity().getResources().getIdentifier("user_boy", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Kelvin","M","Travel tgt before",getActivity().getResources().getIdentifier("user_man_1", "drawable", getActivity().getPackageName())));

        UserInfos.add(new UserInfo("Mary","F","Same Company",getActivity().getResources().getIdentifier("user_girl", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Sam","M","Live near you",getActivity().getResources().getIdentifier("user_boy_1", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Tom","M","Mutual Friend",getActivity().getResources().getIdentifier("user_boy", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Kelvin","M","Travel tgt before",getActivity().getResources().getIdentifier("user_man_1", "drawable", getActivity().getPackageName())));

        UserInfos.add(new UserInfo("Mary","F","Same Company",getActivity().getResources().getIdentifier("user_girl", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Sam","M","Live near you",getActivity().getResources().getIdentifier("user_boy_1", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Tom","M","Mutual Friend",getActivity().getResources().getIdentifier("user_boy", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Kelvin","M","Travel tgt before",getActivity().getResources().getIdentifier("user_man_1", "drawable", getActivity().getPackageName())));

        ArrayAdapter<UserInfo> adapter = new UserInfoListAdapter(getContext(),R.layout.list_user_info,UserInfos);
        UserInfoList.setAdapter(adapter);

        return rootView;
    }

    public class UserInfoListAdapter extends ArrayAdapter<UserInfo> {
        public UserInfoListAdapter(Context context, int rootViewResourceId) {
            super(context, rootViewResourceId);
        }

        public UserInfoListAdapter(Context context, int rootViewResourceId, List<UserInfo> items) {
            super(context, rootViewResourceId, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with
            View itemView = convertView;
            if (itemView == null) {
                itemView =  LayoutInflater.from(getContext()).inflate(R.layout.list_user_info, parent, false);

            }
            // Find the location to work with
            UserInfo currentUserInfo = getItem(position);

            // Fill the view
            TextView name = (TextView) itemView.findViewById(R.id.user_name);
            name.setText(currentUserInfo.getName());

            TextView info = (TextView) itemView.findViewById(R.id.user_info);
            info.setText(currentUserInfo.getInfo());

            TextView reason = (TextView) itemView.findViewById(R.id.user_reason);
            reason.setText(currentUserInfo.getReason());

            ImageView photo = (ImageView) itemView.findViewById(R.id.user_photo);
            photo.setImageResource(currentUserInfo.getImage());

            return itemView;
        }

    }

}
