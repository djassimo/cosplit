package com.example.djamel.cosplit;

public class TableCode {
    private int code;
    private String nomhouse;
    private String fk_object;



    public TableCode(){}

    public TableCode(int code, String nomhouse,String fk_object)
    {
        this.code = code;
        this.nomhouse = nomhouse;
        this.fk_object = fk_object;

    }

    public int getCode()
    {
        return code;
    }
    public String getNomhouse()    {return nomhouse; }
    public String getFk_object(){
        return fk_object;
    }


    public void setCode( int code )
    {
        this.code = code;
    }
    public void setNomhouse( String nomhouse )
    {
        this.nomhouse = nomhouse;
    }
    public void setFk_object(String fk_object){this.fk_object = fk_object;}



}

