package com.example.djamel.cosplit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by djamel on 10/11/2016.
 */

public class HomePage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_page);

        Intent intent = getIntent();
        String message = intent.getStringExtra(RegisterPage.EXTRA_MESSAGE);
        Toast.makeText(HomePage.this,"HouseName:"+message,Toast.LENGTH_LONG).show();


    }

}
