/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;
import java.util.*;
/**
 *
 * @author vitor
 */
public class Servidor {
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private final String nome;
    
    public Servidor(String nomeS){
        nome = nomeS;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void addCliente(Cliente cliente){
        clientes.add(cliente);
        System.out.println("Cliente " + cliente.getNome() + " conectou-se ao servidor.");
    }
    
    public void broadcastMessage(Cliente from, String message){
        for (Cliente cliente : clientes){
            cliente.receiveMessage(message, from);
        }
    }
    
    public void sendMessage(Cliente from, Cliente to, String message){
        Boolean successFrom = false;
        Boolean successTo = false;
        for(Cliente cliente : clientes){
            if(cliente == from){
                successFrom = true;
                for(Cliente cliente1 : clientes){
                    if(cliente1 == to){
                        cliente1.receiveMessage(message, from);
                        successTo = true;
                        break;
                    } 
                }
                if(!successFrom)
                    System.out.println("O cliente " + from.getNome() + " não está conectado no servidor " + getNome());
            }
        }
        if(!successTo)
            System.out.println("O cliente " + to.getNome() + " não está conectado no servidor " + getNome());
    }
    
    @Override
    public String toString(){
        String primeiro = String.format("Servidor: %s\nClientes:\n" , 
                getNome());
        String segundo = "";
        for(Cliente cliente : clientes){
            segundo = String.format(segundo + " " + cliente.getNome());
        }
        return primeiro + segundo;
    }
}
