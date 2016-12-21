package com.example.djamel.cosplit;

import java.util.Date;


public class TableCost {
    private Double prix;
    private Date date;
    private String userpayement;
    private String categorie;



    public TableCost(){}

    public TableCost(Double prix, Date date, String userpayement, String categorie)
    {
        this.prix = prix;
        this.date = date;
        this.userpayement = userpayement;
        this.categorie = categorie;
    }

    public Double getPrix()
    {
        return prix;
    }

    public Date getDate()
    {
        return date;
    }

    public String getUserpayement()
    {
        return userpayement;
    }

    public String getCategorie()    {return categorie;}

    public void setPrix( Double prix )
    {
        this.prix = prix;
    }

    public void setDate( Date date)
    {
        this.date= date;
    }

    public void setUserPayement( String userpayement )
    {
        this.userpayement= userpayement;
    }

    public void setcategorei( String categorie )
    {
        this.categorie= categorie;
    }
}


