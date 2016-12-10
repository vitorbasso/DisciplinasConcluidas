/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;
import javax.swing.JPanel;
import java.awt.Color;
import trabalhofinal.settings.Configuracao;
import trabalhofinal.truco.JogoTruco;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author vitor
 */
public class Table extends JPanel{
    private final Configuracao settings;
    private final JogoTruco jogoAtual;
    private int round = 1;
    private int totalRound = 1; 
    private int vez = 0;
    private JPanel carta;
    private final ArrayList<Jogador> players;
    private final Tracker contador;
    private int vezTruco;
    private boolean podeCorrer;
    private int whoWasFirst;
    
    
    public Table(Configuracao config, JogoTruco jogoatual, ArrayList<Jogador> pl, Tracker conta){
        super(new FlowLayout()); 
        settings = config;
        jogoAtual = jogoatual;
        setBackground(Color.GREEN);
        players = pl;
        contador = conta;
        carta = new JPanel(new GridLayout(2,2));
        carta.setBackground(Color.GREEN);
        podeCorrer = true;
        whoWasFirst = 0;
        
        
        
        add(carta);
        
    } 
    
    public void newTable(){
        round = 1;
        totalRound = 1;
        vez = 0;
        vezTruco = 0;
        podeCorrer = true;
        whoWasFirst = 0;
        
    }
    
    public void resetTable(){
        carta.removeAll();
        redraw();
    }
    
    public void setTotalRound(int i){
        totalRound = i;
    }
    
    public int getTotalRound(){
        return totalRound;
    }
    
    public void finishNow(boolean teste){
        if(teste){
            setTotalRound(1);
            setRound(1);
        }
    }
    
    public boolean podeCorrer(){
        return podeCorrer;
    }
    
    public void corri(){
        podeCorrer = false;
    }
    
    public void receberCarta(Cards card, Jogador player){ 
        
        card.setBackground(Color.GREEN);
        
        if(getTotalRound() >= settings.getQuantosJogadores() *3 || contador.gotWinner()){
            carta.add(card);
            finishNow(true);
        }else{
            
            incrementTotalRound();
        
            if(getRound() > settings.getQuantosJogadores()){

                resetTable();
                
                setRound(2);
            }else
                incrementRound();
            
            carta.add(card);
        }
        
        proximoVez();
        
        redraw();
        
        contador.compareCard(card.getPoder(), player.getTeam(), player.getId());
        contador.nextTurn();
        
        jogoAtual.playerUpdate();
        
    }
    
    public void checkWin(){
        if(contador.gotWinner()){
            String winners;
            if(contador.teamWinner() == contador.PLAYER_TEAM){
                winners = settings.getNomeJogador();
            }else{
                winners = "BOT";
            }
            
            JOptionPane.showMessageDialog(jogoAtual, String.format("O time %s VENCEU!\nTime %s: %d Pontos -- Time %s: %d Pontos,", winners, settings.getNomeJogador(), contador.getTeamPlayerScore(), "BOT", contador.getTeamBotScore()));
            JOptionPane.showMessageDialog(jogoAtual,"Começando um novo jogo!");
            
            jogoAtual.newGame(settings);
        }
    }
    
    public void incrementTotalRound(){
        totalRound++;
    }
    
    public int getRound(){
        return round;
    }
    
    public void redraw(){
        validate();
        repaint();
    }
    
    public void incrementRound(){
        round++;
    }
    
    public void setRound(int s){
        round = s;
    }
    
    public void proximoVez(){
        vez++;
        if(settings.getQuantosJogadores() <= vez)
            vez = 0;
        vezTruco = vez;
        for(Jogador player : players){
            if(player.getId() == getVez())
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.YELLOW);
            else
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.GREEN);
        }
    }
    
    public int getVezTruco(){
        return vezTruco;
    }
    
    public void setVezTruco(int i){
        vezTruco = i;
    }
    
    public void proximoVezTruco(){
        
        if(vezTruco > vez)
            vezTruco = vez;
        else
            vezTruco++;
    }
    
    public int getVez(){
        return vez;
    }
    
    public void setVez(int i){
        
        vez = i;
        setVezTruco(i);
        whoWasFirst = i;
        
        for(Jogador player : players){
            if(player.getId() == getVez())
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.YELLOW);
            else
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.GREEN);
        }
        
    }
    
    public int getWhoWasFirst(){
        return whoWasFirst;
    }
    
    public int getProximoVez(){
        int aux = vez;
        aux++;
        if(settings.getQuantosJogadores() <= vez)
            aux = contador.getNextFirstToPlay();
        return aux;
    }
    
}
