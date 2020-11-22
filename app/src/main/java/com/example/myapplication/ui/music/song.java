package com.example.myapplication.ui.music;

import android.widget.EditText;

public class song {
    private String nom_chanteur;
    private String time_chanteur;

    public song(String nom, String time) {
        this.nom_chanteur = nom;
        this.time_chanteur = time;
    }

    public String getNom_chanteur() {
        return nom_chanteur;
    }

        public String getTime_chanteur() {
        return time_chanteur;
    }
}
