package com.yo.cx.cxyo;

import android.content.Context;
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

public class GameFragment extends Fragment {

    ListView GameInfoList;

    ArrayList GameInfos = new ArrayList<UserInfo>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        GameInfoList = (ListView)rootView.findViewById(R.id.game_info_list);

        GameInfos.add(new UserInfo("Poker", "Use Asia Miles", R.drawable.poker));
        GameInfos.add(new UserInfo("Chess", "Use Asia Miles", R.drawable.chess));
        GameInfos.add(new UserInfo("Sokudo", "Use Asia Miles to get hints", R.drawable.sokudo));

        ArrayAdapter<UserInfo> adapter = new GameInfoListAdapter(getContext(),R.layout.list_user_info,GameInfos);
        GameInfoList.setAdapter(adapter);

        return rootView;
    }

    public class GameInfoListAdapter extends ArrayAdapter<UserInfo> {
        public GameInfoListAdapter(Context context, int rootViewResourceId) {
            super(context, rootViewResourceId);
        }

        public GameInfoListAdapter(Context context, int rootViewResourceId, List<UserInfo> items) {
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
            info.setText(currentUserInfo.getReason());

            TextView reason = (TextView) itemView.findViewById(R.id.user_reason);
            reason.setVisibility(View.GONE);

            ImageView photo = (ImageView) itemView.findViewById(R.id.user_photo);
            photo.setImageResource(currentUserInfo.getImage());

            return itemView;
        }

    }
}
