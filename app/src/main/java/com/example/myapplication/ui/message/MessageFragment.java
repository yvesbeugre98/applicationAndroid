package com.example.myapplication.ui.message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.ui.appel.AppelFragment;

public class MessageFragment extends Fragment implements View.OnClickListener{

    private MessageViewModel messageViewModel;
    private EditText numero;
    private EditText message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    

    //2 - Declare callback
    private OnButtonClickedListenerMessage mCallback;
    private View send;

    // 1 - Declare our interface that will be implemented by any container activity
    public interface OnButtonClickedListenerMessage {
        public void onButtonClicked(View view);

        void OnButtonClickedListenerMessage(View view);

        void onButtonClickedMessage(View v);
    }

    // --------------


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messageViewModel =
                new ViewModelProvider(this).get(MessageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        send = root.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendsms(root);
            }
        });
        return root;
    }


    //Envoi de message
    public void sendsms(View v)
    {
        //get the phone the number and the message
        numero = v.findViewById(R.id.telephon);
        message = v.findViewById(R.id.msg);
        String number = numero.getText().toString();
        String msg = message.getText().toString();
        //use the sms manager to send message
        SmsManager sm=SmsManager.getDefault();
        sm.sendTextMessage(number, null, msg, null, null);
        Toast.makeText(getActivity(),"Messege sent",Toast.LENGTH_LONG).show();
    }
/*******************************************************************************************************/
@Override
public void onActivityCreated(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onActivityCreated(savedInstanceState);


    /*Button addtagbtn = (Button) getActivity().findViewById(R.id.addtagbtn);
    addtagbtn.setOnClickListener(new addtagbtnlsn(tagnameET,getActivity()));
    Button viewtagbtn = (Button) getActivity().findViewById(R.id.viewtagbtn);
    viewtagbtn.setOnClickListener(new viewtagbtnlsn());*/

}
/*******************************************************************************************************/
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
        mCallback.onButtonClickedMessage(v);
    }

    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListenerMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

    public EditText getMessage() {
        return message;
    }

    public EditText getNumero() {
        return numero;
    }
}