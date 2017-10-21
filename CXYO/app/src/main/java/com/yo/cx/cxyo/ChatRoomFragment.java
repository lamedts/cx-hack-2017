package com.yo.cx.cxyo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class ChatRoomFragment extends Fragment {

    ImageButton BackButton;
    TextView TargetName;

    ListView Messenges;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat_room, container, false);

        BackButton = (ImageButton)rootView.findViewById(R.id.back_button);
        TargetName = (TextView)rootView.findViewById(R.id.target_name);

        TargetName.setText(getArguments().getString("target"));

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkFragment t = new NetworkFragment();

                Bundle bundle = new Bundle();
                bundle.putString("from", "chatroom");

                t.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, t).commit();
            }
        });


        return rootView;
    }



}
