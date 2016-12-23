package com.example.djamel.cosplit;

/**
 * Created by djamel on 23/12/2016.
 */

public class TableCodeMaison {


    private int idhouse;
    private String iduser;




    public TableCodeMaison(){}

    public TableCodeMaison( int idhouse,String iduser)
    {

        this.idhouse = idhouse;
        this.iduser = iduser;

    }


    public int getIdhouse()    {return idhouse; }
    public String getIduser(){
        return iduser;
    }


    public void setIdhouse( int idhouse )
    {
        this.idhouse = idhouse;
    }
    public void setIduser(String iduser){this.iduser = iduser;}
}
