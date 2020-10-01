package com.example.myapplication.async;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appital.classfit.adapter.Carslist_adapter.Carslist_adapter;
import com.example.myapplication.Tools;
import com.example.myapplication.objects.Car;

import java.util.ArrayList;

public class GetCarslist_async extends AsyncTask<Object, Object, String> {


    String page=null;
    FragmentActivity activity;
    RecyclerView carslist;
    LinearLayout empty;
    public GetCarslist_async( String page, FragmentActivity activity, RecyclerView carslist, LinearLayout empty) {

        this.page = page;
        this.activity = activity;
        this.carslist = carslist;
        this.empty = empty;
    }

    @Override
    protected String doInBackground(Object... objects) {
        if (page!=null) {
            String url = "http://demo1585915.mockable.io/api/v1/cars?page=" + page;
            Tools tools = new Tools();
            String result = tools.sendGet(url);
            return result;
        }
        return null;
    }

//    {
//        "status": 1,
//            "data": [
//        {
//            "id": 21,
//                "brand": "AUDI_3",
//                "constractionYear": "01.2015",
//                "isUsed": true,
//                "imageUrl": "http://i.imgur.com/FG2eSjW.jpg"
//        }
//]
//    }



    @Override
    protected void onPostExecute(String re) {
        super.onPostExecute(re);
if (re!=null&&re.trim().length()>0){
    ArrayList<Car>list=Tools.parsejson(re);
    if (list!=null&&list.size()>0){
        if (carslist!=null&&empty!=null){
carslist.setVisibility(View.VISIBLE);
empty.setVisibility(View.GONE);
            Carslist_adapter carslist_adapter=new Carslist_adapter(list,activity);
            carslist.setAdapter(carslist_adapter);
        }
    }else {
if (carslist!=null&&empty!=null&&((carslist.getAdapter()!=null)||(carslist.getAdapter()!=null&&carslist.getAdapter().getItemCount()==0))){

    carslist.setVisibility(View.GONE);
    empty.setVisibility(View.VISIBLE);
}
    }
}
    }
}
