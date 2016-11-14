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

public class MainPage extends AppCompatActivity

{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

    }
    public void sendMessage(View view) {
        //Toast.makeText(MainPage.this,"idregister,nnnn:",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainPage.this, SignupActivity.class);

        startActivity(intent);

    }
    }
