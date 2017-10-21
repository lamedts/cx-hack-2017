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
import android.widget.Spinner;


public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Spinner purpose_spinner = (Spinner) view.findViewById(R.id.layout_purpose_spinner);
        ArrayAdapter<CharSequence> purpose_adapter = ArrayAdapter.createFromResource(getContext(), R.array.purpose_array, android.R.layout.simple_spinner_item);
        purpose_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purpose_spinner.setAdapter(purpose_adapter);

        Spinner meeting_spinner = (Spinner) view.findViewById(R.id.layout_meeting_spinner);
        ArrayAdapter<CharSequence> meet_adapter = ArrayAdapter.createFromResource(getContext(), R.array.meeting_array, android.R.layout.simple_spinner_item);
        meet_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meeting_spinner.setAdapter(meet_adapter);

        return view;
    }
}
