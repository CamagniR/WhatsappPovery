/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsapppovery;

import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Riccardo
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Condivisa condivisa=new Condivisa();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci IP del peer del destinatario:");
        String ip= scanner.nextLine();
        
        System.out.println("Inserisci nickname:");
        String nick= scanner.nextLine();
        
       InetAddress address = InetAddress.getByName(ip);
        ThreadInvio threadInvio = new ThreadInvio(address,condivisa);
        threadInvio.start();
        ThreadRicezione threadRiceve = new ThreadRicezione(threadInvio.getSocket(),condivisa);
        threadRiceve.start();
    }
    
}
