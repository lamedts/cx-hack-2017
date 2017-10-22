package com.yo.cx.cxyo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import aero.panasonic.inflight.services.InFlight;
import aero.panasonic.inflight.services.IInFlightCallback;

import aero.panasonic.inflight.services.InFlightServices;
import aero.panasonic.inflight.services.advertising.AdvertisingAttributes;
import aero.panasonic.inflight.services.advertising.AdvertisingV1;
import aero.panasonic.inflight.services.advertising.Banner;
import aero.panasonic.inflight.services.connectinggate.ConnectingFlight;
import aero.panasonic.inflight.services.connectinggate.ConnectingGateV1;
import aero.panasonic.inflight.services.connectinggate.CurrentFlight;


public class FlightInfoFragment extends Fragment {

    private InFlight mInFlight;
    private AdvertisingV1 advertisingV1;
    private ConnectingGateV1 connectingGateV1;

    private ImageView bannerView;
    private TextView toLoc;
    private TextView toLocLong;
    private TextView fromLoc;
    private TextView fromLocLong;
    private TextView flightNo;
    CountDownLatch latch = new CountDownLatch(2);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);
        bannerView = (ImageView) view.findViewById(R.id.banner);
        toLoc = (TextView) view.findViewById(R.id.to_loc);
        fromLoc = (TextView) view.findViewById(R.id.from_loc);
        toLocLong = (TextView) view.findViewById(R.id.to_long_loc);
        fromLocLong = (TextView) view.findViewById(R.id.from_long_loc);
        flightNo = (TextView) view.findViewById(R.id.flight_no);

        mInFlight = ((MainActivity)getActivity()).getIFE();

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        final View ContentsView = getActivity().getLayoutInflater().inflate(R.layout.diag_layout, null);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());

                //  Create a new boolean and preference and set it to true
                // For Info day Only
                //boolean isSeemInfoDayFirstStart = getPrefs.getBoolean("SeemInfoDayFirstStart", true);
                boolean isFirstStart = getPrefs.getBoolean("FrontfirstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Spinner purpose_spinner = (Spinner) ContentsView.findViewById(R.id.layout_purpose_spinner);
                            ArrayAdapter<CharSequence> purpose_adapter = ArrayAdapter.createFromResource(getContext(), R.array.purpose_array, android.R.layout.simple_spinner_item);
                            purpose_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            purpose_spinner.setAdapter(purpose_adapter);

                            Spinner meeting_spinner = (Spinner) ContentsView.findViewById(R.id.layout_meeting_spinner);
                            ArrayAdapter<CharSequence> meet_adapter = ArrayAdapter.createFromResource(getContext(), R.array.meeting_array, android.R.layout.simple_spinner_item);
                            meet_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            meeting_spinner.setAdapter(meet_adapter);

                            Button sendButton = (Button)ContentsView.findViewById(R.id.sendButton);
                            Button cancelButton = (Button)ContentsView.findViewById(R.id.cancelButton);

                            // set title
                            alertDialogBuilder.setTitle("Hey!");

                            alertDialogBuilder.setView(ContentsView);
                            // create alert dialog
                            final AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();

                            sendButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });

                            cancelButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    });

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("FrontfirstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();

        AdvertisingV1.initService(getContext(), new IInFlightCallback() {
            @Override
            public void onInitServiceComplete(Object mServiceObject, String serviceName) {
                Log.v("debug", "onInitServiceComplete(): " + serviceName);
                advertisingV1 = (AdvertisingV1) mServiceObject;
                AdvertisingAttributes attribute = new AdvertisingAttributes();

                //Banner
                attribute.setZoneWidth(728);
                attribute.setZoneHeight(90);
                advertisingV1.requestBannerByZonePath("panasonic", attribute, new AdvertisingV1.OnBannerReceiveListener() {
                    @Override
                    public void onBannerReceived(Banner banner) {

                        Log.i("Info", "onBannerReceived() " + banner.toString());
                        Picasso.with(getContext()).load(banner.getContentUrl()).into(bannerView);

                    }

                    @Override
                    public void onError(AdvertisingV1.Error error) {
                        Log.e("Error", AdvertisingV1.Error.getErrorMessage(error));
                    }
                });
            }
            @Override
            public void onInitServiceFailed(String s, InFlight.Error error) {
                Log.e("Error", "onInitServiceFailed()" + InFlight.Error.getErrorMessage(error));
            }
        }, mInFlight);

        ConnectingGateV1.initService(getActivity(), new IInFlightCallback() {
            @Override
            public void onInitServiceComplete(Object o, String s) {
                if(s.equals(InFlightServices.CONNECTING_GATE_V1_SERVICE.getServiceName())){
                    connectingGateV1 = (ConnectingGateV1) o;
                    connectingGateV1.requestConnectingGateInfo("",null,"",connectingGateInfoListener);
                    latch.countDown();
                    Log.i("[latch] - gate", Float.toString(latch.getCount()));
                }
            }

            @Override
            public void onInitServiceFailed(String s, InFlight.Error error) {
                Log.e("Error", "onInitServiceFailed()" + InFlight.Error.getErrorMessage(error));
            }
        },mInFlight);

//        try {
//            Log.i("[latch] - try", Float.toString(latch.getCount()));
//            latch.await();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return view;
    }

    private final ConnectingGateV1.ConnectingGateInfoListener connectingGateInfoListener = new ConnectingGateV1.ConnectingGateInfoListener(){
        @Override
        public void onConnectingGateError(ConnectingGateV1.Error error) {
            Log.e("Error", "onConnectingGateError" + error.toString());
        }
        @Override
        public void onConnectingGateInfoAvailable(CurrentFlight currentFlight, List<ConnectingFlight> list) {

            latch.countDown();

            Log.i("Info", "Current Flight Info: " + currentFlight.toString() + "\n");
            // Log.i("Info", "Current Flight Info: " + currentFlight.get() + "\n");

            toLoc.setText(currentFlight.getArrivalAirport().getIataCode());
            fromLoc.setText(currentFlight.getDepartureAirport().getIataCode() );
            flightNo.setText(currentFlight.getFlightNumber());
            fromLocLong.setText(currentFlight.getDepartureAirport().getCity());
            toLocLong.setText(currentFlight.getArrivalAirport().getCity());

            // getArrivalGate()
            // getArrivalTerminal()
            // getEstimatedArrivalTime()
            // getDate()
            // getText()
            // getString()

            if (list.size() > 0) {
                for(ConnectingFlight connectingFlight : list){
                    Log.i("Info","Connecting Flight: " + connectingFlight.toString() + "\n");
                }
            }else{
                Log.i("Info","No connecting flights");
            }

        }
    };

}
