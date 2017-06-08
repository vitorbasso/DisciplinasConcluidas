/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionlab;
import exceptionlab.Exception.PilhaException;

/**
 *
 * @author vitor
 */
public class ExceptionLab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stack pilha = new Stack(10);
        int nElementos = 10;
        int poping;
        try{
            for(int i = 0; i<nElementos; i++){
                pilha.push(i+1);
            }
            System.out.println(pilha);
            for(int i = 0; i<nElementos;i++){
                poping = pilha.pop();
                System.out.println("Poping element " + poping + " from the stack.\n");
            }
            System.out.println(pilha);
        }catch (PilhaException exception){
            System.out.println(exception.getMessage());
        }
      
    }
    
}
