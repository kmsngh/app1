package com.example.app1.Classes;

public class PlaylistItem {

    String title, singer, url;
    int albumImg;

    public PlaylistItem() {

    }

    public PlaylistItem(String title, String singer, int albumImg, String url) {
        this.title = title;
        this.singer = singer;
        this.albumImg = albumImg;
        this.url = url;
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

    public void setUrl(String url) {
        this.url = url;
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

    public String getUrl() {
        return url;
    }

}


