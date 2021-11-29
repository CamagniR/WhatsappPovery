/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsapppovery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Riccardo
 */
public class ThreadInvio extends Thread  {
    
    private Condivisa condivisa;
    private InetAddress destinationIPAddress;
    private DatagramSocket udpClientSocket;
    private static int clientport=2003;
    
    
     public ThreadInvio(InetAddress address,Condivisa c) throws SocketException {
        this.destinationIPAddress = address;
        condivisa=c;
        
        // Creo DatagramSocket
        this.udpClientSocket = new DatagramSocket();
        this.udpClientSocket.connect(destinationIPAddress, clientport);
    }
 
    public DatagramSocket getSocket() {
        return this.udpClientSocket;
    }

    public void run() {       
        try {         
            // Input stream da tastiera
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) 
            {
               
                String clientMessage="";
                if (condivisa.getUltimoTag().equals("")) {
                    System.out.println("inserisci il tuo nickname ");
                    clientMessage="c;"+userInput.readLine();
                }
                if (condivisa.getUltimoTag().equals("c")) {
                    System.out.println("Vuoi comunicare con un peer(la)? [y/n]");
                    clientMessage=userInput.readLine();
                     if (clientMessage.equals("y")) {
                    System.out.println("inserisci il tuo nickname? ");
                    String nicknameLocale= userInput.readLine();
                    
                    //invio all'altro peer
                    clientMessage= clientMessage+";"+nicknameLocale;
                    
                }
                }
                
                /*if (condivisa.getUltimoTag().equals("y")) {
                     System.out.println("sei sicuro di voler comunicare? [y/n]");
                    clientMessage=userInput.readLine();
                    
                    
                }*/
                
                if (condivisa.getUltimoTag().equals("y")) {
                
                    
                    System.out.println("vuoi rispondere o ti fai i cavoli tuoi?[m/e]");
                    clientMessage=userInput.readLine();
                 if (clientMessage.equals("m")) {
                     System.out.println("rispondi dai su rispondi cazzo");
                     String rispoMessaggio="";
                     clientMessage="m;"+userInput.readLine();
               }else if(clientMessage.equals("e"))
               {
                    //Stop();
               }
                }
                
                  
                byte[] sendData = new byte[1024];
                sendData = clientMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destinationIPAddress, clientport);
                udpClientSocket.send(sendPacket);
                    
                Thread.yield();
            }
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
