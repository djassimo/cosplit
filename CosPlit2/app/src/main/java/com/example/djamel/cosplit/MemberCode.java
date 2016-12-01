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
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Iterator;


public class MemberCode extends AppCompatActivity {

    EditText Editname1;
    Button btnInsert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_code);
        Editname1=(EditText) findViewById(R.id.Name2);
        btnInsert1=(Button)findViewById(R.id.sendcode);
        sendviewRegister();
    }

    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {
                        final String code1 = Editname1.getText().toString();


                        /*
                        Backendless.Data.of( BackendlessUser.class ).find( new AsyncCallback<BackendlessCollection<BackendlessUser>>()
                        {
                            @Override
                            public void handleResponse( BackendlessCollection<BackendlessUser> users )
                            {
                                Iterator<BackendlessUser> userIterator = users.getCurrentPage().iterator();

                                while( userIterator.hasNext() )
                                {
                                    BackendlessUser user = userIterator.next();
                                    //System.out.println( "Email - " + user.getEmail() );
                                    //System.out.println( "User ID - " + user.getUserId() );
                                    System.out.println( "code maison - " + user.getProperty( "code" ) );
                                    System.out.println( "code maison - " + user.getProperty( "housename" ) );

                                    System.out.println( "============================" );
                                    //Toast.makeText(MemberCode.this,"inséré : "+code1,Toast.LENGTH_LONG).show();
                                    //Toast.makeText(MemberCode.this,"inséré : ",Toast.LENGTH_LONG).show();

                                    if(code1.equals(user.getProperty("code").toString())){
                                        //Toast.makeText(MemberCode.this,"le bon code est la!!",Toast.LENGTH_LONG).show();
                                        Intent it = new Intent(MemberCode.this, HomePage.class);
                                        startActivity(it);
                                    }
                                    else
                                    {
                                        //Toast.makeText(MemberCode.this,"erreur dans le code !!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void handleFault( BackendlessFault backendlessFault )
                            {
                                System.out.println( "Server reported an error - " + backendlessFault.getMessage() );
                            }
                        }

                        );*/

                        AsyncCallback<BackendlessCollection<TableCode>> callback=new AsyncCallback<BackendlessCollection<TableCode>>()
                        {
                            @Override
                            public void handleResponse( BackendlessCollection<TableCode> tablecode )
                            {


                                Iterator<TableCode> iterator=tablecode.getCurrentPage().iterator();

                                while( iterator.hasNext() )
                                {
                                    TableCode tableCode=iterator.next();
                                    //System.out.println( "Maison name = " + tableCode.getHouseName() );
                                    System.out.println( "maison code = " + tableCode.getCode() );


                                    if(code1.equals(tableCode.getCode())){
                                        Toast.makeText(MemberCode.this,"le bon code est la!!",Toast.LENGTH_LONG).show();
                                        Intent it = new Intent(MemberCode.this, HomePage.class);
                                        startActivity(it);
                                    }
                                    else
                                    {
                                        Toast.makeText(MemberCode.this,"erreur dans le code !!",Toast.LENGTH_LONG).show();
                                    }

                                }

                            }

                            @Override
                            public void handleFault( BackendlessFault backendlessFault )
                            {

                            }
                        };

                        Backendless.Data.of( TableCode.class ).find( callback );

                    }

                }

        );
    }

}
