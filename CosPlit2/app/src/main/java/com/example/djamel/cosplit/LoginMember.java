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
    String nomhouse,idobj,rolenull="";
    int codeHome;

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
                        String iduser;

                        Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                BackendlessUser curentuser = Backendless.UserService.CurrentUser();
                                if (curentuser != null) {
                                    idobj = curentuser.getObjectId();

                                }


                                //parcourir la table TableCode pour recupererer les Id dans la table codemaison
                                AsyncCallback<BackendlessCollection<TableCodeMaison>> callback = new AsyncCallback<BackendlessCollection<TableCodeMaison>>() {
                                    @Override
                                    public void handleResponse(BackendlessCollection<TableCodeMaison> response) {
                                        Iterator<TableCodeMaison> iterator = response.getCurrentPage().iterator();


                                        while (iterator.hasNext()) {


                                            TableCodeMaison tableCode = iterator.next();
                                            if(idobj.equals(tableCode.getIduser())){
                                                codeHome = tableCode.getIdhouse();


                                            }
                                        }
                                        AsyncCallback<BackendlessCollection<TableCode>> callback1 = new AsyncCallback<BackendlessCollection<TableCode>>() {
                                            @Override
                                            public void handleResponse(BackendlessCollection<TableCode> response) {
                                                Iterator<TableCode> iterator = response.getCurrentPage().iterator();
                                                // Toast.makeText(LoginMember.this, "le code home est !!"+codeHome, Toast.LENGTH_LONG).show();
                                                // nomhouse="";
                                                while (iterator.hasNext()) {


                                                    TableCode tableCode = iterator.next();

                                                    if (codeHome==(tableCode.getCode())) {
                                                        nomhouse = tableCode.getNomhouse();

                                                        //Toast.makeText(LoginMember.this, "nom maison!!"+nomhouse, Toast.LENGTH_LONG).show();

                                                    }
                                                    //Toast.makeText(LoginMember.this, "nom maison1!!"+nomhouse, Toast.LENGTH_LONG).show();
                                                }

                                        if (nomhouse.toString() != "") {
                                            Intent it = new Intent(LoginMember.this, HomePage.class);

                                            Toast.makeText(LoginMember.this, "nom maison2!!" + nomhouse, Toast.LENGTH_LONG).show();

                                            Bundle bundle = new Bundle();
                                            bundle.putString("role", rolenull);
                                            bundle.putString("housename", nomhouse);
                                            it.putExtras(bundle);
                                            startActivity(it);

                                        }
                                            }

                                            @Override
                                            public void handleFault(BackendlessFault fault) {

                                            }
                                        };
                                        Backendless.Data.of(TableCode.class).find(callback1);
                                        Toast.makeText(LoginMember.this, "le code home aaa !!"+codeHome, Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                    }
                                };
                                Backendless.Data.of(TableCodeMaison.class).find(callback);




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

