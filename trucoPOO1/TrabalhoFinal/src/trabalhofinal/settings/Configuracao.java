/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.settings;

/**
 *
 * @author vitor
 */
public class Configuracao {
    
    private String nomeJogador;
    private int quantosJogadores;
    private boolean jogoNovo = true;
    private String saveGame;
    private boolean first = true;
    private boolean cheating = true;
    private boolean AI = false;
    
    public Configuracao(){
        setBase("Default", 2);
    }
    
    public void setBase(String nome, int qnt){
        setNomeJogador(nome);
        setQuantosJogadores(qnt);
    }
    
    public void setLoadArchive(String save){
        setJogoNovo(false);
        saveGame = save;
    }
    
    public void setNomeJogador(String nome){
        nomeJogador = nome;
    }
    
    public String getNomeJogador(){
        return nomeJogador;
    }
    
    public void setQuantosJogadores(int numero){
        quantosJogadores = numero;
    }
    
    public int getQuantosJogadores(){
        return quantosJogadores;
    }
    
    public void setJogoNovo(boolean teste){
        jogoNovo = teste;
    }
    
    public String getArchiveName(){
        return saveGame;
    }
    
    public boolean isNewGame(){
        return jogoNovo;
    }
    
    public boolean isFirst(){
        return first;
    }
    
    public void setFirst(boolean state){
        first = state;
    }
    
    public void setCheat(boolean state){
        cheating = state;
    }
    
    public boolean getCheat(){
        return cheating;
    }
    
    public boolean getAI(){
        return AI;
    }
    
    public void setAI(boolean state){
        AI = state;
    }
    
}
