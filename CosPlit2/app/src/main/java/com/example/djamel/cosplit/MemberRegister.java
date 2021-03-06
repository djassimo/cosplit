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
import com.backendless.persistence.BackendlessSerializer;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import static android.R.attr.data;

public class MemberRegister extends AppCompatActivity {
    EditText Editname1, Editname2, Editname3, Editname4;
    BootstrapButton btnInsert1;
    String user ="isuser",message1,rolemsg;
    int code1;
    BackendlessUser backendlessUser = new BackendlessUser();

    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_member_register);

        if(Backendless.UserService.loggedInUser()=="")
        {
            //Toast.makeText(MainPage.this,"imhere",Toast.LENGTH_LONG).show();
        }else
        {
            Intent intent = new Intent(MemberRegister.this, HomePage.class);
            startActivity(intent);
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        rolemsg = bundle.getString("role");
        message1 = bundle.getString("housename");
        code1= bundle.getInt("code");


        Toast.makeText(MemberRegister.this,"votre code est"+code1,Toast.LENGTH_LONG).show();
        //Toast.makeText(MemberRegister.this,"maison importé"+message1,Toast.LENGTH_LONG).show();

        Editname1 = (EditText) findViewById(R.id.Name1);
        Editname2 = (EditText) findViewById(R.id.Name2);
        Editname3 = (EditText) findViewById(R.id.Name3);
        Editname4 = (EditText) findViewById(R.id.Name4);
        btnInsert1 = (BootstrapButton) findViewById(R.id.sendregister);

        sendviewRegister();
    }

    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String nom = Editname1.getText().toString();
                        String prenom = Editname2.getText().toString();
                        String email = Editname3.getText().toString();
                        String password = Editname4.getText().toString();


                        //BackendlessUser backendlessUser = new BackendlessUser();
                        backendlessUser.setPassword(password);
                        backendlessUser.setProperty("name", nom);
                        backendlessUser.setProperty("lastname", prenom);
                        backendlessUser.setEmail(email);
                        backendlessUser.setProperty("role",user);



                        Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                BackendlessSerializer.serializeUserProperties(backendlessUser);
                                String objectId = backendlessUser.getUserId().toString();
                                //Toast.makeText(MemberRegister.this,"assurer role"+user,Toast.LENGTH_LONG).show();
                                //Toast.makeText(MemberRegister.this,"assurer home"+" "+message1,Toast.LENGTH_LONG).show();
                                Intent it = new Intent(MemberRegister.this, HomePage.class);
                                //it.putExtra(EXTRA_MESSAGE,nomhouse);

                                Backendless.Persistence.save(new TableCodeMaison (code1,objectId), new BackendlessCallback<TableCodeMaison>( ) {

                                    @Override
                                    public void handleResponse(TableCodeMaison response) {

                                        //    Toast.makeText(RegisterPage.this, "Contact sauvegardé : ", Toast.LENGTH_SHORT).show();
                                    }

                                });


                                Intent intent = new Intent(MemberRegister.this, LoginMember.class);
                                startActivity(intent);
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                //Toast.makeText(RegisterPage.this,"error registration",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                }
        );
    }
}
