package com.leochardon.blackjack.controller;

import com.leochardon.blackjack.model.Card;
import com.leochardon.blackjack.model.EmptyDeckException;

import java.util.ArrayList;

public class BlackJack {
    private Deck deck;
    private int sabot;
    private Hand playerHand;
    private Hand bankHand;
    boolean isFinished;


    public BlackJack() throws EmptyDeckException {
        deck = new Deck();
        playerHand = new Hand();
        bankHand = new Hand();
        isFinished = false;
        reset();
    }

    public BlackJack(int sabot) throws EmptyDeckException {
        this.sabot = sabot;
        deck = new Deck(sabot);
        playerHand = new Hand();
        bankHand = new Hand();
        isFinished = false;
        reset();
    }

    public int getSabot() { return sabot;}

    public int getDeckLength(){
        return deck.length();
    }

    public String getPlayerHandString() {
        return playerHand.toString();
    }

    public String getBankHandString() {
        return bankHand.toString();
    }

    public int getPlayerBest(){
        return playerHand.best();
    }

    public int getBankBest(){
        return bankHand.best();
    }

    public void reset() throws EmptyDeckException {
        isFinished = false;
        bankHand.clear();
        playerHand.clear();
        bankHand.add(deck.draw());
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
    }

    public ArrayList<Card> getPlayerCardList(){
        ArrayList<Card> origin =  playerHand.getCardList();
        return new ArrayList<Card>(origin);
    }

    public ArrayList<Card> getBankCardList(){
        ArrayList<Card> origin =  bankHand.getCardList();
        return new ArrayList<Card>(origin);
    }

    public boolean isPlayerWinner(){
        int bestPlayer = playerHand.best(); // on calcul en avance les meilleurs scores
        int bestBank = bankHand.best();
        if(isFinished){ // si la partie est finie on entame les test
            if(bestPlayer <= 21){ // cas ou le joueur peut gagner
                if(bestBank < bestPlayer){
                    return true; // il gagne car la bank a un plus petit score
                }
                if(bestBank > 21){
                    return true; // il gagne car la bank a superieur a 21
                }
                else{
                    return false; // il perd car la bank est plus grande que lui
                }
            }else{
                return false; // le joueur perd car son score depasse 21
            }
        }else {
            return false; // il ne se passe rien car le jeu n'est pas finis
        }
    }

    public boolean isBankWinner(){
        int bestPlayer = playerHand.best(); // on calcul en avance les meilleurs scores
        int bestBank = bankHand.best();
        if(isFinished){ // si la partie est finie on entame les test
            if(bestBank <= 21){ // cas ou la bank peut gagner
                if(bestPlayer < bestBank){
                    return true; // elle gagne car le joueur a un plus petit score
                }
                if(bestPlayer > 21){
                    return true; // elle gagne car le joueur a superieur a 21
                }
                else{
                    return false; // elle perd car le joueur est plus grand qu'elle
                }
            }else{
                return false; // la bank perd car son score depasse 21
            }
        }else {
            return false; // il ne se passe rien car le jeu n'est pas finis
        }
    }

    public boolean isGameFinished(){
        return isFinished;
    }

    public void playerDrawAnotherCard() throws EmptyDeckException {
        if(!isFinished){
            playerHand.add(deck.draw());
            if(playerHand.best() >= 21){
                isFinished = true;
            }
        }
    }

    public void bankLastTurn() throws EmptyDeckException{
        if(!isFinished){
            while(bankHand.best() <= playerHand.best()){
                bankHand.add(deck.draw());

            }
            if(bankHand.best() >= 21){
                isFinished = true;
            }
        }
    }


}
