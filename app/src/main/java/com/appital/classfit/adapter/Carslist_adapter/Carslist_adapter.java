package com.appital.classfit.adapter.Carslist_adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.objects.Car;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;

public class Carslist_adapter extends  RecyclerView.Adapter<Carslist_adapter.MyViewHolder> {

    public Carslist_adapter(ArrayList<Car> list, FragmentActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    ArrayList<Car>list;
    FragmentActivity activity;
    private View itemView;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
Car car =list.get(position);
if (car.id!=null&&car.id.trim().length()>0){
    holder.id.setText(car.id);
}
else {
    holder.id.setVisibility(View.GONE);
}
if (car.isUsed!=null&&car.isUsed.trim().length()>0){
    holder.used.setText(car.isUsed);
}
else {
    holder.used.setVisibility(View.GONE);
}
if (car.constractionYear!=null&&car.constractionYear.trim().length()>0){
    holder.constractionYear.setText(car.constractionYear);
}
else {
    holder.constractionYear.setVisibility(View.GONE);
}
if (car.brand!=null&&car.brand.trim().length()>0){
    holder.brand.setText(car.brand);
}else {
    holder.brand.setVisibility(View.GONE);
}
if (car.imageUrl!=null&&car.imageUrl.trim().length()>0){
    Glide.with(activity)
            .load(car.imageUrl)
            .into(holder.imageView);
}
}


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id,brand,constractionYear,used;

        public MyViewHolder(View view) {
            super(view);

            imageView =(ImageView)itemView.findViewById(R.id.imageView);
            id =(TextView) itemView.findViewById(R.id.id);
            brand =(TextView) itemView.findViewById(R.id.brand);
            constractionYear =(TextView) itemView.findViewById(R.id.constractionYear);
            used =(TextView) itemView.findViewById(R.id.used);
        }



    }




}








