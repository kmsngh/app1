package com.example.app1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.app1.Classes.row;
import com.example.app1.Utils.OnGalleryItemClickListener;
import com.example.app1.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaggeredRecyclerAdapter extends RecyclerView.Adapter<StaggeredRecyclerAdapter.ImageViewHolder> {

    Context mContext;
    ArrayList<row> mData;
    OnGalleryItemClickListener listener;

    public StaggeredRecyclerAdapter(Context mContext, ArrayList<row> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);

        return new ImageViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {


        holder.img.setImageResource(mData.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnGalleryItemClickListener listener){
        this.listener = listener;
    }

    public void onItemClick(ImageViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }
    public row getItem(int position){return mData.get(position); }
    public class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView img;


        public ImageViewHolder(@NonNull final View itemView, final OnGalleryItemClickListener listener2) {
            super(itemView);

            img = itemView.findViewById(R.id.row_img);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener2 != null){
                        listener2.onItemClick(ImageViewHolder.this, view, position );

                    }

                }
            });

        }
    }

}
