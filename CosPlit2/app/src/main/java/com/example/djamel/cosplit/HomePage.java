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
        int message = intent.getIntExtra(RegisterPage.EXTRA_MESSAGE,0);
        Toast.makeText(HomePage.this,"idregister:"+message,Toast.LENGTH_LONG).show();

    }

}
