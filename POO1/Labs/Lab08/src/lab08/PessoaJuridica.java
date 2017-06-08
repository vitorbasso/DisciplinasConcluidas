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
public class PessoaJuridica extends Pessoa{
    private int fa , ci;
    private final String cnpj;
    
    public PessoaJuridica (String nome,String cnpj){
        super (nome);
        this.cnpj=cnpj;
    }
    
    @Override
    public double calculaImposto(){
        return (0.2 * (fa-ci));
    }
    
    public int getFa (){
        return this.fa;
    }
    
    public void setFa (int fa){
        this.fa=fa;
    }
    
    public int getCi (){
        return ci;
    }
    
    public void setCi (int ci){
        this.ci = ci;
    }
}
