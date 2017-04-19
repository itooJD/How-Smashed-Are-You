package com.emptyshit.hsay.gameComponent;

import java.util.Random;

/**
 * Created by Alex on 18.04.2017.
 */

public class EnterWordGame {

    private String[] wordsList;
    private Random rand;
    private String word;
    private String input;

    public EnterWordGame(){

    }

    public String wordToTypeIn(){
        int number = rand.nextInt(this.wordsList.length);
        word = wordsList[number];
        return word;
    }

    public boolean compareWords(String input){
        if (word.equals(input)){
            return true;
        }
        else {
            return false;
        }
    }

}
