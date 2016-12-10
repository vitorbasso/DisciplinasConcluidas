/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;
import trabalhofinal.settings.Configuracao;
import trabalhofinal.truco.JogoTruco;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Jogador extends JPanel{
    
    private String name;
    private final Configuracao settings;
    private final JogoTruco jogoAtual;
    private ArrayList<Cards> hand;
    private JPanel painelMaoJogador;
    private JPanel painelOpcoesJogador;
    private final JButton trucoButton;
    private final JButton correButton;
    private final JButton aceitaButton;
    private final JPanel painelInfo;
    private JPanel painelCartas;
    private JLabel infoJogador;
    private int pontos;
    private final boolean isPlayer;
    private final boolean isPlayerTeam;
    private final Table mesa;
    private final Jogador self = this;
    private final int id;
    private final int whichTeam;
    private final Tracker contador;
    private int trucoTimes; 
    
    
    public Jogador(Configuracao config, JogoTruco jogo,Table m, boolean isIt, boolean isPT, int jogadorID, Tracker cont){
        super(new BorderLayout());
        isPlayer = isIt;
        settings = config;
        jogoAtual = jogo;
        pontos = 0;
        hand = new ArrayList<>();
        mesa = m;
        id = jogadorID;
        painelCartas = new JPanel(new GridLayout(1,3));
        painelMaoJogador = new JPanel(new BorderLayout());
        painelOpcoesJogador = new JPanel(new GridLayout(1,3,1,1));
        painelInfo = new JPanel(new FlowLayout());
        isPlayerTeam = isPT;
        contador = cont;
        setName(settings.getNomeJogador());
        if(isPlayerTeam())
            whichTeam = 0;
        else
            whichTeam = 1;
        infoJogador = new JLabel();
        if(jogadorID == 0)
            settings.setNomeJogador(getName());
        
        trucoButton = new JButton ("TRUCO!");
        correButton = new JButton ("Corre");
        aceitaButton = new JButton("Desce");
        
        painelOpcoesJogador.add(trucoButton);
        painelOpcoesJogador.add(aceitaButton);
        painelOpcoesJogador.add(correButton);
        
        trucoButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(mesa.getVezTruco() == getId() && contador.getTimesTruco() < 4){
                            if(contador.isAskedTruco()){
                                if(contador.getTimesTruco() < 3){
                                    contador.truco();
                                    int aux = contador.getTurnValue() + 3;
                                    JOptionPane.showMessageDialog(jogoAtual, String.format("É %d !!", aux));
                                    mesa.proximoVezTruco();
                                }
                            }else{
                                mesa.proximoVezTruco();
                                JOptionPane.showMessageDialog(jogoAtual, "TRUCO!");
                            }
                             
                            contador.pedirTruco();
                        }
                    }
                }
        );
        
        aceitaButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(contador.isAskedTruco() && mesa.getVezTruco() == getId()){
                            JOptionPane.showMessageDialog(jogoAtual, "DESCE!");
                            contador.aceitarTruco();
                        }
                    }
                }
        );
        
        correButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(true){
                            
                            JOptionPane.showMessageDialog(jogoAtual, "Com essa mão não dá!");
                            if(whichTeam == contador.PLAYER_TEAM)
                                contador.giveWin(contador.BOT_TEAM);
                            else
                                contador.giveWin(contador.PLAYER_TEAM);
                            
                            contador.endRound();
                            mesa.corri();
                            mesa.finishNow(true);
                            mesa.setVez(0);
                            jogoAtual.playerUpdate();
                            painelCartas.validate();
                            painelCartas.repaint();
                        }
                    }
                    
                }
        );
        
        painelInfo.add(infoJogador);
        
        painelMaoJogador.setBackground(Color.BLUE);
        painelOpcoesJogador.setBackground(Color.BLUE);
        painelCartas.setBackground(Color.RED);
        
        this.setBackground(Color.BLUE);
        this.add(painelInfo, BorderLayout.NORTH);
        this.add(painelMaoJogador, BorderLayout.CENTER);
        this.add(painelOpcoesJogador, BorderLayout.SOUTH);
        
        buildHand();
        updateInfo();
    }
    
    public ArrayList<Cards> getHand(){
        return hand;
    } 
    
    public void buildHand(){
        painelCartas.removeAll();
        painelMaoJogador.removeAll();
        
        for(Cards carta : hand){
            carta.setBackground(Color.GREEN);
            painelCartas.add(carta);
        }
        
        for(Cards carta : hand){
            carta.addActionListener(
                    new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent event){
                            if(!carta.isFlipped() && settings.getCheat() && !carta.isInPlayerHand())
                                carta.flip();
                            else if(id == mesa.getVez() && !contador.isAskedTruco()){
                                for(Jogador play : jogoAtual.getPlayers()){
                                    if(play.getId() == mesa.getProximoVez() && play.getId() != mesa.getWhoWasFirst())
                                        for(Cards carta1 : play.getHand()){
                                            carta1.setBackground(Color.YELLOW);
                                        }
                                    else
                                        for(Cards carta : hand){
                                            carta.setBackground(Color.GREEN);
                                        }
                                }
                                if(!carta.isFlipped())
                                    carta.flip();
                                painelCartas.remove(carta);
                                hand.remove(carta);
                                mesa.receberCarta(carta, self);
                                if(contador.finishEarly()){
                                    mesa.finishNow(true);
                                    mesa.setVez(0);
                                }else
                                    //mesa.proximoVez(); 
                                
                                painelCartas.validate();
                                painelCartas.repaint();
                            }
                        }
                    }
            );
        }
        
        painelMaoJogador.add(new Placeholder(), BorderLayout.NORTH);
        painelMaoJogador.add(painelCartas, BorderLayout.CENTER);
        painelMaoJogador.add(new Placeholder(), BorderLayout.SOUTH);
        
        validate();
        repaint();
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public void setName(String nome){
        name = nome;   
    }
    
    public int getTeam(){
        return whichTeam;
    }
    
    public void receiveCard(Cards carta){
        if(isPlayer)
            carta.flip();
        hand.add(carta);
        
    }
    
    public int getId(){
        return id;
    }
    
    public void updateInfo(){
        String auxNome;
        String auxTime;
        int auxPontos;
        if(isPlayer()){
            auxNome = settings.getNomeJogador();
            auxTime = auxNome;
            auxPontos = contador.getTeamPlayerScore();
        }
        else{
            auxNome = "BOT" + getId();
            if(isPlayerTeam()){
                auxTime = settings.getNomeJogador();
                auxPontos = contador.getTeamPlayerScore();
            }else{
                auxTime = auxNome;
                auxPontos = contador.getTeamBotScore();
            }
        }
        
        infoJogador.setText(String.format("Time: %s. Jogador: %s - Rodada: %d . (%d pontos na partida)", auxTime, auxNome, contador.getRound(), auxPontos));
        validate();
        repaint();
    } 
    
    public int getPontos(){
        return pontos;
    }
    
    public void increasePontos(int po){
        pontos += po;
        updateInfo();
    }
    
    public boolean isPlayer(){
        return isPlayer;
    }
    
    public boolean isPlayerTeam(){
        return isPlayerTeam;
    }
    
    public void freeHand(){
        hand.clear();
    }
    
    private class Placeholder extends JPanel{
        public Placeholder(){
            setBackground(Color.BLUE);
        }
    }
    
}
