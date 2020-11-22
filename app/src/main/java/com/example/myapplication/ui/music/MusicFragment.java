package com.example.myapplication.ui.music;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.ui.appel.AppelFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MusicFragment extends Fragment{

    private MusicViewModel musicViewModel;
    ListView song_list;

    SearchView searchView;
    ArrayAdapter<String> adapter;

    String[] data = {"a", "b", "c"};

    //2 - Declare callback
    private View play;
    private ListView list_music;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music, container, false);
        afficheList(root);
        return root;
    }

    public void afficheList(View v){
        List<song> songList = new ArrayList<song>();
        songList.add(new song("Mc one", "03:25"));
        songList.add(new song("Jojo le barbu", "04:00"));
        songList.add(new song("Dena mwana", "02:14"));

        list_music = v.findViewById(R.id.song_list);
        list_music.setAdapter(new songAdapter(getContext(), songList));
    }
}