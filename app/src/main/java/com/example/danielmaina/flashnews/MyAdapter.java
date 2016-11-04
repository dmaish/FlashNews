package com.example.danielmaina.flashnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by danielmaina on 11/4/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //object of the ArrayList feedItems in the ReadRss class
    ArrayList<FeedItem>feedItems;
    Context context;

    //constructor for class
    public MyAdapter(Context context,ArrayList<FeedItem>feedItems){
          this.context=context;
          this.feedItems=feedItems;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//the inflater below takes the item specified in the following .inflate() method and passes it into a recyclerView Item
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row_news_item,parent,false);
       //viewHolder object
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    //method to limit the number of recyclerViews on your app
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
