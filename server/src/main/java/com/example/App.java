package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            //CREO LA PORTA COLLEGATA AL SERVER 3000
            System.out.println("Server avviato");
            ServerSocket server = new ServerSocket(3000);

            int quantita = 5;
            while (true) {
                Socket s = server.accept();

                System.out.println("Il client si è connesso");
                
                NuovoThread t1 = new NuovoThread(s, quantita);
                t1.start();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza");
            System.exit(1);
        }
    }
}
