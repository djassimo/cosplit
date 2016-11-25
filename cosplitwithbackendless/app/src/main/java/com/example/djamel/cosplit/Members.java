package com.example.djamel.cosplit;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;

/**
 * Created by djamel on 24/11/2016.
 */

public class Members {

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public Members(){}
    public Members(String firstname, String lastname, String email, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getName()
    {
        return firstname;
    }
    public void setName( String name ) {
        this.firstname = firstname;
    }
}
