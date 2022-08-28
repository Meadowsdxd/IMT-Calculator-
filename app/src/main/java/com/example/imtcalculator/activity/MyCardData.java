package com.example.imtcalculator.activity;

public class MyCardData {
    private String cardName;
    private Integer cardData;
    private Integer cardImage;

    public MyCardData(String cardName, int cardData, Integer cardImage) {
        this.cardName = cardName;
        this.cardData = cardData;
        this.cardImage = cardImage;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardData() {
        return cardData;
    }

    public void setCardData(Integer cardData) {
        this.cardData = cardData;
    }

    public Integer getCardImage() {
        return cardImage;
    }

    public void setCardImage(Integer cardImage) {
        this.cardImage = cardImage;
    }
}
