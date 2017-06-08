/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionlab.Exception;

/**
 *
 * @author vitor
 */
public enum PilhaExceptionInfo {
    STACK_EMPTY("Tentativa de remoção em pilha vazia!\n"), STACK_FULL("Tentativa de adição em pilha cheia!\n");
    
    final String cause;
    
    private PilhaExceptionInfo(String c){
        cause = c;
    }
    
    @Override
    public String toString(){
        return cause;
    }
}
