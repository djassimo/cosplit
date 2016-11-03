package com.example.djamel.cosplit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by djamel on 31/10/2016.
 */

public class RegisterPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_page);
        db =new MyDataBase(this);

        Editname1=(EditText) findViewById(R.id.Name1);
        Editname2=(EditText) findViewById(R.id.Name2);
        Editname3=(EditText) findViewById(R.id.Name3);
        Editname4=(EditText) findViewById(R.id.Name4);
        btnInsert1=(Button)findViewById(R.id.sendregister);

        sendviewRegister();


         }

    //fonction pour création house et insertion dans la base de données
    public void sendviewRegister() {

        btnInsert.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {
                        //appel de fonction insertion
                        boolean isInserted = db.insertdata(
                                Editname.getText().toString());

                        if(isInserted)
                        {
                            //affichage d"activity de Register
                            Intent intent = new Intent(v.getContext(),RegisterPage.class);
                            startActivity(intent);
                        }
                        else
                            //message pour remplir le nom de la maison
                            Toast.makeText(SignupActivity.this,"Please type in your house name",Toast.LENGTH_LONG).show();

                    }
                }
        );




    }

}
