package com.example.djamel.cosplit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.Iterator;
import java.util.Random;

public class SignupActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.djamel.cosplit";

    EditText Editname;
    BootstrapButton btnInsert;
    String message;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.signup_activity_page);

        //recuperation des champs saisie dans la page signup_activity_page.xml
        btnInsert = (BootstrapButton) findViewById(R.id.sendregister);
        sendviewRegister();
    }

    public void sendviewRegister() {

        btnInsert.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        //affichage d"activity de RegisterPage et passage de id de register vers l'activity RegisterPage
                        Editname = (EditText) findViewById(R.id.Name);
                        message = Editname.getText().toString();
                        Intent intent = new Intent(SignupActivity.this, RegisterPage.class);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                }
        );
    }


}

