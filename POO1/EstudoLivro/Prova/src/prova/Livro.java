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
public class Livro extends Leitura{
    private int nPaginas;
    
    public Livro(String nome, String autores, int numeroP){
        super(nome,autores);
        setNumeroPaginas(numeroP);
    }
    
    public void setNumeroPaginas(int numeroP){
        nPaginas = (numeroP>0 ? numeroP : 0);
    }
    
    public int getNumeroPaginas(){
        return nPaginas;
    }
    
    @Override
    public String toString(){
        return String.format("Livro - \n%sNumero de paginas: %d",
                super.toString(),
                getNumeroPaginas());
    }
    
}
