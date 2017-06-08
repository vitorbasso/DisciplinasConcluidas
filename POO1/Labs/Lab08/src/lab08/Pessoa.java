/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab08;

/**
 *
 * @author vitor
 */
public abstract class Pessoa {
    private final String nome;
    
    public Pessoa (String name){
        this.nome = name;
    }
    
    public abstract double calculaImposto ();
    
    public String getNome (){
        return this.nome;
    }
}
