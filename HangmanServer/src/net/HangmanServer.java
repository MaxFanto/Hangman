/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import hangman.Hangman;
import hangman.Player;
import java.io.IOException;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {

    public static void main(String[] args) throws IOException {
        Hangman game = new Hangman();
        Player player = new NetPlayer();
        game.playGame(player);
    }
    
}
