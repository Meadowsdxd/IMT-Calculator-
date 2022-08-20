package com.example.imtcalculator.more;

public class Card {
    int id;
    String text;

    public Card(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTtext() {
        return text;
    }

    public void setTtext(String text) {
        this.text = text;
    }
}
