package com.leochardon.blackjack.model;

import java.util.EmptyStackException;

public class EmptyDeckException extends Exception {
    public EmptyDeckException(){
        this("Empty deck!");
    }

    public EmptyDeckException(String message){
        super(message);
    }

}
