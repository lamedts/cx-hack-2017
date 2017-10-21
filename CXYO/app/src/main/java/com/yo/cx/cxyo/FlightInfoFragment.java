package com.yo.cx.cxyo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import aero.panasonic.inflight.services.InFlight;
import aero.panasonic.inflight.services.IInFlightCallback;

import aero.panasonic.inflight.services.InFlightServices;
import aero.panasonic.inflight.services.advertising.AdvertisingAttributes;
import aero.panasonic.inflight.services.advertising.AdvertisingV1;
import aero.panasonic.inflight.services.advertising.Banner;
import aero.panasonic.inflight.services.data.datasourcemanager.FileStoreDataSourceManager;
import aero.panasonic.inflight.services.passengerdata.v1.PassengerData;


public class FlightInfoFragment extends Fragment {

    private InFlight mInFlight;
    private AdvertisingV1 advertisingV1;
    private PassengerData mPassengerData;

    private ImageView bannerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);
        bannerView = (ImageView) view.findViewById(R.id.banner);

        mInFlight = ((MainActivity)getActivity()).getIFE();

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

        PassengerData.initService(getContext(), new IInFlightCallback() {
            @Override
            public void onInitServiceComplete(Object o, String s) {
                if(s.equals(InFlightServices.PASSENGER_DATA_V1_SERVICE.getServiceName())) {
                    mPassengerData = (PassengerData) o;
                    if (mPassengerData == null) return;

                    mPassengerData.requestAllPassengersBasicInfo(new PassengerData.OnAllPassengersBasicInfoReceiveListener() {
                        @Override
                        public void onPassengersBasicInfoReceived(JSONObject passengerData) {
                            Log.d("Debug", "onPassengersBasicInfoReceived: " + passengerData.toString());
                        }

                        @Override
                        public void onPassengerDataError(PassengerData.Error error) {
                            Log.e("Error", "onPassengerDataError: " + error);
                        }
                    });
                }
            }
            @Override
            public void onInitServiceFailed(String serviceName, InFlight.Error error) {
                Log.e("Error", "onInitServiceFailed: " + serviceName + " " + error);
            }
        }, mInFlight);

        return view;
    }
}
