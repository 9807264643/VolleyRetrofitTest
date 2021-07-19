package com.example.flickerbrowserapp.VolleySliderActivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flickerbrowserapp.R;
import com.example.flickerbrowserapp.VolleySliderActivity.Model.HomeModel;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<HomeModel> homeModelArrayList;

    public SliderAdapter(Context context, ArrayList<HomeModel> homeModelArrayList) {
        this.context = context;
        this.homeModelArrayList = homeModelArrayList;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide, null);
        return new SliderAdapterVH(inflater);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        final HomeModel model = homeModelArrayList.get(position);

        String img = model.getSliderImg();

        Picasso.get().load(img).placeholder(R.drawable.img_placeholder).fit().into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        return homeModelArrayList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder{
        View itemView;
        ImageView imageViewBackground;

    public SliderAdapterVH(View itemView) {
        super(itemView);
        imageViewBackground = itemView.findViewById(R.id.image);
        this.itemView = itemView;

    }
}
}
