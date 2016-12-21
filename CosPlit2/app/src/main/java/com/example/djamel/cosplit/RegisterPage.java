package com.example.djamel.cosplit;

    import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.backendless.Backendless;
    import com.backendless.BackendlessUser;
    import com.backendless.async.callback.AsyncCallback;
    import com.backendless.async.callback.BackendlessCallback;
    import com.backendless.exceptions.BackendlessFault;
    import com.backendless.persistence.BackendlessSerializer;
    import com.beardedhen.androidbootstrap.BootstrapButton;
    import com.beardedhen.androidbootstrap.TypefaceProvider;

    import java.util.Random;

    import javax.xml.validation.Validator;

public class RegisterPage extends AppCompatActivity {



    int code;
    String nomhouse,admin;
    EditText Editname1,Editname2,Editname3,Editname4;
    BootstrapButton btnInsert1;
    BackendlessUser backendlessUser = new BackendlessUser();

    // nouvelle modification
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";

    public int generatecode()
    {
        int r;
        Random resultat;
        resultat= new Random();
        r= (10000+ resultat.nextInt(899999));
        return r;
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.register_activity_page);

        Backendless.initApp( this, APP_ID, SECRET_KEY, VERSION);
        //Button registerButton = (Button) findViewById(R.id.sendregister);



        code = generatecode();

        //recuperation des champs saisie dans la page register_activity_page.xml
        Editname1=(EditText) findViewById(R.id.Name1);
        Editname2=(EditText) findViewById(R.id.Name2);
        Editname3=(EditText) findViewById(R.id.Name3);
        Editname4=(EditText) findViewById(R.id.Name4);
        btnInsert1=(BootstrapButton) findViewById(R.id.sendregister);
        sendviewRegister();

         }


    //fonction pour création Register et insertion dans la base de données:
    public void sendviewRegister() {

        btnInsert1.setOnClickListener(

                new View.OnClickListener()
                {

                    @Override
                    public void onClick (View v)
                    {
                        Intent intent = getIntent();
                        nomhouse = intent.getStringExtra(SignupActivity.EXTRA_MESSAGE);

                        String nom = Editname1.getText().toString();
                        String prenom = Editname2.getText().toString();
                        String email = Editname3.getText().toString();
                        String password = Editname4.getText().toString();
                        admin = "isadmin";


                        backendlessUser.setPassword(password);
                        backendlessUser.setProperty("name",nom);
                        backendlessUser.setEmail(email);
                        backendlessUser.setProperty("lastname",prenom);
                        backendlessUser.setProperty("role",admin);

                        //backendlessUser.setProperty("housename",nomhouse);
                        //backendlessUser.setProperty("code",code);




                        Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>()
                        {
                            @Override
                            public void handleResponse(BackendlessUser response)
                            {
                                //récupération idobject et l"insertion dans la table Tablecode
                                BackendlessSerializer.serializeUserProperties(backendlessUser);
                                String objectId =backendlessUser.getObjectId().toString();
                                admin = backendlessUser.getProperty("role").toString();
                                //Toast.makeText(RegisterPage.this, "ma masison est : "+nomhouse, Toast.LENGTH_SHORT).show();
                                //affichage d"activity de Homepage et passage de id de register vers l'activity HomePage


                                //Toast.makeText(RegisterPage.this, "idobject est : "+name, Toast.LENGTH_SHORT).show();
                                Backendless.Persistence.save(new TableCode (code , nomhouse,objectId), new BackendlessCallback<TableCode>( ) {

                                    @Override
                                    public void handleResponse(TableCode response) {
                                        Intent it = new Intent(RegisterPage.this, HomePage.class);
                                        //it.putExtra(EXTRA_MESSAGE,nomhouse);

                                        Bundle bundle = new Bundle();
                                        bundle.putString("role",admin);
                                        bundle.putString("housename",nomhouse);
                                        it.putExtras(bundle);
                                        startActivity(it);
                                    //    Toast.makeText(RegisterPage.this, "Contact sauvegardé : ", Toast.LENGTH_SHORT).show();
                                    }

                                });



                            }

                            @Override
                            public void handleFault(BackendlessFault fault)
                            {
                                //Toast.makeText(RegisterPage.this,"error registration",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                }
        );
    }
}
