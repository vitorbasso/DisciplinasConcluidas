/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;


/**
 *
 * @author vitor
 */
public class Baralho extends ArrayList<Cards>{
    
    private final String Naipes[] = {"Paus", "Espadas", "Copas", "Ouros"};
    
    private void fillDeck(){
        for(int i = 1; i <= 6; i++){
            for(String naipe : Naipes){
                if(naipe == "Espadas" && i == 4)
                    this.add(new Cards(naipe, 8));
                else
                    this.add(new Cards(naipe, i));
            }
        }
        this.add(new Cards("Ouros", 7));
        this.add(new Cards("Copas", 9));
        this.add(new Cards("Paus", 10));
    }
    
    private void shuffleDeck(){
        Collections.shuffle(this);
    }
    
    public Cards getCard() throws IndexOutOfBoundsException{
        try{
            return remove(0);
        }catch(IndexOutOfBoundsException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
            throw exception;
        }
    }
    
    public void putCard(Cards carta){
        add(carta);
    }
    
    public void resetDeck(){
        clear();
        fillDeck();
        shuffleDeck();
    }
    
    public void qntsCartas(){
        int resultado = 0;
        for(Cards carta : this){
            resultado++;
        }
        System.out.println(resultado);
    }
    
}
