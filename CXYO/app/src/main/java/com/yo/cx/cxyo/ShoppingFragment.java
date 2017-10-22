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

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import aero.panasonic.inflight.services.IInFlightCallback;
import aero.panasonic.inflight.services.InFlight;
import aero.panasonic.inflight.services.catalog.CatalogDataV1;
import aero.panasonic.inflight.services.catalog.RequestCatalog;
import aero.panasonic.inflight.services.catalog.RequestCategory;
import aero.panasonic.inflight.services.catalog.RequestCategoryItem;


public class ShoppingFragment extends Fragment {
    private InFlight mInFlight;

    private List<String> product_titles;
    private List<Integer> product_prices;
    private List<String> product_img_urls;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInFlight = ((MainActivity)getActivity()).getIFE();

        CatalogDataV1.initService(getContext(), CatalogDataV1.CatalogType.SHOPPING, new IInFlightCallback(){
            @Override
            public void onInitServiceComplete(Object o, String s) {
                CatalogDataV1 catalogDataV1 = (CatalogDataV1) o;
                RequestCategoryItem requestCategoryItem = catalogDataV1.requestCategoryItems("1a047", "", categoryItemResponseListener);
                if (requestCategoryItem != null) {
                    requestCategoryItem.executeAsync();
                }
            }
            @Override
            public void onInitServiceFailed(String s, InFlight.Error error) {
                Log.e("Error", "onInitServiceFailed()" + InFlight.Error.getErrorMessage(error));
            }
        }, mInFlight);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping, container, false);
    }

    private final CatalogDataV1.CategoryItemResponseListener categoryItemResponseListener = new CatalogDataV1.CategoryItemResponseListener(){
        @Override
        public void onError(CatalogDataV1.Error error) {
            Log.i("Info", "Category Item Error: " + error.toString());
        }
        @Override
        public void onCategoryItemReceived(JSONArray jsonArray) {
            if(jsonArray.length() > 0){
                for(int i = 0; i < jsonArray.length(); i++){
                    Log.i("Info", "Category Item: " + jsonArray.opt(i).toString());
                    JSONObject x = (JSONObject) jsonArray.opt(i);
                    try {
                        x.getJSONObject("title").getString("eng");
                    } catch (Exception e) {
                        ;
                    }
                    Log.i("Info", "abc: " + x.toString());
                }
            }else{
                Log.i("Info", "No Category Item Information");
            }
        }
    };
}
