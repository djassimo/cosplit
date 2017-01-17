package com.example.djamel.cosplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AddCost extends AppCompatActivity {
    String e1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);
        TextView cost_category= (TextView)findViewById(R.id.textView21);
        Intent intent = getIntent();
        e1 = intent.getStringExtra("e1");
        cost_category.setText(e1);



    }
}
