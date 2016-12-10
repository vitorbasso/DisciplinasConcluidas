/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Icon;

/**
 *
 * @author vitor
 */
public class Cards extends JButton{
    
    private final String naipe;
    private final int poder;
    private int poderAtual;
    private final String nome;
    private boolean flipped = false;
    private final Icon cardFace;
    private final Icon cardBack;
    private boolean inPlayerHand = false;
    
    
    public Cards(String na, int po){
        cardFace = new ImageIcon(getClass().getResource("cartasPNG/" + po + na + ".png"));
        cardBack = new ImageIcon(getClass().getResource("cartasPNG/costasCarta.png"));
        setIcon(cardBack);
        naipe = na;
        poder = po;
        
        
        if(po >= 5 && po <=6){
            nome = String.format("%d de %s", (po-3), naipe);
        }else if(po > 6){
            switch (po){
                    case 7:
                        nome = "Sete de Ouros";
                        break;
                    case 8:
                        nome = "Espadilhas";
                        break;
                    case 9:
                        nome = "Sete de Copas";
                        break;
                    default:
                        nome = "Zap";
            }
        }else{
            String aux;
            switch (po){
                    case 1:
                        aux = "Dama";
                        break;
                    case 2:
                        aux = "Valete";
                        break;
                    case 3:
                        aux = "Rei";
                        break;
                    default:
                        aux = "Ás";
            }
            nome = String.format("%s de %s", aux, naipe);
        }
         
        setToolTip();
        setBorderPainted(false);
        setFocusPainted(false);
        
    }
    
    public int getPoder(){
        return poder;
    }
    
    public String getNome(){
        return nome;
    }
    
    public boolean isFlipped(){
        return flipped;
    }
    
    public void flip(){
        if(flipped){
            setIcon(cardBack);
            poderAtual = 0;
        }
        else{
            setIcon(cardFace);
            poderAtual = poder;
        }
        flipped = !flipped;
        setToolTip();
    }
    
    public void setToolTip(){
        String aux = "";
        if(flipped)
            aux = nome;
        else
            aux = "Carta virada";
        
        setToolTipText(String.format("%s - poderAtual = %d", aux, poderAtual));
    }
    
    public boolean isInPlayerHand(){
        return inPlayerHand;
    }
    
    public void setInPlayerHand(boolean state){
        inPlayerHand = state;
    }
    
}
