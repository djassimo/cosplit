package com.example.djamel.cosplit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannedString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.Iterator;


public class LoginMember extends AppCompatActivity {
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";
    EditText Editname1, Editname2;
    BootstrapButton btnInsert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.login_member_page);
        Editname1 = (EditText) findViewById(R.id.Name1);
        Editname2 = (EditText) findViewById(R.id.Name2);

        btnInsert1 = (BootstrapButton) findViewById(R.id.registre);
        sendviewRegister();

        if (Backendless.UserService.loggedInUser() == "") {
            //Intent intent = new Intent(LoginMember.this, LoginMember.class);
            //startActivity(intent);
        } else {
            Toast.makeText(LoginMember.this, "il faut que tu connecté toi !!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(LoginMember.this, MainPage.class);
        startActivity(i);
    }

    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final String email = Editname1.getText().toString();
                        final String password = Editname2.getText().toString();

                        Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                BackendlessUser curentuser = Backendless.UserService.CurrentUser();
                                if (curentuser != null) {
                                    String idobj = curentuser.getObjectId();
                                }
                                Intent it = new Intent(LoginMember.this, HomePage.class);
                                //it.putExtra(EXTRA_MESSAGE,nomhouse);
                                startActivity(it);
                                //Toast.makeText(LoginMember.this,"connecté !!",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(LoginMember.this, "not loggin!!", Toast.LENGTH_LONG).show();
                            }
                        }, true);


                    }
                }
        );
    }
}

