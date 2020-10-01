package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.async.GetCarslist_async;
import com.example.myapplication.helper.CheckInternetConnection;
import com.example.myapplication.objects.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Tools {
    private static AsyncTask masterasync=null;
    private static boolean check=false;



    public  String sendGet(String x) {
        if (x!=null&&x.contains("https")) {
            StringBuilder builder = new StringBuilder();
            HttpsURLConnection urlConnection = null;
            InputStream in = null;
            try {
                URL url = new URL(x);
                urlConnection =
                        (HttpsURLConnection) url.openConnection();

                urlConnection.setRequestProperty("Connection", "Keep-Alive");
                urlConnection.setRequestMethod("GET");

                urlConnection.connect();
                in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (urlConnection != null && in != null) {
                urlConnection.disconnect();
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JSONObject rez = null;

            return builder.toString();
        }else {
            StringBuilder builder = new StringBuilder();
            HttpURLConnection urlConnection = null;
            InputStream in = null;
            try {
                URL url = new URL(x);
                urlConnection =
                        (HttpURLConnection) url.openConnection();
                urlConnection.setInstanceFollowRedirects(false);
                urlConnection.setRequestMethod("GET");

                urlConnection.setRequestProperty("Connection", "Keep-Alive");
                urlConnection.setRequestProperty("Content-Type", "text/plain");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.connect();
                in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (urlConnection != null && in != null) {
                urlConnection.disconnect();
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return builder.toString();
        }
    }

    public  static void async(AsyncTask asyncTask, FragmentActivity activity){


        check = new CheckInternetConnection(activity).haveNetworkConnection();
if (check) {
    if (asyncTask != null && masterasync != null && masterasync.getClass() == asyncTask.getClass() && masterasync.getStatus() == AsyncTask.Status.RUNNING) {
        masterasync.cancel(true);
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    } else if (asyncTask != null) {
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    masterasync = asyncTask;
}
    }
  public static ArrayList<Car> parsejson(String result){
        ArrayList<Car> list=new ArrayList<>();
      try {
          JSONObject jsonObject=new JSONObject(result);
          if (jsonObject!=null&&jsonObject.has("data")){
              JSONArray jsonArray=jsonObject.getJSONArray("data");
              for (int i=0; i<jsonArray.length();i++){
                  JSONObject json=jsonArray.getJSONObject(i);
                  Car car=new Car();
                  if (json!=null){
                      if (json.has("id")){
                          car.id=json.getString("id");
                      }
                      if (json.has("brand")){
                          car.brand=json.getString("brand");
                      }
                      if (json.has("constractionYear")){
                          car.constractionYear=json.getString("constractionYear");
                      }
                      if (json.has("isUsed")){
                          car.isUsed=json.getString("isUsed");
                      }
                      if (json.has("imageUrl")){
                          car.imageUrl=json.getString("imageUrl");
                      }
                  }
                  list.add(car);
              }
              return list;
          }
      } catch (JSONException e) {
          e.printStackTrace();
      }
      return null;
  }

}
