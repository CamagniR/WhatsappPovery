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
public class mainProva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci IP del peer del destinatario:");
        String r= scanner.nextLine();
        WhatsappPovery chat = new WhatsappPovery(r,2003);
        System.out.println("Inserisci nickname:");
        String nick= scanner.nextLine();
        chat.sendMessage("c"+";"+nick);
        chat.setNicknameLocale(nick);
        chat.run();
    }
    
}
