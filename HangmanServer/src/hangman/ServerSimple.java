/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MassimilianoFanto
 */
public class ServerSimple {
    private ServerSocket serverSocket;
    private int port = 5000;
    
    public static void main(String argv[]) throws Exception {
        new Server();
    }

    public ServerSimple() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Il Server è in attesa sulla porta 5000.");
        
        try {
                System.out.println("In attesa di Connessione.");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());
            while(true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintStream out = new PrintStream(clientSocket.getOutputStream(), true);
                out.println("Ciao Acer\n");
                String message = in.readLine();
                System.out.println(message);
                out.flush();
            }
                // chiude gli stream e le connessioni
//                out.close();
//                in.close();
//                clientSocket.close();
            }
            catch(Exception e) {}
    }
    
    
    
}
