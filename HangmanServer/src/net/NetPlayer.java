/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package net;

import hangman.Game;
import hangman.GameResult;
import hangman.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MassimilianoFanto
 */
public class NetPlayer extends Player {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port = 5000;
    BufferedReader in;
    PrintWriter out;

    public NetPlayer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Il Server è in attesa sulla porta 5000.");
        System.out.println("In attesa di Connessione.");
        clientSocket = serverSocket.accept();
        System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }
    
    

    @Override
    public char chooseLetter(Game game) {
        char lettera = 0;
        try {
            lettera = in.readLine().charAt(0);
            System.out.println(lettera);
            if (game.getResult() == GameResult.FAILED) {
                out.println("finish");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return lettera;
    }

    @Override
    public void update(Game game) {
        switch(game.getResult()) {
            case FAILED:
                printBanner("Hai perso!  La parola da indovinare era '" +
                            game.getSecretWord() + "'");
                break;
            case SOLVED:
                printBanner("Hai indovinato!   (" + game.getSecretWord() + ")");
                break;
            case OPEN:
                int rem = Game.MAX_FAILED_ATTEMPTS - game.countFailedAttempts();
                System.out.print("\n" + rem + " tentativi rimasti\n");
                System.out.println(this.gameRepresentation(game));
                System.out.println(game.getKnownLetters());
                break;
        }
    }
    
    private String gameRepresentation(Game game) {
        int a = game.countFailedAttempts();
        
        String s = "   ___________\n  /       |   \n  |       ";
        s += (a == 0 ? "\n" : "O\n");
        s += "  |     " + (a == 0 ? "\n" : (a < 5
                ? "  +\n"
                : (a == 5 ? "--+\n" : "--+--\n")));
        s += "  |       " + (a < 2 ? "\n" : "|\n");
        s += "  |      " + (a < 3 ? "\n" : (a == 3 ? "/\n" : "/ \\\n"));
        s += "  |\n================\n";
        return s;
    }
    
    private void printBanner(String message) {
        System.out.println("");
        for (int i = 0; i < 80; i++)
            System.out.print("*");
        System.out.println("\n***  " + message);
        for (int i = 0; i < 80; i++)
            System.out.print("*");
        System.out.println("\n");
    }
    
}
