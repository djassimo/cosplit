package com.example.djamel.cosplit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.backendless.persistence.BackendlessDataQuery;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.sql.RowId;
import java.util.Iterator;
import java.util.Map;

import static com.example.djamel.cosplit.LoginMember.VERSION;
import static com.example.djamel.cosplit.MemberRegister.APP_ID;
import static com.example.djamel.cosplit.RegisterPage.SECRET_KEY;


public class MemberCode extends AppCompatActivity {

    EditText Editname1;
    BootstrapButton btnInsert1;
    private  ProgressDialog progressBar;
    public ProgressDialog TempDialog;
    CountDownTimer CDT;
    String user = "isuser",nommaison;

    //private int progressBarStatus = 0;
    //private Handler progressBarbHandler = new Handler();
    //private long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_member_code);

        //initialisation de backendless
        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

        Editname1 = (BootstrapEditText) findViewById(R.id.Name2);
        btnInsert1 = (BootstrapButton) findViewById(R.id.sendregister);

        //verification si l'utilisateur est déja en session connecté ou pas :
        if(Backendless.UserService.loggedInUser()=="")
        {
            //Toast.makeText(MainPage.this,"imhere",Toast.LENGTH_LONG).show();
        }else
        {
            Intent intent = new Intent(MemberCode.this, HomePage.class);
            startActivity(intent);
        }
        sendviewRegister();

    }
    //lors du clic sur le bouton physique de retour
    @Override
    public void onBackPressed() {
        Intent i = new Intent(MemberCode.this, MainPage.class);
        startActivity(i);
    }

    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(final View v) {
                        final int code1 = Integer.parseInt(Editname1.getText().toString());

                        AsyncCallback<BackendlessCollection<TableCode>> callback = new AsyncCallback<BackendlessCollection<TableCode>>() {
                            @Override
                            public void handleResponse(BackendlessCollection<TableCode> tablecode) {
                                Iterator<TableCode> iterator = tablecode.getCurrentPage().iterator();

                                nommaison ="";
                                String IdHome = "";

                                while (iterator.hasNext()) {
                                    TableCode tableCode = iterator.next();
                                    if (code1 == (tableCode.getCode())) {
                                        nommaison = tableCode.getNomhouse();
                                        IdHome = tableCode.getFk_admin();
                                    }
                                }

                                if(nommaison.toString() !=""){
                                    TempDialog = new ProgressDialog(MemberCode.this);
                                    TempDialog.setCancelable(false);
                                    TempDialog.setProgress(5);
                                    TempDialog.show();

                                    CDT = new CountDownTimer(5000, 1000)
                                    {
                                        public void onTick(long millisUntilFinished)
                                        {
                                            TempDialog.setMessage("Please wait.." );
                                        }

                                        public void onFinish()
                                        {
                                            TempDialog.dismiss();
                                            Intent it = new Intent(MemberCode.this, MemberRegister.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("role",user);
                                            bundle.putString("housename",nommaison);
                                            bundle.putInt("code",code1);
                                            it.putExtras(bundle);
                                            startActivity(it);
                                        }
                                    }.start();
                                }
                                else{
                                    TempDialog = new ProgressDialog(MemberCode.this);
                                    TempDialog.setCancelable(false);
                                    TempDialog.setProgress(5);
                                    TempDialog.show();

                                    CDT = new CountDownTimer(5000, 1000)
                                    {
                                        public void onTick(long millisUntilFinished)
                                        {
                                            TempDialog.setMessage("Please wait.." );
                                        }

                                        public void onFinish()
                                        {
                                            TempDialog.dismiss();
                                            Toast.makeText(MemberCode.this, "code FAUX !!", Toast.LENGTH_LONG).show();
                                        }
                                    }.start();

                                }
                            }
                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {

                            }
                        };
                        Backendless.Data.of(TableCode.class).find(callback);
                    }

                }
        );
    }

}
