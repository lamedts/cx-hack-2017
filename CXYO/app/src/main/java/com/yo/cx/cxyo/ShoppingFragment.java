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
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

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

    private class ProductInfo {
        String product_title, product_img_url;
        int product_price;

        public ProductInfo(String product_title, String product_img_url, int product_price) {
            this.product_title = product_title;
            this.product_img_url = product_img_url;
            this.product_price = product_price;
        }

        public String getProduct_title() {
            return product_title;
        }

        public void setProduct_title(String product_title) {
            this.product_title = product_title;
        }

        public String getProduct_img_url() {
            return product_img_url;
        }

        public void setProduct_img_url(String product_img_url) {
            this.product_img_url = product_img_url;
        }

        public int getProduct_price() {
            return product_price;
        }

        public void setProduct_price(int product_price) {
            this.product_price = product_price;
        }
    }

    GridView recommendation_grid;
    ProductListAdapter productListAdapter;

    private List<ProductInfo> product_infos = new ArrayList<ProductInfo>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInFlight = ((MainActivity)getActivity()).getIFE();

        View rootView = inflater.inflate(R.layout.fragment_shopping, container, false);
        recommendation_grid = (GridView)rootView.findViewById(R.id.recommendation_grid);

        product_infos.add(new ProductInfo("SK-II Facial Treatment Essence Duo Se...", "https://d18kl27lpu4e4b.cloudfront.net/inflight/services/catalogs/shopping/browse/v1/images/1024x600/poster_245070_SDD338H16Q2.jpg", 304));
        product_infos.add(new ProductInfo("SK-II Facial Treatment Essence 250ml", "https://d18kl27lpu4e4b.cloudfront.net/inflight/services/catalogs/shopping/browse/v1/images/1024x600/poster_245070_SDD225T16Q2.jpg", 167));
        product_infos.add(new ProductInfo("SK-II Facial Treatment Mask (10 pieces)", "https://d18kl27lpu4e4b.cloudfront.net/inflight/services/catalogs/shopping/browse/v1/images/1024x600/poster_245070_SDD225D16Q2.jpg", 113));
        product_infos.add(new ProductInfo("SK-II 7-piece Gift Set", "https://d18kl27lpu4e4b.cloudfront.net/inflight/services/catalogs/shopping/browse/v1/images/1024x600/poster_245070_SDD338C16Q2.jpg", 0));
        product_infos.add(new ProductInfo("SK-II GenOptics Spots Essence 50ml", "https://d18kl27lpu4e4b.cloudfront.net/inflight/services/catalogs/shopping/browse/v1/images/1024x600/poster_245070_SDD338J16Q2.jpg", 165));
        product_infos.add(new ProductInfo("SK-II GenOptics Aura Essence 50ml", "https://d18kl27lpu4e4b.cloudfront.net/inflight/services/catalogs/shopping/browse/v1/images/1024x600/poster_245070_SDD338K16Q2.jpg", 197));

        productListAdapter = new ProductListAdapter(getContext(),R.layout.fragment_product_box,product_infos);
        recommendation_grid.setAdapter(productListAdapter);

        /*CatalogDataV1.initService(getContext(), CatalogDataV1.CatalogType.SHOPPING, new IInFlightCallback(){
            @Override
            public void onInitServiceComplete(Object o, String s) {
                CatalogDataV1 catalogDataV1 = (CatalogDataV1) o;
                ArrayList<String> ids = new ArrayList<String>();
                for (int i = 100040; i < 100046; ++i) {
                    ids.add(String.valueOf(i));
                }
                RequestCategoryItem requestCategoryItem = catalogDataV1.requestCategoryItems(ids, "", categoryItemResponseListener);
                //RequestCategoryItem requestCategoryItem = catalogDataV1.requestCategoryItems("1a047", "", categoryItemResponseListener);
                if (requestCategoryItem != null) {
                    requestCategoryItem.executeAsync();
                }
            }
            @Override
            public void onInitServiceFailed(String s, InFlight.Error error) {
                Log.e("Error", "onInitServiceFailed()" + InFlight.Error.getErrorMessage(error));
            }
        }, mInFlight);*/
        // Inflate the layout for this fragment
        return rootView;
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
                        String product_title = x.getJSONObject("title").getString("eng");
                        if (product_title.length() > 40) {
                            product_title = product_title.substring(0, 37) + "...";
                        }
                        Log.i("Info", "product_title: " + product_title);
                        int product_price = x.getJSONObject("price").getJSONObject("usd").getInt("amount");
                        Log.i("Info", "product_price: " + String.valueOf(product_price));
                        String product_img_urls = ((JSONObject)x.getJSONObject("image").getJSONArray("default").opt(0)).getString("url");
                        Log.i("Info", "url: " + product_img_urls);
                        productListAdapter.add(new ProductInfo(product_title, product_img_urls, product_price));
                    } catch (Exception e) {
                        Log.i("Info", "def");
                    }
                    Log.i("Info", "abc: " + x.toString());
                }
            }else{
                Log.i("Info", "No Category Item Information");
            }
        }
    };

    public class ProductListAdapter extends ArrayAdapter<ProductInfo> {
        public ProductListAdapter(Context context, int rootViewResourceId) {
            super(context, rootViewResourceId);
        }

        public ProductListAdapter(Context context, int rootViewResourceId, List<ProductInfo> items) {
            super(context, rootViewResourceId, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with
            View itemView = convertView;
            if (itemView == null) {
                itemView =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_product_box, parent, false);

            }
            // Find the location to work with
            ProductInfo currentProductInfo = getItem(position);

            // Fill the view
            TextView title = (TextView) itemView.findViewById(R.id.product_title);
            title.setText(currentProductInfo.getProduct_title());

            TextView price = (TextView) itemView.findViewById(R.id.product_price);
            price.setText("$" + String.valueOf(currentProductInfo.getProduct_price()));

            ImageView product_image = (ImageView) itemView.findViewById(R.id.product_image);
            Picasso.with(getContext()).load(currentProductInfo.getProduct_img_url()).into(product_image);

            return itemView;
        }

    }
}
