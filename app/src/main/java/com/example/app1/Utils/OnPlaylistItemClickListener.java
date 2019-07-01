package com.example.app1.Utils;
import android.view.View;

import com.example.app1.Fragments.Playlist;

public interface OnPlaylistItemClickListener {

    public void onItemClick(Playlist.PlaylistViewHolder holder, View view, int position);
}