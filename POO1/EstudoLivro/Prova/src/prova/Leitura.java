/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

/**
 *
 * @author vitor
 */
public class Leitura {
    private final String nome;
    private final String autores;
    
    public Leitura(String N, String A){
        nome = N;
        autores = A;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getAutores(){
        return autores;
    }
    
    @Override
    public String toString(){
        return String.format("Nome: %s\nAutores: %s\n", getNome(), getAutores());
    }
    
}
