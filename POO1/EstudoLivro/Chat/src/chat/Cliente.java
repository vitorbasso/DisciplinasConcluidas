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
public class Cliente {
    private String nome;
    private Servidor s;
    
    public Cliente (String nomeC, Servidor S){
        setNome(nomeC);
        setServidor(S);
        s.addCliente(this);
    }
    
    public void setNome(String nomeC){
        nome = nomeC;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setServidor(Servidor S){
        s = S;
    }
    
    public void receiveMessage(String message, Cliente from){
        javax.swing.JOptionPane.showMessageDialog(null,"Chat de " + getNome() + "\n" + from.getNome() + ": " + message);
    }
    
    public void sendMessage(Cliente to, String message){
        s.sendMessage(this, to, message);
    }
    
    @Override
    public String toString(){
        return String.format("Nome: %s\nConectado no servidor: %s", getNome(), s.getNome());
    }
}
