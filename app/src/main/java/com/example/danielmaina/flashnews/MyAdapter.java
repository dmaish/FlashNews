package com.example.danielmaina.flashnews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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
        //linking cardView with xml to initiate animationss(third parties)
        YoYo.with(Techniques.RubberBand)
                .playOn(holder.cardView);

        //storing current Item
        FeedItem current=feedItems.get(position);
        //accessing the news Items  on the custom layout using the ViewHolder object
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());


    }

    @Override
    //method to limit the number of recyclerViews on your app
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //initialising the textViews and ImageViews in the cardView for the recyclerView's use
        TextView Title,Description,Date;
        ImageView Thumbnail;


        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            //initialising their Xml layouts
            cardView=(CardView) itemView.findViewById(R.id.cardview);
            Title=(TextView) itemView.findViewById(R.id.title_text);
            Description=(TextView) itemView.findViewById(R.id.description_text);
            Thumbnail=(ImageView) itemView.findViewById(R.id.thumb_img);
            Date=(TextView) itemView.findViewById(R.id.date_text);
        }
    }
}
