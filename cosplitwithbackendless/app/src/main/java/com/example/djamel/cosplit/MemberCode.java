package com.example.djamel.cosplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;

import static com.example.djamel.cosplit.MainPage.APP_ID;
import static com.example.djamel.cosplit.MainPage.VERSION;
import static com.example.djamel.cosplit.MemberRegister.SECRET_KEY;

public class MemberCode extends AppCompatActivity {

    EditText Editname1;
    Button btnInsert1;
    String nomperson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_code);
        Editname1=(EditText) findViewById(R.id.Name2);
        btnInsert1=(Button)findViewById(R.id.button2);
        Members members = new Members();
        sendviewRegister();
    }

    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {
                        String code = Editname1.getText().toString();

                        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION );
                        AsyncCallback<BackendlessCollection<UserService>> callback = new AsyncCallback<BackendlessCollection<Member>>()
                        {
                            @Override
                            public void handleResponse( BackendlessCollection<Members> response )
                            {
                                List<Members> firstPage = response.getCurrentPage();
                                for( int i = 0; i < firstPage.size(); i++ )
                                    System.out.println( "name - " + firstPage.get( i ).getName() );
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                            }
                        };
                        Backendless.Persistence.of( UserService.class ).find( callback );

                        //affichage d"activity de Homepage et passage de id de register vers l'activity HomePage
                        /*
                        Intent it = new Intent(RegisterPage.this, HomePage.class);
                        it.putExtra(EXTRA_MESSAGE,nomhouse);
                        startActivity(it);
                        */



                    }

                }
        );
    }
/*
    public void MemberJoinHouse(View view) {
        //Toast.makeText(MainPage.this,"idregister,nn444444nn:",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MemberCode.this, MemberRegister.class);
        startActivity(intent);
    }
*/


}
