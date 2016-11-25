package com.example.djamel.cosplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import static android.R.attr.data;

public class MemberRegister extends AppCompatActivity {
    EditText Editname1,Editname2,Editname3,Editname4;
    Button btnInsert1;


    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);


        Editname1=(EditText) findViewById(R.id.Name1);
        Editname2=(EditText) findViewById(R.id.Name2);
        Editname3=(EditText) findViewById(R.id.Name3);
        Editname4=(EditText) findViewById(R.id.Name4);
        btnInsert1=(Button)findViewById(R.id.sendregister);
        sendviewRegister();
    }
    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {
                        String nom = Editname1.getText().toString();
                        String prenom = Editname2.getText().toString();
                        String email = Editname3.getText().toString();
                        String password = Editname4.getText().toString();

                        Backendless.Persistence.save(new Members (nom, prenom, email, password ), new BackendlessCallback<Members>( ) {

                            @Override
                            public void handleResponse(Members response) {
                                Toast.makeText(MemberRegister.this, "Contact sauvegardé : ", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //affichage d"activity de Homepage et passage de id de register vers l'activity HomePage
                        /*
                        Intent it = new Intent(RegisterPage.this, HomePage.class);
                        it.putExtra(EXTRA_MESSAGE,nomhouse);
                        startActivity(it);
                        */



                    }
                }
        );
    }
}
