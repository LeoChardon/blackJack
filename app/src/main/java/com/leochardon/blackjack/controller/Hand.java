package com.leochardon.blackjack.controller;

import com.leochardon.blackjack.model.Card;
import com.leochardon.blackjack.model.Color;
import com.leochardon.blackjack.model.Value;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.min;

public class Hand {
    ArrayList<Card> handOfCards ;

    public Hand(){
        handOfCards = new ArrayList<>();
    }

    public void add(Card card){
        handOfCards.add(card);
    }

    @Override
    public String toString() {
        return handOfCards.toString()+ " possibilité: "+count().toString()+" meilleur: "+best();
    }

    public void clear(){
        handOfCards.clear();
    }

    public ArrayList<Integer> count(){
        ArrayList<Integer> lists = new ArrayList<>();
        boolean isAs = false;
        int result = 0;
        for (Card c: handOfCards) {
            if(c.getValueSymbole().equals(Value.AS.getSymbole())){
                isAs = true;
            }
            result += c.getPoints();
        }
        lists.add(result);
        if(isAs){
            lists.add(result+10);
        }
        return lists;
    }

    public int best(){
        ArrayList<Integer> lists = count();
        if(lists.size() == 2){
            if(lists.get(0) <= 21 && lists.get(0) < lists.get(1) && lists.get(1) > 21){
                return lists.get(0);
            }else{
                return lists.get(1);
            }
        }
        else{
            return lists.get(0);
        }
    }

    public ArrayList<Card> getCardList(){
        return handOfCards;
    }

}
