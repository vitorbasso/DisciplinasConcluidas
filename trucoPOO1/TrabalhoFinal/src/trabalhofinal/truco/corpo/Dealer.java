/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;
import java.util.ArrayList;
/**
 *
 * @author vitor
 */
public class Dealer {
    
    private Baralho baralho;
    
    public Dealer(){
        baralho = new Baralho();   
        baralho.resetDeck();
        baralho.qntsCartas();
    }
    
    public void giveHand(ArrayList<Jogador> players) throws IndexOutOfBoundsException{
        try{
            Cards carta;
            for(Jogador player : players){
                player.freeHand();
                for(int count = 1; count <= 3; count++){
                    carta = baralho.getCard();
                    carta.setInPlayerHand(player.isPlayer());
                    player.receiveCard(carta);
                }
                player.buildHand();
            }
        }catch(IndexOutOfBoundsException indexOutOfBounds){
            System.err.println("Problema na distribuicao das cartas");
            throw indexOutOfBounds;
        }
    }
    
    public void shuffle(){
        baralho.resetDeck();
    }
    
    
    public void resetDealer(){
        baralho.resetDeck();
    }
    
}
