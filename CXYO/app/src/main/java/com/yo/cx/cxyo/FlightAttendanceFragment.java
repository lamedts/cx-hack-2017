package com.yo.cx.cxyo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.support.v4.app.FragmentManager;


public class FlightAttendanceFragment extends Fragment {
    ImageButton RequestButton, ShoppingButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_flight_attendance, container, false);

        RequestButton = (ImageButton)rootView.findViewById(R.id.RequestButton);
        ShoppingButton = (ImageButton)rootView.findViewById(R.id.ShoppingButton);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.request_content_frame, new RequestFragment()).commit();
        DrawableCompat.setTint(RequestButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        DrawableCompat.setTint(ShoppingButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));

        RequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawableCompat.setTint(RequestButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                DrawableCompat.setTint(ShoppingButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.request_content_frame, new RequestFragment()).commit();
            }
        });

        ShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawableCompat.setTint(ShoppingButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                DrawableCompat.setTint(RequestButton.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorDarkGrey));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.request_content_frame, new ShoppingFragment()).commit();
            }
        });

        return rootView;
    }
}
