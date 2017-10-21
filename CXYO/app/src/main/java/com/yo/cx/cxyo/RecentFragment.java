package com.yo.cx.cxyo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RecentFragment extends Fragment {

    ListView UserInfoList;

    List<UserInfo> UserInfos = new ArrayList<UserInfo>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recent, container, false);

        UserInfoList = (ListView)rootView.findViewById(R.id.recent_user_info_list);

        UserInfos.add(new UserInfo("Mary",getActivity().getResources().getIdentifier("user_girl", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Sam",getActivity().getResources().getIdentifier("user_boy_1", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Tom",getActivity().getResources().getIdentifier("user_boy", "drawable", getActivity().getPackageName())));
        UserInfos.add(new UserInfo("Kelvin",getActivity().getResources().getIdentifier("user_man_1", "drawable", getActivity().getPackageName())));

        ArrayAdapter<UserInfo> adapter = new UserInfoListAdapter(getContext(),R.layout.list_user_info,UserInfos);
        UserInfoList.setAdapter(adapter);

        UserInfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserInfo clickedUser = (UserInfo)adapterView.getItemAtPosition(i);

                ChatRoomFragment t = new ChatRoomFragment();

                Bundle bundle = new Bundle();
                bundle.putString("target", clickedUser.getName());

                t.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, t).commit();
            }
        });

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
            info.setVisibility(View.GONE);

            TextView reason = (TextView) itemView.findViewById(R.id.user_reason);
            reason.setVisibility(View.GONE);

            ImageView photo = (ImageView) itemView.findViewById(R.id.user_photo);
            photo.setImageResource(currentUserInfo.getImage());

            return itemView;
        }

    }
}
