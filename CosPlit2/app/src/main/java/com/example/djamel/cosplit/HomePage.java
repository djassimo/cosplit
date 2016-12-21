package com.example.djamel.cosplit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessSerializer;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.Iterator;
import java.util.Map;




public class HomePage extends AppCompatActivity {
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";
    BootstrapButton button2;
    String curentuserid,codeid;

    BackendlessUser backendlessUser = new BackendlessUser();
    protected void onCreate(Bundle savedInstanceState) {
        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.home_activity_page);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String message = bundle.getString("role");
        String message1 = bundle.getString("housename");

        //Toast.makeText(HomePage.this,"role:"+message,Toast.LENGTH_LONG).show();
        if(message.equalsIgnoreCase("isadmin")) {

            TextView textView = new TextView(HomePage.this);
            textView.setTextSize(40);
            textView.setText(message1);
            ViewGroup layout = (ViewGroup) findViewById(R.id.home_activity_page);
            layout.addView(textView);
        }
        else
        {
            Toast.makeText(HomePage.this,"role:",Toast.LENGTH_LONG).show();
        }




        button2 = (BootstrapButton) findViewById(R.id.button4);
        sendviewRegister();
        //onBackPressed();

    }

    @Override
    public void onBackPressed() {
        finish();    }

    // déconnexion du l'utilisateur :
    public void sendviewRegister() {

        button2.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {

                        Backendless.UserService.logout(new AsyncCallback<Void>() {

                            @Override
                            public void handleResponse(Void response) {

                                Intent intent = new Intent(HomePage.this, MainPage.class);

                                startActivity(intent);
                                Toast.makeText(HomePage.this,"Déconnecté:",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(HomePage.this,"erreur déconnection:",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }
        );
    }

}











/*
    public String Nomhouse()
    {
        AsyncCallback<BackendlessCollection<TableCode>> callback=new AsyncCallback<BackendlessCollection<TableCode>>()
        {
            @Override
            public void handleResponse( BackendlessCollection<TableCode> tablecode )
            {


                Iterator<TableCode> iterator=tablecode.getCurrentPage().iterator();

                while( iterator.hasNext() )
                {
                    TableCode tablecode1 = iterator.next();
                    int name2 = tablecode1.getCode();
                    String housename = tablecode1.getNomhouse();
                    String objhouse = tablecode1.getFk_object();

                    //Toast.makeText(HomePage.this,"HouseName:"+housename,Toast.LENGTH_LONG).show();
                    //Toast.makeText(HomePage.this,"Housecode:"+name2,Toast.LENGTH_LONG).show();
                    Toast.makeText(HomePage.this,"houseIdObject:"+curentuserid,Toast.LENGTH_LONG).show();

                    if(curentuserid==objhouse) {
                        System.out.println("Maison name = " + tablecode1.getNomhouse());
                        System.out.println("maison code = " + tablecode1.getCode());
                        TextView textView = new TextView(HomePage.this);
                        textView.setTextSize(40);
                        textView.setText(housename);

                        ViewGroup layout = (ViewGroup) findViewById(R.id.home_activity_page);
                        layout.addView(textView);
                    }

                }

            }


            @Override
            public void handleFault( BackendlessFault backendlessFault )
            {

            }
        };
        Backendless.Data.of( TableCode.class ).find( callback );
    }*/
