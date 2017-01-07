package com.example.djamel.cosplit;

public class Tweet {
    private int color;
    private String pseudo;
    private String text;

    public Tweet(int color, String pseudo, String text) {
        this.color = color;
        this.pseudo = pseudo;
        this.text = text;
    }

    public int getColor(){return color;}
    public String getPseudo (){return pseudo;}
    public String getText(){return text;}
    // ...setters
}
