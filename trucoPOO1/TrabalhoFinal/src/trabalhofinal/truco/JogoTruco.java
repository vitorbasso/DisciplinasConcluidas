/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco;
import java.awt.BorderLayout;
import trabalhofinal.truco.menu.Menu;
import trabalhofinal.truco.corpo.Baralho;
import javax.swing.JFrame;
import javax.swing.JPanel;
import trabalhofinal.truco.corpo.Status;
import java.awt.GridLayout;
import java.awt.Color;
import trabalhofinal.settings.Configuracao;
import trabalhofinal.truco.corpo.Jogador;
import trabalhofinal.truco.corpo.Dealer;
import java.util.ArrayList;
import trabalhofinal.truco.corpo.Table;
import trabalhofinal.truco.corpo.Tracker;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import trabalhofinal.truco.corpo.Cards;
/**
 *
 * @author vitor
 */
public class JogoTruco extends JFrame{
    
    private Menu menu;
    private Baralho baralho;
    private JPanel teste;
    private Status status;
    private final Configuracao settings;
    private ArrayList<Jogador> players;
    private Dealer dealer;
    private Table mesa;
    private final Tracker contador;
    private final Placar placar;
    
    public JogoTruco(Configuracao config){
        
        super("Truco em JAVA");
        
        settings = config;
        teste = new JPanel(new GridLayout(3,3));
        players = new ArrayList<>();
        dealer = new Dealer();
        contador = new Tracker(settings, this);
        mesa = new Table(settings, this, players, contador);
        placar = new Placar();
        
        newGame(settings);
        
        add(menu, BorderLayout.NORTH);
        add(teste, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
        
        teste.setBackground(Color.BLUE);
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,1000);
        setVisible(true);
    }
    
    public void newGame(Configuracao settings){
        
        teste.removeAll();
        players.clear();
        dealer.resetDealer();
        mesa.resetTable();
        
        System.out.println("Novo jogo iniciado" );
        players.add(new Jogador(settings, this, mesa, true, true, 0, contador));
        status = new Status(settings, players.get(0));
        menu = new Menu(settings, status, this);
        contador.resetGame();
        playerUpdate();
        
        mesa.newTable();
        
        setGame(settings);
        
        nextRound();
        contador.newTracker();
        mesa.setVez(0);
        
        for(Jogador player : players){
            if(player.getId() == mesa.getVez())
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.YELLOW);
            else
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.GREEN);
        }
        
        if(settings.isFirst())
            settings.setFirst(false);
    } 
    
    public void playerUpdate(){
        for(Jogador player : players)
            player.updateInfo();
        placar.updatePlacar();
    }
    
    public void nextRound(){
        for(Jogador player : players){
            player.freeHand();
        }
        dealer.shuffle();
        dealer.giveHand(players);  
        mesa.setVez(0);
        for(Jogador player : players)
            if(player.getId() == mesa.getVez())
                for(Cards carta : player.getHand())
                    carta.setBackground(Color.YELLOW);
    }
    
    public void cleanTable(){
        mesa.resetTable();
        nextRound();
    }
    
    public void checkWin(){
        mesa.checkWin();
    }
    
    public void saveGame(){
        System.out.println("SAVED GAME");
    }
    
    public void loadGame(){
        
        System.out.println("LOADED GAME");
        settings.setLoadArchive("JogoTruco20161202");
        newGame(settings);
        status.updateStatus();
    }
    
    public void setGame(Configuracao settings){
        boolean aux;
        int jo;
        for(int count = 1; count < settings.getQuantosJogadores(); count++){
            jo = count;
            if(count == 3){
                aux = true;
                jo = 2;
            }else
                aux = false;
            if(count == 2)
                jo = 3;
            players.add(new Jogador(settings, this, mesa, false, aux, jo, contador));
        }
        
        setBoard(settings);
    }
    
    public void setBoard(Configuracao settings){
        teste.add(placar);
        if(settings.getQuantosJogadores() == 4){
            int i = 0;
            for(Jogador player : players){
                teste.add(player);
                if(i == 1)
                    teste.add(mesa);
                else
                    teste.add(new Placeholder(i));
                i++;
            }
        }else{
            teste.add(players.get(0));
            int i = 0;
            for(int count = 0; count <5 ; count++){
                if(i == 2)
                    teste.add(mesa);
                else
                    teste.add(new Placeholder(i));
                i++;
            }
            teste.add(players.get(1));
            teste.add(new Placeholder(6));
        }
        validate();
        repaint();
    } 
    
    public ArrayList<Jogador> getPlayers(){
        return players;
    }
    
    public void setVez(int proximo){
        mesa.setVez(proximo);
        for(Jogador player : players){
            if(proximo == player.getId())
                for(Cards carta : player.getHand()){
                    carta.setBackground(Color.YELLOW);
                }
        }
    }
    
    private class Placar extends JPanel{
        private JLabel nomePlayer;
        private JLabel jogoNovo;
        private JLabel score;
        private JLabel numeroPlayers;
        private JPanel placar;
        private final JLabel titulo;
        private final JLabel truco;
        private final JLabel aluno;
        private final JLabel matricula;
        private final JLabel materia;
        
        public Placar(){
            super(new BorderLayout());
            
            nomePlayer = new JLabel();
            nomePlayer.setForeground(Color.WHITE);
            score = new JLabel(); 
            score.setForeground(Color.white);
            numeroPlayers = new JLabel();
            numeroPlayers.setForeground(Color.WHITE);
            jogoNovo = new JLabel();
            jogoNovo.setForeground(Color.WHITE);
            titulo = new JLabel("############      PLACAR       ############\n\n");
            titulo.setForeground(Color.WHITE);
            titulo.setHorizontalAlignment(JLabel.CENTER);
            placar = new JPanel(new GridLayout(13,1));
            
            updatePlacar();
            truco = new JLabel("Jogo de Truco em Java");
            truco.setForeground(Color.WHITE);
            truco.setHorizontalAlignment(JLabel.CENTER);
            
            aluno = new JLabel("Aluno: Vitor Martins Basso");
            aluno.setForeground(Color.WHITE);
            aluno.setHorizontalAlignment(JLabel.CENTER);
            
            matricula = new JLabel("Matricula: 11611BCC034");
            matricula.setForeground(Color.WHITE);
            matricula.setHorizontalAlignment(JLabel.CENTER);
            
            materia = new JLabel("Programação Orientada a Objetos 1 - Marcelo Maia");
            materia.setForeground(Color.WHITE);
            materia.setHorizontalAlignment(JLabel.CENTER);
            
            
            placar.add(titulo);
            placar.add(new JLabel(""));
            
            placar.add(nomePlayer);
            placar.add(score);
            placar.add(numeroPlayers);
            placar.add(jogoNovo);
            placar.add(new JLabel(""));
            placar.add(truco);
            placar.add(aluno);
            placar.add(matricula);
            placar.add(materia);
            
            add(new AnotherVer(), BorderLayout.NORTH);
            add(placar, BorderLayout.CENTER);
            add(new AnotherVer(), BorderLayout.SOUTH);
            add(new AnotherSide(), BorderLayout.WEST);
            add(new AnotherSide(), BorderLayout.EAST);
            
            placar.setBackground(Color.GRAY);
            
            setBackground(Color.BLUE);
        }
        
        public void updatePlacar(){
            nomePlayer.setText(String.format("Jogador: %s", settings.getNomeJogador()));
            nomePlayer.setHorizontalAlignment(JLabel.CENTER);
            score.setText(String.format("Time %s: %d Pontos -- Time BOT: %d Pontos.", settings.getNomeJogador(), contador.getTeamPlayerScore(), contador.getTeamBotScore()));
            score.setHorizontalAlignment(JLabel.CENTER);
            numeroPlayers.setText(String.format("Partida com %d jogadores.", settings.getQuantosJogadores()));
            numeroPlayers.setHorizontalAlignment(JLabel.CENTER);
            String aux;
            
            if(settings.isNewGame())
                jogoNovo.setText("");
            else
                jogoNovo.setText(String.format("Jogo carregado de %s", settings.getArchiveName()));
            
            validate();
            repaint();
        }
        
        private class AnotherSide extends JPanel{
            private final JPanel esquerda;
            private final JPanel direita;
            public AnotherSide(){
                super(new BorderLayout());
                
                esquerda = new JPanel();
                esquerda.setBackground(Color.LIGHT_GRAY);
                direita = new JPanel();
                direita.setBackground(Color.LIGHT_GRAY);
                add(esquerda, BorderLayout.WEST);
                add(direita, BorderLayout.EAST);
                setBackground(Color.LIGHT_GRAY);
            }
        }
        
        private class AnotherVer extends JPanel{
            private final JPanel cima;
            private final JPanel embaixo;
            public AnotherVer(){
                super(new BorderLayout());
                
                cima = new JPanel();
                cima.setBackground(Color.LIGHT_GRAY);
                embaixo = new JPanel();
                embaixo.setBackground(Color.LIGHT_GRAY);
                add(cima, BorderLayout.NORTH);
                add(embaixo, BorderLayout.SOUTH);
                setBackground(Color.LIGHT_GRAY);
            }
        }
        
    }
    
    private class Placeholder extends JPanel{
        private final Icon smartphone;
        private final Icon tento;
        private final Icon deckBox;
        private final JPanel local;
        private final JLabel imagem;
        
        public Placeholder(int qual){
            setBackground(Color.BLUE);
            
            smartphone = new ImageIcon(getClass().getResource("smartphone.jpg"));
            tento = new ImageIcon(getClass().getResource("tento.jpg"));
            deckBox = new ImageIcon(getClass().getResource("deckBox.jpg"));
            local = new JPanel(new FlowLayout());
            local.setBackground(Color.BLUE);
            imagem = new JLabel();
            if(settings.getQuantosJogadores() == 4){
                if(qual == 0)
                    imagem.setIcon(deckBox);
                else if(qual == 2)
                    imagem.setIcon(smartphone);
                else if(qual == 3)
                    imagem.setIcon(tento);
            }else{
                if(qual == 0)
                    imagem.setIcon(deckBox);
                else if(qual == 4)
                    imagem.setIcon(smartphone);
                else if(qual == 6)
                    imagem.setIcon(tento);
            }
            
            imagem.setBackground(Color.BLUE);
            
            local.add(imagem);
            add(local);
            
        }
    }
    
}
