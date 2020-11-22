package com.example.myapplication.ui.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VideoViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public VideoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is video fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }





}
