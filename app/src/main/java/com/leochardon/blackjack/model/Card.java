package com.leochardon.blackjack.model;

public class Card {
    private Value value;
    private Color color;

    public Card(Value value, Color color){
        this.value = value;
        this.color = color;
    }

    @Override
    public String toString(){
        return getValueSymbole()+getColorSymbole();
    }

    public String getColorSymbole(){
        return color.getSymbole();
    }

    public String getColorName(){
        if(color.equals(Color.CLUB)){
            return "club";
        }
        if(color.equals(Color.DIAMOND)){
            return "diamond";
        }
        if(color.equals(Color.HEART)){
            return "heart";
        }
        if(color.equals(Color.SPADE)){
            return "spade";
        }
        else{
            return "noColor";
        }
    }

    public String getValueSymbole(){
        return value.getSymbole();
    }

    public String getCardFullName(){
        return "card_"+getColorName()+"_"+getValueSymbole().toLowerCase();
    }

    public int getPoints(){
        return value.getPoints();
    }


}
