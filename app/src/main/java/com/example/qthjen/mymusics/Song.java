package com.example.qthjen.mymusics;

public class Song {

    private String mTitle;
    private int mFile;

    public Song(String title, int file) {

        mTitle = title;
        mFile = file;

    }

    public int getmFile() {

        return mFile;
    }

    public void setmFile(int mFile) {

        this.mFile = mFile;
    }

    public String getmTitle() {

        return mTitle;
    }

    public void setmTitle(String mTitle) {

        this.mTitle = mTitle;
    }
}
