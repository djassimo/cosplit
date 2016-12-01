package com.example.djamel.cosplit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;


public class LoginMember extends AppCompatActivity {
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";
    EditText Editname1,Editname2;
    Button btnInsert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_code);
        //Editname1=(EditText) findViewById(R.id.Name2);
        btnInsert1=(Button)findViewById(R.id.button4);
        sendviewRegister();
    }


    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {

                        String email = Editname1.getText().toString();
                        String password = Editname2.getText().toString();

                        //affichage d"activity de Homepage et passage de id de register vers l'activity HomePage

                        Intent it = new Intent(LoginMember.this, HomePage.class);
                        //it.putExtra(EXTRA_MESSAGE,nomhouse);
                        startActivity(it);




                    }
                }
        );
    }
}

