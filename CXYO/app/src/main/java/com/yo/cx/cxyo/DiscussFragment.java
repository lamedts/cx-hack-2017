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


public class DiscussFragment extends Fragment {

    ImageButton InFlightMeal, LongTermTopic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_discuss, container, false);

        InFlightMeal = (ImageButton)rootView.findViewById(R.id.InFlightMeal);
        LongTermTopic = (ImageButton)rootView.findViewById(R.id.LongTermTopic);

        InFlightMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InFlightMeal.setImageResource(R.drawable.rating_after);
            }
        });

        LongTermTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ForumFragment()).commit();
            }
        });

        return rootView;
    }
}
