package com.example.myapplication.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Tools;
import com.example.myapplication.async.GetCarslist_async;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarslistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarslistFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {
RecyclerView list;
SwipeRefreshLayout swipeRefreshLayout;
int page=1;
LinearLayout empty;
TextView title;
    AsyncTask asyncRunning;
    // TODO: Rename and change types and number of parameters
    public static CarslistFragment newInstance() {
        CarslistFragment fragment = new CarslistFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_carslist, container, false);
        list=(RecyclerView)v.findViewById(R.id.list);
        title=(TextView) v.findViewById(R.id.title);
        empty=(LinearLayout) v.findViewById(R.id.empty);
        title.setText("Cars list");
        swipeRefreshLayout=(SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(this);

        //        to handle recycleview manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(mLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
//to get infromation about the recycle view layout
        final LinearLayoutManager m2LayoutManager;
        m2LayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(m2LayoutManager);
        onRefresh();
        //        to handle scroll
//        if user scroll get more data
        list.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    int pastVisiblesItems, visibleItemCount, totalItemCount;
//                    get count of visible items  in recycle
                    visibleItemCount = m2LayoutManager.getChildCount();
//                    get count of total items thet recycle own in layout
                    totalItemCount = m2LayoutManager.getItemCount();
//                    get cout f past visible item in recycle view
                    pastVisiblesItems = m2LayoutManager.findFirstVisibleItemPosition();
//                    if  the count of items that appeared bigger than total items return true
//                    this meaning the recycle  view calling more information from server
                    boolean loadMore =
                            pastVisiblesItems + visibleItemCount >= totalItemCount;
                    if (loadMore)
                    {
                        if (asyncRunning==null||(asyncRunning!=null&&asyncRunning.getStatus()!= AsyncTask.Status.RUNNING)) {
                            page++;
                            asyncRunning=  new GetCarslist_async(String.valueOf(page),getActivity(),list,empty).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }

                }}
            }
        });


        return v;

    }

    @Override
    public void onRefresh() {
        page=1;
        Tools.async(new GetCarslist_async(String.valueOf(page),getActivity(),list,empty),getActivity());
    }
}
