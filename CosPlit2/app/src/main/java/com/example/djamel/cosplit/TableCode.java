package com.example.djamel.cosplit;

/**
 * Created by djamel on 30/11/2016.
 */

public class TableCode {
    public int code;
    public String nomhouse;



    public TableCode(){}

    public TableCode(int code, String nomhouse)
    {
        this.code = code;
        this.nomhouse = nomhouse;

    }

    public int getCode()
    {
        return code;
    }

    public String getHouseName()
    {
        return nomhouse;
    }


    public void setCode( int code )
    {
        this.code = code;
    }

    public void setHousename( String nomhouse )
    {
        this.nomhouse = nomhouse;
    }


}

