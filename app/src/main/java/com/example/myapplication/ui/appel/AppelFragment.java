package com.example.myapplication.ui.appel;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class AppelFragment extends Fragment implements  View.OnClickListener{

    private AppelViewModel appelViewModel;


    //2 - Declare callback
    private OnButtonClickedListener mCallback;
    private EditText inputPhoneNumber;

    // 1 - Declare our interface that will be implemented by any container activity
    public interface OnButtonClickedListener {
        public void onButtonClicked(View view);

        void OnButtonClickedListenerMusic(View view);

        void onButtonClickedMusic(View v);

        void onButtonClickedMessage(View v);
    }

    // --------------

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appelViewModel =
                new ViewModelProvider(this).get(AppelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appel, container, false);

        inputPhoneNumber = (EditText) root.findViewById(R.id.appel);
        Button btnCall = (Button) root.findViewById(R.id.appeler);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ajouter un REGEX pour valider le numéro
                AlertDialog.Builder popup = new AlertDialog.Builder(getContext());
                if(inputPhoneNumber.length() != 0){

                    popup.setTitle("Confirmation")
                            .setMessage("Appeler ce numéro : " + inputPhoneNumber.getText() + " ?")
                            .setPositiveButton("Appeler", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Ouvrir l'application native APPEL du téléphone
                                    /*Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                    callIntent.setData(Uri.parse("tel:"+inputPhoneNumber.getText().toString()));
                                    startActivity(callIntent);*/
                                    Log.i("a", "a");
                                    if(makePhoneCall()){

                                        Toast.makeText(getContext(), "Ça marche", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getContext(), "Ça marche pas", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton("Abandonner", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                            .create()
                            .show();
                }else{
                    popup.setTitle("Numéro invalide")
                            .setMessage("Veuillez entrer un numéro de téléphone !")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .create()
                            .show();
                }
            }
        });
        return root;
    }
    
    //Fonction pour appeler
    private boolean makePhoneCall() {
        String num = inputPhoneNumber.getText().toString();
        if(num.trim().length()>0){
            Log.i("a", "a");
            if(checkSelfPermission(getContext(),
                    Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[] {Manifest.permission.CALL_PHONE}, 1);
                return  true;
            }else {
                String dial = "tel:"+ num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                return  true;
            }

        }else {
            Toast.makeText(getContext(),"entrer un numero de telephone",Toast.LENGTH_SHORT);
            return  false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(getContext(), "Pernission non accordée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 4 - Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    // --------------
    // ACTIONS
    // --------------

    public void onClick(View v) {
        // 5 - Spread the click to the parent activity
        mCallback.onButtonClicked(v);
    }

    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }
}