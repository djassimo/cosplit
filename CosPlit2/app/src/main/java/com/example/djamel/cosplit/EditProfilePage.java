package com.example.djamel.cosplit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;


/**
 * Created by abbassi on 30/12/2016.
 */

public class EditProfilePage extends AppCompatActivity {
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";
    BootstrapEditText fisrtname, lastname, password;
    String nom, prenom, pass;
    BootstrapButton btn;
    BackendlessUser backendlessUser = new BackendlessUser();
    BackendlessUser curentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_page);


        btn = (BootstrapButton) findViewById(R.id.edit);
        curentuser = Backendless.UserService.CurrentUser();

        String nom1 = curentuser.getEmail();
        //Toast.makeText(EditProfilePage.this, "User has been updated"+nom1, Toast.LENGTH_LONG).show();
        //  prenom = lastname.getText().toString();
        //  pass = password.getText().toString();
        editProfile();
    }

    public void editProfile() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fisrtname = (BootstrapEditText) findViewById(R.id.FirstName);
                        lastname = (BootstrapEditText) findViewById(R.id.LastName);
                        password = (BootstrapEditText) findViewById(R.id.Password);


                        nom = fisrtname.getText().toString();
                        prenom = lastname.getText().toString();
                        pass = password.getText().toString();


                        curentuser.setProperty("lastname", prenom);
                        curentuser.setProperty("name", nom);
                        curentuser.setPassword(pass);

                        Backendless.UserService.update(curentuser, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Toast.makeText(EditProfilePage.this, "User has been updated", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(EditProfilePage.this, "not update", Toast.LENGTH_LONG).show();

                            }
                        });

                        // Toast.makeText(EditProfilePage.this, "User has been updated" + nom, Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}