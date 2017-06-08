/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author vitor
 */
public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Servidor facebook = new Servidor("Facebook");
        
        Cliente thiago = new Cliente("Thiago", facebook);
        Cliente ronistone = new Cliente("Ronistone", facebook);
        Cliente vitor = new Cliente("Vitor", facebook);
        
        thiago.sendMessage(ronistone,"Ronistone, seu lindo!");
        ronistone.sendMessage(thiago,"São seus olhos! lindão!");
        
        facebook.broadcastMessage(ronistone, "Mandando para todos!");
        
        
    }
    
}
