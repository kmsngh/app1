package com.example.app1.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app1.R;
import com.example.app1.Adapters.StaggeredRecyclerAdapter;
import com.example.app1.Classes.row;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Gallery extends Fragment {

    private StaggeredGridLayoutManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
//       gridview = (GridView) getView().findViewById(R.id.gridview);
//        // ImageAdapter 객체를 생성하고 GridView 객체에 연결
//        gridview.setAdapter(new ImageAdapter(getContext()));

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

        RecyclerView staggeredRv  = new RecyclerView(getContext());
        staggeredRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        staggeredRv.setAdapter(new StaggeredRecyclerAdapter(getContext(), mData));

        return staggeredRv;
    }


}
