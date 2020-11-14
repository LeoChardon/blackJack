package com.leochardon.blackjack.controller;

import com.leochardon.blackjack.model.Card;
import com.leochardon.blackjack.model.Color;
import com.leochardon.blackjack.model.EmptyDeckException;
import com.leochardon.blackjack.model.Value;

import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> listOfCards = new ArrayList<>();

    public Deck(){
        this(3);
    }

    public Deck(int sabot){
        for (int i = 0; i < sabot; i++) {
            for(Color c: Color.values()){
                for (Value v:Value.values()) {
                    listOfCards.add(new Card(v,c));
                }
            }
        }
        Collections.shuffle(listOfCards);

    }

    public Card draw() throws EmptyDeckException {
        if(listOfCards.isEmpty()){
            throw new EmptyDeckException();
        }
        Card card = listOfCards.get(0);
        listOfCards.remove(0);
        return card;
    }

    public int length(){
        return listOfCards.size();
    }

    @Override
    public String toString(){
        return listOfCards.toString();
    }


}
