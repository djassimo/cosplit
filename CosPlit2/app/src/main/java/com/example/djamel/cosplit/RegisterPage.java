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

    MyDataBase db;
    int id;

    EditText Editname1,Editname2,Editname3,Editname4;
    Button btnInsert1;
    protected void onCreate(Bundle savedInstanceState) {
        db =new MyDataBase(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_page);
        id = db.getlastid();
        Toast.makeText(RegisterPage.this, "Please type in your house name:"+id, Toast.LENGTH_LONG).show();
        System.out.print("id hello:"+id);



        Editname1=(EditText) findViewById(R.id.Name1);
        Editname2=(EditText) findViewById(R.id.Name2);
        Editname3=(EditText) findViewById(R.id.Name3);
        Editname4=(EditText) findViewById(R.id.Name4);
        btnInsert1=(Button)findViewById(R.id.sendregister);




        sendviewRegister();


         }

    //fonction pour création house et insertion dans la base de données:
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

                        if(isInserted)
                        {
                            //affichage d"activity de Register
                            //Intent intent = new Intent(v.getContext(),RegisterPage.class);
                            //startActivity(intent);
                            Toast.makeText(RegisterPage.this,"The register is created",Toast.LENGTH_LONG).show();
                        }
                        else
                            //message pour remplir le nom de la maison
                            Toast.makeText(RegisterPage.this,"noooooooooooo",Toast.LENGTH_LONG).show();

                    }
                }
        );



    }

}
