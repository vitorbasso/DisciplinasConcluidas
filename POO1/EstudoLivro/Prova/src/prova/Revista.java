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
public class Revista extends Leitura{
    private int volume;
    private int numero;
    
    public Revista(String nome, String autores, int vol, int n){
        super(nome,autores);
        setVolume(vol);
        setNumero(n);
    }
    
    public void setVolume(int vol){
        volume = (vol>0 ? vol : 0);
    }
    
    public int getVolume(){
        return volume;
    }
    
    public void setNumero(int n){
        numero = (n>0 ? n : 0);
    }
    
    public int getNumero(){
        return numero;
    }
    
    @Override
    public String toString(){
        return String.format("Revista - \n%sVolume: %d\nNumero: %d\n",
                super.toString(),
                getVolume(),
                getNumero());
    }
    
}
