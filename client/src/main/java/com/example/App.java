package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket s = new Socket("localhost", 3000);
            System.out.println("Connessione effettuata");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            String tipo = " ";
            String risposta = " ";

            do{
                System.out.println("> Digita D per visualizzare la disponibilità,A per acquistare un biglietto o Q per uscire:");
                tipo = scanner.nextLine();
                out.writeBytes(tipo + "\n");
                risposta = in.readLine();               
                System.out.println(risposta);
            }while(risposta.equals("Q"));


            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong");
            System.exit(1);
        }
    }
}