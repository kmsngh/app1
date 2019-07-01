package com.example.app1;

import android.net.Uri;

public class ContactItem {
    String name, number;
    Uri userPhoto;
    int user;

    public ContactItem() {
    }

    public ContactItem(String name, String number, Uri userPhoto) {
        this.name = name;
        this.number = number;
        this.userPhoto = userPhoto;
    }

    public ContactItem(String name, String number, int user) {
        this.name = name;
        this.number = number;
        this.user = user;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUserPhoto(Uri userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setUser(int user) { this.user = user; }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Uri getUserPhoto() {
        return userPhoto;
    }

    public int getUser() { return user; }
}
