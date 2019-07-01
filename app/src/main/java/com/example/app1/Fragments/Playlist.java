package com.example.app1.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.Classes.PlaylistItem;
import com.example.app1.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Playlist extends Fragment {

    ArrayList<PlaylistItem> mData;

    public Playlist(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mData = new ArrayList<PlaylistItem>();
        mData.add(new PlaylistItem("bad guy","Billie Eilish", R.drawable.badguy));
        mData.add(new PlaylistItem("Psycho", "Post Malone",R.drawable.psycho));
        mData.add(new PlaylistItem("Senorita","Shawn Mendes, Camila Cabello", R.drawable.senorita));
        mData.add(new PlaylistItem("Sucker","Jonas Brother",R.drawable.sucker));
        mData.add(new PlaylistItem("Jealous(feat. Chris Brown, Lil Wayne & Big Sean)","DJ Khaled",R.drawable.djkhald));
        mData.add(new PlaylistItem("Never Really Over","Katy Perry", R.drawable.neverreallyover));

        RecyclerView rv = new RecyclerView(getContext());

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new PlaylistAdapter(mData));

        return rv;
    }

    public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {

        ArrayList<PlaylistItem> mData;

        public PlaylistAdapter( ArrayList<PlaylistItem> mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View layout = layoutInflater.inflate(R.layout.playlist_item, parent, false);
            return new PlaylistViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
            holder.tv_name.setText(mData.get(position).getTitle());
            holder.tv_number.setText(mData.get(position).getSinger());
            holder.img_user.setImageResource(mData.get(position).getAlbumImg());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_number;
        ImageView img_user;

        public PlaylistViewHolder(@NonNull View itemView){
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_title);
            tv_number = itemView.findViewById(R.id.tv_singer);
            img_user = itemView.findViewById(R.id.album_art);

        }
    }
}
