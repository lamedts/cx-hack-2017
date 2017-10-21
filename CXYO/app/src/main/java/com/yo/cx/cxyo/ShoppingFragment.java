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

import aero.panasonic.inflight.services.IInFlightCallback;
import aero.panasonic.inflight.services.InFlight;
import aero.panasonic.inflight.services.catalog.CatalogDataV1;
import aero.panasonic.inflight.services.catalog.RequestCatalog;
import aero.panasonic.inflight.services.catalog.RequestCategory;


public class ShoppingFragment extends Fragment {
    private InFlight mInFlight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInFlight = new InFlight();
        mInFlight.setAppId(getContext(), "c4ae8d6b48299d9a5fb607a4086faa15b4f433ff79a26685abe61268817d~1p8c2fb02f59f01c30e7af4cac36c4cf3f1");

        CatalogDataV1.initService(getContext(), CatalogDataV1.CatalogType.SHOPPING, new IInFlightCallback(){
            @Override
            public void onInitServiceComplete(Object o, String s) {
                CatalogDataV1 catalogDataV1 = (CatalogDataV1) o;
                Log.i("Info", "Catalog Object: " + catalogDataV1.getCatalogType());
                RequestCatalog requestCatalog = catalogDataV1.requestCatalogs("","eng",catalogResponseListener);
                if (requestCatalog != null) {
                    requestCatalog.executeAsync();
                }
                RequestCategory requestCategory = catalogDataV1.requestCatalogCategories("1a","",categoryResponseListener);
                if (requestCategory != null) {
                    requestCategory.executeAsync();
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


    private final CatalogDataV1.CategoryResponseListener categoryResponseListener = new CatalogDataV1.CategoryResponseListener(){
        @Override
        public void onError(CatalogDataV1.Error error) {
            Log.i("Info", "Category Error: " + error.toString());
        }
        @Override
        public void onCategoryReceived(JSONArray jsonArray) {
            if(jsonArray.length() > 0){
                for(int i = 0; i < jsonArray.length(); i++){
                    Log.i("Info", "Category: " + jsonArray.opt(i).toString());
                }
            }else{
                Log.i("Info", "No Category Information");
            }
        }
    };

    private final CatalogDataV1.CatalogResponseListener catalogResponseListener = new CatalogDataV1.CatalogResponseListener(){
        @Override
        public void onError(CatalogDataV1.Error error) {
            Log.e("Error","Catalog Error: " + error.toString());
        }
        @Override
        public void onCatalogReceived(JSONArray jsonArray) {
            if(jsonArray.length() > 0){
                for(int i = 0; i < jsonArray.length(); i++){
                    Log.i("Info", "Catalog: " + jsonArray.opt(i).toString());
                }
            }else{
                Log.i("Info", "No Catalog Information");
            }
        }
    };
}
