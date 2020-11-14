package com.leochardon.blackjack.model;

public enum Color {

    HEART("\u2665"), // rouge
    SPADE("\u2660"),
    CLUB("\u2663"),
    DIAMOND("\u2666"), // rouge
    ;

    private String symbole;

    Color(String color) {
        this.symbole = color;
    }

    public String getSymbole(){
        return symbole;
    }
}
