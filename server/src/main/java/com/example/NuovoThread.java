package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class NuovoThread extends Thread{
    private Socket s;
    private Integer n;


    public NuovoThread (Socket s, Integer nBiglietti) {
        this.s = s;
        this.n = nBiglietti;
    }

    public void run () {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String Stringricevuta = "";
            do{
                Stringricevuta = in.readLine();
                
                System.out.println(Thread.currentThread().getName() + " ha digitato " + Stringricevuta);
                if (Stringricevuta.equals("D")) {
                    out.writeBytes(" > Disponibili " + n + " biglietti \n");
                    break;
                } else if (Stringricevuta.equals("A")) {
                    if(n > 0){
                        n--;
                        out.writeBytes(" > Biglietto acquistato\n");
                    }
                    else{
                        out.writeBytes("> Biglietti esauriti \n");
                    }
                    break;
                }
                else if(Stringricevuta.equals("Q")){
                    out.writeBytes("> Bye Bye\n");
                    s.close();
                    break;
                }
            }while(n != 0 || Stringricevuta.equals("Q"));
            
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + "ERRORE ");
            System.exit(1);
        }
    }
}
