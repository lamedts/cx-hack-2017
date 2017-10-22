package com.yo.cx.cxyo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ConnectFragment extends Fragment {

    ImageButton DiscussButton, ProfileButton, NetworkButton;


    public ConnectFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_connect, container, false);

        DiscussButton = (ImageButton)rootView.findViewById(R.id.DiscussButton);
        ProfileButton = (ImageButton)rootView.findViewById(R.id.ProfileButton);
        NetworkButton = (ImageButton)rootView.findViewById(R.id.NetworkButton);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DiscussFragment()).commit();
        DrawableCompat.setTint(DiscussButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        DrawableCompat.setTint(NetworkButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
        DrawableCompat.setTint(ProfileButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));

        DiscussButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawableCompat.setTint(DiscussButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                DrawableCompat.setTint(NetworkButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                DrawableCompat.setTint(ProfileButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new DiscussFragment()).commit();
            }
        });

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawableCompat.setTint(DiscussButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                DrawableCompat.setTint(NetworkButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                DrawableCompat.setTint(ProfileButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();
            }
        });

        NetworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawableCompat.setTint(DiscussButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                DrawableCompat.setTint(NetworkButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                DrawableCompat.setTint(ProfileButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new NetworkFragment()).commit();
            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_connect, container, false);
        return rootView;
    }

}
