package com.example.djamel.cosplit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannedString;
import android.util.Log;
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
    public ProgressDialog TempDialog;
    CountDownTimer CDT;
    EditText Editname1, Editname2;
    BootstrapButton btnInsert1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.login_member_page);

        //verification de l'etat de connexion de l'utilisateur
        if (Backendless.UserService.loggedInUser() == "") {
            //Toast.makeText(MainPage.this,"imhere",Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(LoginMember.this, HomePage.class);
            startActivity(intent);
        }

        //saisi de mail et de mot passe
        Editname1 = (EditText) findViewById(R.id.Name1);
        Editname2 = (EditText) findViewById(R.id.Name2);

        btnInsert1 = (BootstrapButton) findViewById(R.id.registre);
        sendviewRegister();
    }

    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //recuperation des champs email et password de user
                        final String email = Editname1.getText().toString();
                        final String password = Editname2.getText().toString();

                        //verification des données saisi par l'utilisateur
                        Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {


                            @Override
                            public void handleResponse(BackendlessUser response) {


                                    TempDialog = new ProgressDialog(LoginMember.this);
                                    //TempDialog.setMessage("Please wait...");
                                    TempDialog.setCancelable(false);
                                    TempDialog.setProgress(5);
                                    TempDialog.show();

                                    CDT = new CountDownTimer(5000, 1000) {

                                        public void onTick(long millisUntilFinished) {
                                            TempDialog.setMessage("Connexion en cours \n Please wait..");

                                        }

                                        public void onFinish() {
                                            TempDialog.dismiss();

                                            Intent it = new Intent(LoginMember.this, HomePage.class);
                                            startActivity(it);
                                            //Toast.makeText(MemberCode.this, "votre maison est  !!" + nommaison, Toast.LENGTH_LONG).show();
                                        }
                                    }.start();
                                }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                TempDialog = new ProgressDialog(LoginMember.this);
                                //TempDialog.setMessage("Please wait...");
                                TempDialog.setCancelable(false);
                                TempDialog.setProgress(5);
                                TempDialog.show();

                                CDT = new CountDownTimer(5000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        TempDialog.setMessage("Connexion en cours \n Please wait..");

                                    }

                                    public void onFinish() {
                                        TempDialog.dismiss();
                                        Toast.makeText(LoginMember.this, "Verifie votre email ou mot de passe !!", Toast.LENGTH_LONG).show();
                                    }
                                }.start();
                                //Toast.makeText(LoginMember.this, "not loggin!!", Toast.LENGTH_LONG).show();
                            }
                        }, true);


                    }
                }
        );
    }
}

