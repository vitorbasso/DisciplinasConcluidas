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
public class Lab08 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pessoa grupo[] = new Pessoa [2];
        PessoaFisica pessoaF = new PessoaFisica ("Juscileine" , "045.234.505-81");
        pessoaF.setRa(2000);
        PessoaJuridica pessoaJ = new PessoaJuridica ("Americanas inc" , "123.534.345-54");
        pessoaJ.setFa(500000);
        pessoaJ.setCi(80000);
        grupo[0] = pessoaF;
        grupo[1] = pessoaJ;
        
        for (int i = 0 ; i<2 ; i++)
            System.out.println (grupo[i].getNome() + " deve a receita federal o valor de " + grupo[i].calculaImposto() + " reais.\n");
    }
    
}
