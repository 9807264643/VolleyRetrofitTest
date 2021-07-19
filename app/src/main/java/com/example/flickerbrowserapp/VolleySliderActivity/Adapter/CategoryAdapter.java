package com.example.flickerbrowserapp.VolleySliderActivity.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickerbrowserapp.R;
import com.example.flickerbrowserapp.VolleySliderActivity.Model.SubCatModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    private Activity activity;
    private Context context;
    private ArrayList<SubCatModel> list;
    private String SubCategoryUrl = "http://philiabeauty.in/elegant-publishers/controller-api.php?action=get_subcategory_by_category&id=";
    private String Token = "&access_token=Ym9va19kZXRhaWxz";
    private String imgRoot = "http://philiabeauty.in/elegant-publishers/";

//    public CategoryAdapter(Activity activity, ArrayList<SubCatModel> list) {
//        this.activity = activity;
//        this.list = list;
//    }


    public CategoryAdapter(Context context, ArrayList<SubCatModel> list) {
        this.context = context;
        this.list = list;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_listitems, parent, false);
//        MyViewHolder my = new MyViewHolder(view);
//        return my;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {

        final SubCatModel model = list.get(position);

        Log.v("data", model.getSubCatTITLE());
        holder.subCatTitle.setText(model.getSubCatTITLE());
        holder.subCatId.setText(model.getSubCatID());
        String img = model.getSubCatIMG();

        Picasso.get().load(img).placeholder(R.drawable.img_placeholder).fit().into(holder.catImage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView subCatTitle;
        private TextView subCatId;
        private ImageView catImage;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            subCatTitle = itemView.findViewById(R.id.subcat_name);
            subCatId = itemView.findViewById(R.id.subcat_id);
            catImage = itemView.findViewById(R.id.subcat_image);


        }
    }


}
