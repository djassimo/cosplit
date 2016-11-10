package com.example.djamel.cosplit;

import android.content.Intent;
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

import java.util.Random;

/**
 * Created by djamel on 31/10/2016.
 */

public class SignupActivity extends AppCompatActivity {
public final static String EXTRA_MESSAGE = "com.example.djamel.cosplit";
    MyDataBase db;
    EditText Editname;
    int generatedcode;
    Button btnInsert;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_page);

        db =new MyDataBase(this);

        Editname=(EditText) findViewById(R.id.Name);
        btnInsert=(Button)findViewById(R.id.sendregister);
        generatedcode=generatecode();
        sendviewRegister();


    }
    //fonction de generation de code aléatoire de 6 chiffre
    public int generatecode()
        {
            int r;
            Random resultat;

            resultat= new Random();

            r= (10000+ resultat.nextInt(899999));
            return r;
        }


    //fonction pour création house et insertion dans la base de données
    public void sendviewRegister() {

        btnInsert.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //appel de fonction insertion
                        boolean isInserted = db.insertdata(
                                Editname.getText().toString(),
                                generatedcode);


                        if (isInserted) {
                            //affichage d"activity de Register
                            Toast.makeText(SignupActivity.this, "The House is Created", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignupActivity.this, RegisterPage.class);
                            startActivity(intent);
                        } else
                            //message pour remplir le nom de la maison
                            Toast.makeText(SignupActivity.this, "Please type in your house name", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    }

