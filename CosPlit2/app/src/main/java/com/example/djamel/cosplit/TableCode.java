package com.example.djamel.cosplit;

import java.util.Date;

public class TableCode {
    private int code;
    private String nomhouse;
    private String fk_admin;

    public TableCode(){}

    public TableCode(int code, String nomhouse,String fk_admin)
    {
        this.code = code;
        this.nomhouse = nomhouse;
        this.fk_admin = fk_admin;

    }

    public int getCode()
    {
        return code;
    }
    public String getNomhouse()    {return nomhouse; }
    public String getFk_admin(){
        return fk_admin;
    }


    public void setCode( int code )
    {
        this.code = code;
    }
    public void setNomhouse( String nomhouse )
    {
        this.nomhouse = nomhouse;
    }
    public void setFk_admin(String fk_admin){this.fk_admin = fk_admin;}




}

