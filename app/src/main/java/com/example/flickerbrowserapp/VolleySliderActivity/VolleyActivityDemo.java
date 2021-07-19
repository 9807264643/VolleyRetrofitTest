package com.example.flickerbrowserapp.VolleySliderActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flickerbrowserapp.R;
import com.example.flickerbrowserapp.VolleySliderActivity.Adapter.CategoryAdapter;
import com.example.flickerbrowserapp.VolleySliderActivity.Adapter.SliderAdapter;
import com.example.flickerbrowserapp.VolleySliderActivity.Model.HomeModel;
import com.example.flickerbrowserapp.VolleySliderActivity.Model.SubCatModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyActivityDemo extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SliderAdapter sliderAdapter;
    private String sliderId, sliderTitle;
    private String image;
    public ArrayList<HomeModel> BannerDataList = new ArrayList();
    private SliderView sliderView;

    private ArrayList<SubCatModel> subCatModelList = new ArrayList<>();
    private String catId, catTitle;
    private String cat_image;
    private CategoryAdapter categoryAdapter;

    private String ImgRoot = "http://philiabeauty.in/elegant-publishers/";
    private String SliderUrl = "http://philiabeauty.in/elegant-publishers/controller-api.php?action=get_slider&access_token=Ym9va19kZXRhaWxz";
    private String CategoryUrl = "http://philiabeauty.in/elegant-publishers/controller-api.php?action=get_category&access_token=Ym9va19kZXRhaWxz";
//    private String SubCategoryUrl = "http://philiabeauty.in/elegant-publishers/controller-api.php?action=get_subcategory_by_category&id=";
    private String token = "Ym9va19kZXRhaWxz";

    private String SubCategoryUrl = "http://philiabeauty.in/elegant-publishers/controller-api.php?action=get_subcategory_by_category&id=";
    private String Token = "&access_token=Ym9va19kZXRhaWxz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        recyclerView = findViewById(R.id.recyclerview);

        /*     It is use to call the Slider*/
        getSliderDetail();
        setSlider();


        getCategory();
        setCatAdaptor();

    }


    private void getSliderDetail() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SliderUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("DATA");
                    Log.v("Response", response.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        HomeModel dataModel = new HomeModel();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        sliderId = jsonObject.getString("ID");
                        sliderTitle = jsonObject.getString("TITLE");
                        image = ImgRoot + jsonObject.getString("IMG_SRC");
                        dataModel.setSliderImg(image);
//                        dataModel.setID(image);
//                        dataModel.setTITLE(image);

                        BannerDataList.add(dataModel);

                    }

                    sliderAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    System.out.print("Exception is :" + e.getMessage());

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = (RequestQueue) Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void setSlider() {

        sliderView = findViewById(R.id.imageSlider);
        sliderAdapter = new SliderAdapter(this, BannerDataList);

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEOUTSCALINGTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    private void getCategory() {
        String SubCategoryUrlFinal = SubCategoryUrl + 23 + Token;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SubCategoryUrlFinal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseSudcat", response.toString());

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("DATA");
                    Log.v("SubCat Response", response.toString());

                    for (int i = 0; i <jsonArray.length(); i++) {
                        SubCatModel subCatModel = new SubCatModel();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                         catId = jsonObject.getString("ID");
                        catTitle = jsonObject.getString("TITLE");
                        cat_image = ImgRoot + jsonObject.getString("IMG_SRC");

                        subCatModel.setSubCatID(catId);
                        subCatModel.setSubCatTITLE(catTitle);
                        subCatModel.setSubCatIMG(cat_image);

                        subCatModelList.add(subCatModel);

                    }
                    categoryAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    System.out.print("Exception is :" + e.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage().toString());

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void setCatAdaptor() {
//        @SuppressLint("WrongConstant")
//        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
//        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
//        //  call the constructor of CustomAdapter to send the reference and data to Adapter
//        categoryAdapter = new CategoryAdapter(this, subCatModels);
//        recyclerView.setAdapter(categoryAdapter);

        @SuppressLint("WrongConstant")
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(manager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        categoryAdapter = new CategoryAdapter(this, subCatModelList);
        recyclerView.setAdapter(categoryAdapter);
    }


}