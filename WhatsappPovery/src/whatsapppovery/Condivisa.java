/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsapppovery;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo
 */
public class Condivisa {
    
    private String ultimoTag;
    private Boolean checkLetto;
    private Boolean chatIniziata;
    
    public Condivisa() {
        ultimoTag = "";
        checkLetto=false;
        chatIniziata=false;
        
    }
     
    public Condivisa(String ultimoTag) {
        this.ultimoTag = ultimoTag;
    }
    
    public synchronized String getUltimoTag() {
        
        return ultimoTag;
    }

    public synchronized void setUltimoTag(String ultimoTag) {
        this.ultimoTag = ultimoTag;
    }

    public synchronized Boolean getCheckLetto() {
        return checkLetto;
    }

    public synchronized void setCheckLetto(Boolean checkLetto) {
        this.checkLetto = checkLetto;
    }

    public synchronized Boolean getChatIniziata() {
        return chatIniziata;
    }

    public synchronized void setChatIniziata(Boolean chatIniziata) {
        this.chatIniziata = chatIniziata;
    }
   

   
    
    
    
    
    
    
    
    
    
}
