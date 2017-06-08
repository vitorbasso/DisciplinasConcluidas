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
public class PessoaFisica extends Pessoa{
    private int ra;
    private final String cpf;
    
    public PessoaFisica (String nome, String cpf){
        super (nome);
        this.cpf=cpf;
    }
    
    @Override
    public double calculaImposto(){
        return 0.15 * ra;
    }
    
    public int getRa (){
        return this.ra;
    }
    
    public void setRa (int ra){
        this.ra=ra;
    }
}
