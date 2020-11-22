package com.example.myapplication.ui.video;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class VideoFragment extends Fragment {
    private VideoViewModel videoViewModel;
    public static  final String LOG_TAG = "AndroidVideoView";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        videoViewModel =
                new ViewModelProvider(this).get(VideoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_video    , container, false);

        final VideoView videoView = root.findViewById(R.id.videoView);

        MediaController controller = new MediaController(getContext());
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        controller.setAnchorView(videoView);
                    }
                });
            }
        });
        try {
            videoView.setMediaController(controller);
            String resName = VideoViewUtils.RAW_VIDEO_EXAMPLE;
            VideoViewUtils.playVideoRaw(getContext(), videoView, resName);
        } catch (Exception e){
            Log.e(LOG_TAG, "Error Play Raw Video: "+e.getMessage());
            Toast.makeText(getContext(),"Error Play Raw Video: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return root;
    }
}
