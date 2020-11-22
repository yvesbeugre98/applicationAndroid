package com.example.myapplication.ui.video;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewUtils {
    public static final String RAW_VIDEO_EXAMPLE = "myvideo";
    public static  final String LOG_TAG = "AndroidVideoView";

    //fonction qui permet de lire la video
    public static void playVideoRaw(Context context, VideoView video, String resName){
        try {
            int id = VideoViewUtils.getRawResIdByName( context, resName);

            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
            Log.i(LOG_TAG, "Video URI: "+ uri);

            video.setVideoURI(uri);
            video.requestFocus();
            video.start();
        }
        catch (Exception e){
            Log.e(LOG_TAG, "Error Play Raw Video: "+e.getMessage());
            Toast.makeText(context,"Error Play Raw Video: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    public static int getRawResIdByName(Context context, String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "raw", pkgName);

        Log.i(LOG_TAG, "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
}
