package com.example.app1.Utils;

import android.view.View;

import com.example.app1.Adapters.StaggeredRecyclerAdapter;

public interface OnGalleryItemClickListener {

    public void onItemClick(StaggeredRecyclerAdapter.ImageViewHolder holder, View view, int position);
}
