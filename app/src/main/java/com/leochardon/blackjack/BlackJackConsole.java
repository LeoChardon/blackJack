package com.leochardon.blackjack;

import android.util.Log;

import com.leochardon.blackjack.controller.Deck;
import com.leochardon.blackjack.controller.Hand;
import com.leochardon.blackjack.model.Card;
import com.leochardon.blackjack.model.Color;
import com.leochardon.blackjack.model.EmptyDeckException;
import com.leochardon.blackjack.model.Value;

public class BlackJackConsole {
    private static final String TAG = "BLACKJACK CONSOLE";
    BlackJackConsole() {
        Card[] tab = {
                new Card(Value.TWO, Color.HEART),
                new Card(Value.JACK, Color.SPADE)
        };

        for (Card c : tab) {
            Log.e(TAG, "This card is a " + c + " worth " + c.getPoints() + " points.");
            Log.e(TAG, "It's a name " + c.getColorName());
            switch (c.getColorSymbole()) {
                case "\u2665":
                    Log.e(TAG, "symbole: heart");
                    break;
                case "\u2660":
                    Log.e(TAG, "symbole: spade");
                    break;
                case "\u2663":
                    Log.e(TAG, "symbole: club");
                    break;
                case "\u2666":
                    Log.e(TAG, "symbole: diamond");
                    break;
            }
            if (c.getValueSymbole().matches("[JQK]")) {
                Log.e(TAG, "It's a face !");
            } else {
                Log.e(TAG, "It's not a face");
            }
        }

        Deck deck = new Deck(2);
        Log.e(TAG, "Here is the deck" + deck + "\n");
        for (int i = 0; i < 3; i++) {
            try {
                Card c = deck.draw();
                Log.e(TAG, "This card is" + c + " worth " + c.getPoints() + " points");
            } catch (EmptyDeckException e) {
                e.printStackTrace();
            }
        }
        Deck deck2 = new Deck(2);
        Log.e(TAG, "Here is the deck2" + deck2 + "\n");
        Hand hand = new Hand();
        Log.e(TAG, "Your hand is currently :" + hand);
        for (int i = 0; i < 2; i++) {
            try {
                hand.add(deck2.draw());
            } catch (EmptyDeckException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "Your hand is currently: " + hand);
        hand.clear();
        Log.e(TAG, "Your hand is currently: " + hand);
        Log.e(TAG, "Here is the deck2" + deck2 + "\n");
        Log.e(TAG, "============================================" + hand);
        Hand handTest = new Hand();
        handTest.add(
                new Card(Value.AS,Color.CLUB)
        );
        handTest.add(
                new Card(Value.AS,Color.CLUB)
        );
        handTest.add(
                new Card(Value.QUEEN,Color.CLUB)
        );
        handTest.add(
                new Card(Value.TWO,Color.CLUB)
        );
        handTest.add(
                new Card(Value.SEVEN,Color.CLUB)
        );
        handTest.add(
                new Card(Value.TEN,Color.CLUB)
        );
        Log.e(TAG, "Your hand is currently: " + handTest);
        Log.e(TAG, "Your hand is choice: " + handTest.count());
        Log.e(TAG, "Your hand best is: " + handTest.best());

    }
}
