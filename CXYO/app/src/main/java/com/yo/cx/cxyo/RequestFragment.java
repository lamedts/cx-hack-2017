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


public class RequestFragment extends Fragment {
    List<FrequentRequest> FrequentRequests = new ArrayList<FrequentRequest>();
    ListView FrequentRequestList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);

        FrequentRequestList = (ListView)rootView.findViewById(R.id.frequent_request_list);

        FrequentRequests.add(new FrequentRequest("Blanket", getActivity().getResources().getIdentifier("request_blanket", "drawable", getActivity().getPackageName())));
        FrequentRequests.add(new FrequentRequest("Collect food tray", getActivity().getResources().getIdentifier("request_collect_food_tray", "drawable", getActivity().getPackageName())));
        FrequentRequests.add(new FrequentRequest("Coffee", getActivity().getResources().getIdentifier("request_coffee", "drawable", getActivity().getPackageName())));

        ArrayAdapter<FrequentRequest> adapter = new FrequentRequestListAdapter(getContext(),R.layout.list_frequent_request,FrequentRequests);
        FrequentRequestList.setAdapter(adapter);

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
