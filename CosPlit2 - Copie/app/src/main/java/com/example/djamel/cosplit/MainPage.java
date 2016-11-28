package com.example.djamel.cosplit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;

public class MainPage extends AppCompatActivity{
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

    }
    public void sendMessage(View view) {
        //Toast.makeText(MainPage.this,"idregister,nn444444nn:",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainPage.this, SignupActivity.class);
        startActivity(intent);
    }
    public void joinHouse(View view) {
        //Toast.makeText(MainPage.this,"idregister,nn444444nn:",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainPage.this, MemberCode.class);
        startActivity(intent);
    }
    public void LoginMember(View view) {
        //Toast.makeText(MainPage.this,"idregister,nn444444nn:",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainPage.this, HomePage.class);
        startActivity(intent);
    }
    }
