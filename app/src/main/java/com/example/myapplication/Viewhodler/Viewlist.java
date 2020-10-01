package com.example.myapplication.Viewhodler;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class Viewlist extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView id,brand,constractionYear,used;
    public Viewlist( View itemView) {
        super(itemView);
        imageView =(ImageView)itemView.findViewById(R.id.imageView);
        id =(TextView) itemView.findViewById(R.id.id);
        brand =(TextView) itemView.findViewById(R.id.brand);
        constractionYear =(TextView) itemView.findViewById(R.id.constractionYear);
        used =(TextView) itemView.findViewById(R.id.used);
    }
}
