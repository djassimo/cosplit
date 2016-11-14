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

/**
 * Created by djamel on 31/10/2016.
 */

public class RegisterPage extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.djamel.cosplit";
    MyDataBase db;
    int id,idregister;
    EditText Editname1,Editname2,Editname3,Editname4;
    Button btnInsert1;

    protected void onCreate(Bundle savedInstanceState) {
        db =new MyDataBase(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_page);

        //récuperer l"id de register
       idregister = db.getlastidRegister();
        //Toast.makeText(RegisterPage.this, "Please type in your house name:"+id, Toast.LENGTH_LONG).show();
        //System.out.print("id hello:"+id);

        //recuperation de id house passé de l'activity SignupActivity
        Intent intent = getIntent();
        id = intent.getIntExtra(SignupActivity.EXTRA_MESSAGE,0);
        Toast.makeText(RegisterPage.this,"idhouse:"+id,Toast.LENGTH_LONG).show();

        //recuperation des champs saisie dans la page register_activity_page.xml
        Editname1=(EditText) findViewById(R.id.Name1);
        Editname2=(EditText) findViewById(R.id.Name2);
        Editname3=(EditText) findViewById(R.id.Name3);
        Editname4=(EditText) findViewById(R.id.Name4);
        btnInsert1=(Button)findViewById(R.id.sendregister);

        //Appel a la fonction d'insertion de données dans la base données
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
                        //appel de fonction insertion
                        boolean isInserted = db.insertdataRegister(
                                Editname1.getText().toString(),
                                Editname2.getText().toString(),
                                Editname3.getText().toString(),
                                Editname4.getText().toString(),
                                id);
                        //vérification de l'insertion dans la base de données
                        if(isInserted)
                        {
                            //affichage d"activity de Homepage et passage de id de register vers l'activity HomePage
                            Intent it = new Intent(RegisterPage.this, HomePage.class);
                            it.putExtra(EXTRA_MESSAGE,idregister);
                            startActivity(it);

                        }
                        else
                            //message s"il y a un echec d'insertion
                            Toast.makeText(RegisterPage.this,"The register is not created",Toast.LENGTH_LONG).show();

                    }
                }
        );



    }

}
