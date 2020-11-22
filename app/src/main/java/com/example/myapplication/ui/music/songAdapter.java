package com.example.myapplication.ui.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class songAdapter extends BaseAdapter {

    private Context context;
    private List<song> songList;
    private LayoutInflater inflater;

    public  songAdapter(Context context, List<song> songList){
        this.context = context;
        this.songList = songList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public song getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {

        root = inflater.inflate(R.layout.song, null);

        //Current element
        song currentItem = getItem(position);
        String name = currentItem.getNom_chanteur();
        String time = currentItem.getTime_chanteur();

        //fields
        TextView nom = root.findViewById(R.id.nom_chanteur);
        TextView temps = root.findViewById(R.id.time_music);

        //Modifier la vue
        nom.setText(name);
        temps.setText(time);


        return parent;
    }
}
