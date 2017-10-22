package com.yo.cx.cxyo;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class ForumFragment extends Fragment {

    ImageButton BackButton, AttractionRating, ForumPost1, ForumPost2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);

        BackButton = (ImageButton)rootView.findViewById(R.id.forum_back_button);
        AttractionRating = (ImageButton)rootView.findViewById(R.id.AttractionsRating);
        ForumPost1 = (ImageButton)rootView.findViewById(R.id.ForumPost1);
        ForumPost2 = (ImageButton)rootView.findViewById(R.id.ForumPost2);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DiscussFragment()).commit();
            }
        });

        AttractionRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttractionRating.setImageResource(R.drawable.attractions_rating_after);
            }
        });

        return rootView;
    }
}
