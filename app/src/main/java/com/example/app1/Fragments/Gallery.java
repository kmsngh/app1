package com.example.app1.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ablanco.zoomy.Zoomy;
import com.example.app1.Classes.row;
import com.example.app1.Utils.OnGalleryItemClickListener;
import com.example.app1.R;
import com.example.app1.Adapters.StaggeredRecyclerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Gallery extends Fragment {

    private StaggeredGridLayoutManager manager;

    ImageView img;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        ArrayList mData = new ArrayList<row>();
        mData.add(new row(R.drawable.gala));
        mData.add(new row(R.drawable.galb));
        mData.add(new row(R.drawable.galc));
        mData.add(new row(R.drawable.gald));
        mData.add(new row(R.drawable.gale));
        mData.add(new row(R.drawable.galf));
        mData.add(new row(R.drawable.galg));
        mData.add(new row(R.drawable.galh));
        mData.add(new row(R.drawable.gali));
        mData.add(new row(R.drawable.galj));
        mData.add(new row(R.drawable.gala));
        mData.add(new row(R.drawable.galb));
        mData.add(new row(R.drawable.galc));
        mData.add(new row(R.drawable.gald));
        mData.add(new row(R.drawable.gale));
        mData.add(new row(R.drawable.galf));
        mData.add(new row(R.drawable.galg));
        mData.add(new row(R.drawable.galh));
        mData.add(new row(R.drawable.gali));
        mData.add(new row(R.drawable.galj));


        final RecyclerView staggeredRv  = view.findViewById(R.id.staggered_rv);
        staggeredRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        final StaggeredRecyclerAdapter adapter = new StaggeredRecyclerAdapter(getContext(), mData);
        staggeredRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnGalleryItemClickListener() {
            @Override
            public void onItemClick(StaggeredRecyclerAdapter.ImageViewHolder holder, View view, int position) {
                row item= adapter.getItem(position);
                Zoomy.Builder builder = new Zoomy.Builder(getActivity()).target(staggeredRv.getChildAt(position));
                builder.register();
            }
        });
        return staggeredRv;

    }
}


