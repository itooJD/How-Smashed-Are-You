package com.emptyshit.hsay.gameComponent;

import java.util.Random;

/**
 * Created by Alex on 18.04.2017.
 */

public class EnterWordGame {

    private String[] wordsList = {"OpenGL", "ZEUS","riessig","WINZIG"};
    private String word;

    public EnterWordGame(){
    }

    public String wordToTypeIn(){
        Random rand = new Random();
        this.word = this.wordsList[rand.nextInt(this.wordsList.length)];
        return this.word;
    }

    public boolean compareWords(String input){
        return word.equals(input);
    }

}
