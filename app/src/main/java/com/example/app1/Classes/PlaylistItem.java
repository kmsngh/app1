package com.example.app1.Classes;

public class PlaylistItem {

    String title, singer;
    int albumImg;

    public PlaylistItem() {

    }

    public PlaylistItem(String title, String singer, int albumImg) {
        this.title = title;
        this.singer = singer;
        this.albumImg = albumImg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setAlbumImg(int albumImg) {
        this.albumImg = albumImg;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public int getAlbumImg() {
        return albumImg;
    }
}
