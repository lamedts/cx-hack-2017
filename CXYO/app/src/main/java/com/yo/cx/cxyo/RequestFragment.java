package com.yo.cx.cxyo;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RequestFragment extends Fragment {
    List<FrequentRequest> FrequentRequests = new ArrayList<FrequentRequest>();
    ListView FrequentRequestList;
    ImageButton request_back_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);

        FrequentRequestList = (ListView)rootView.findViewById(R.id.frequent_request_list);
        request_back_button = (ImageButton)rootView.findViewById(R.id.request_back_button);

        FrequentRequests.add(new FrequentRequest("Blanket", getActivity().getResources().getIdentifier("blanket", "drawable", getActivity().getPackageName())));
        FrequentRequests.add(new FrequentRequest("Collect food tray", getActivity().getResources().getIdentifier("restaurant_cutlery_circular_symbol_of_a_spoon_and_a_fork_in_a_circle", "drawable", getActivity().getPackageName())));
        FrequentRequests.add(new FrequentRequest("Coffee", getActivity().getResources().getIdentifier("hot_coffee_rounded_cup_on_a_plate_from_side_view", "drawable", getActivity().getPackageName())));
        FrequentRequests.add(new FrequentRequest("Wake up for dinner", getActivity().getResources().getIdentifier("alarm_clock", "drawable", getActivity().getPackageName())));

        ArrayAdapter<FrequentRequest> adapter = new FrequentRequestListAdapter(getContext(),R.layout.list_frequent_request,FrequentRequests);
        FrequentRequestList.setAdapter(adapter);

        request_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.FlightAttendanceLayout, new FlightAttendanceFragment()).commit();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }


    public class FrequentRequestListAdapter extends ArrayAdapter<FrequentRequest> {
        public FrequentRequestListAdapter(Context context, int rootViewResourceId) {
            super(context, rootViewResourceId);
        }

        public FrequentRequestListAdapter(Context context, int rootViewResourceId, List<FrequentRequest> items) {
            super(context, rootViewResourceId, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with
            View itemView = convertView;
            if (itemView == null) {
                itemView =  LayoutInflater.from(getContext()).inflate(R.layout.list_frequent_request, parent, false);

            }
            // Find the location to work with
            FrequentRequest currentFrequentRequest = getItem(position);

            // Fill the view
            TextView name = (TextView) itemView.findViewById(R.id.request);
            name.setText(currentFrequentRequest.getRequest());

            ImageView photo = (ImageView) itemView.findViewById(R.id.request_image);
            photo.setImageResource(currentFrequentRequest.getImage());

            return itemView;
        }

    }

}
