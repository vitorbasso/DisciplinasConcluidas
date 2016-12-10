/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;
import trabalhofinal.settings.Configuracao;
import javax.swing.JLabel;
import trabalhofinal.settings.Configuracao;
import trabalhofinal.truco.corpo.Jogador;
/**
 *
 * @author vitor
 */
public class Status extends JLabel{
    
    private String name;
    private int qntJogadores;
    private Configuracao settings;
    private Jogador player;
    
    public Status(Configuracao config, Jogador jogador){
        settings = config;
        setName(settings.getNomeJogador());
        setQntJogadores(settings.getQuantosJogadores());
        player = jogador;
        updateStatus();
    }
    
    public void setName(String nome){
        name = nome;
    }
    
    public String getName(){
        return name;
    }
    
    public void setQntJogadores(int qnt){
        qntJogadores = qnt;
    }
    
    public int getQntJogadores(){
        return qntJogadores;
    }
    
    public void updateStatus(){
        setName(settings.getNomeJogador());
        setQntJogadores(settings.getQuantosJogadores());
        String aux = "";
        if(settings.isNewGame())
            aux = "Jovo novo.";
        else
            aux = String.format("Jogo carregado de '%s'.", settings.getArchiveName());
        if(settings.getAI())
            aux += " - Jogando com AI";
        else
            aux += " - Jogando sem AI";
        if(settings.getCheat())
            aux += ". - Usando Cheats";
        
        setText(String.format("Jogador: %s. Jogo com %d jogadores. %s.", getName(), getQntJogadores(), aux));
    }
    
}
