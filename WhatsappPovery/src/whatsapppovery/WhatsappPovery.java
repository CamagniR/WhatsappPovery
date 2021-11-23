
package whatsapppovery;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;


public class WhatsappPovery extends Thread {
private int Port;
private String recIP;
private final static BufferedReader in = new BufferedReader(new   InputStreamReader(System.in));
private String nicknameLocale;
private String nicknameEsterno;

private DatagramSocket server = null;
private DatagramPacket packet = null;

private Scanner scanner = new Scanner(System.in);


public WhatsappPovery( String ip, int port) throws Exception {
    recIP = ip;
    Port = port;
    System.out.println("chat program: IP address: " + recIP + " port " + Port);
    //start();

}

public void run() {
    try {
        // open DatagramSocket to receive
        server = new DatagramSocket(Port);
        // loop forever reading datagrams from the DatagramSocket
        while (true) {
            byte[] buffer = new byte[1500]; // max char length
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        server.receive(packet);
        byte[] dataReceived = packet.getData(); // copia del buffer dichiarato sopra
        String messaggioRicevuto = new String(dataReceived, 0, packet.getLength());
        String[] campi= messaggioRicevuto.split(";");
        String risposta="";    
        
            if (campi[0].equals("c")) {
                System.out.println("Vuoi comunicare con un peer(la)? [y/n]");
                String r= scanner.nextLine();
                if (r.equals("y")) {
                    System.out.println("inserisci il tuo nickname? ");
                    nicknameLocale= scanner.nextLine();
                    
                    //invio all'altro peer
                    risposta= r+";"+nicknameLocale;
                    
                }
                else{
                risposta =r;
                
                }
                sendMessage(risposta);
            }
            
            if (campi[0].equals("y")) {
                
                if (campi[1]!=null) {
                    nicknameEsterno=campi[1];
                }
                
                
            }
            
            
           if (campi[0].equals("m")) {
               
               System.out.println(nicknameEsterno+": "+campi[1]);
               System.out.println("vuoi riondere o ti fai i cavoli tuoi?[m/e]");
               String alFly=scanner.nextLine();
               if (alFly.equals("m")) {
                   System.out.println("rispondi dai su rispondi cazzo");
               String rispoMessaggio="";
               rispoMessaggio=scanner.nextLine();
               sendMessage("m"+";"+rispoMessaggio);
               }else if(alFly.equals("e"))
               {
                    Stop();
               }
               
               
               
           }
           
           if (campi[0].equals("n")) {
                  Stop(); 
           }
          
          
        }
    } catch (SocketException se) {
        //System.err.println("chat error (Socket Closed = good): " + Se.getMessage());
        
        } catch (IOException se) {
        System.err.println("chat error: " + se.getMessage());
    }
}

public void Stop() {
    if (server != null) {
        server.close();
        server = null;
    }
}

    public void setNicknameLocale(String nicknameLocale) {
        this.nicknameLocale = nicknameLocale;
    }

    public void setNicknameEsterno(String nicknameEsterno) {
        this.nicknameEsterno = nicknameEsterno;
    }



public boolean sendMessage(String message) throws IOException {
    try {
        System.out.println("Sending to " + recIP + " socket " + Port + " data: " + message);
        byte[] data = message.getBytes();
        DatagramSocket theSocket = new DatagramSocket();
        DatagramPacket theOutput = new DatagramPacket(data, data.length, InetAddress.getByName(recIP), Port);
        theSocket.send(theOutput);
        
        return true;
    } catch (IOException e) {
        return false;
    }
}


}