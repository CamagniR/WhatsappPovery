/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsapppovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo
 */
public class ThreadRicezione extends Thread {
    
    
    private Condivisa condivisa;
    private DatagramSocket udpClientSocket;

    public ThreadRicezione(DatagramSocket ds, Condivisa c) throws Exception {
        this.udpClientSocket = ds;
        condivisa=c;
    }


    public void run() {
        
        byte[] receiveData = new byte[1024];
        
        while (true) {            
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                
                udpClientSocket.receive(receivePacket);
            } catch (IOException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                String messaggioRicevuto =  new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] campi= messaggioRicevuto.split(";");
                condivisa.setUltimoTag(campi[0]);
                
                
                System.out.println(messaggioRicevuto + "\"\n");
                
                Thread.yield();
            } 
           
         
        }
    
}
