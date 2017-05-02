package hangman;

import java.io.*;
import java.net.*;
 
public class Server extends Thread {
    private ServerSocket serverSocket;
    private int port = 5000;
    
    public static void main(String argv[]) throws Exception {
        new Server();
    }
    
    public Server() throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("Il Server è in attesa sulla porta 5000.");
        this.start();
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("In attesa di Connessione.");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintStream out = new PrintStream(clientSocket.getOutputStream(), true);
                out.println("Ciao Acer\n");
                String message = in.readLine();
                System.out.println(message);
                out.flush();
                // chiude gli stream e le connessioni
                out.close();
                in.close();
                clientSocket.close();
            }
            catch(Exception e) {}
        }
    }
}

//class Connect extends Thread
//{
//    private Socket client = null;
//    BufferedReader in = null;
//    PrintStream out = null;
//    
//    public Connect() {}
//    
//    public Connect(Socket clientSocket)
//    {
//        client = clientSocket;
//        try {
//            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            out = new PrintStream(client.getOutputStream(), true);
//        }
//        catch(Exception e1) {
//            try { client.close(); }
//            catch(Exception e) { System.out.println(e.getMessage());}
//            return;
//        }
//        this.start();
//    }
//    
//    @Override
//    public void run() {
//        try {
//            out.println("Ciao Acer\n");
//            String message = in.readLine();
//            System.out.println(message);
//            out.flush();
//            // chiude gli stream e le connessioni
//            out.close();
//            in.close();
//            client.close();
//        }
//        catch(Exception e) {}
//    }
//}