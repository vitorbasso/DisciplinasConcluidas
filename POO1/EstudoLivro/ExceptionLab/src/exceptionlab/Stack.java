/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionlab;
import exceptionlab.Exception.PilhaException;
import exceptionlab.Exception.PilhaExceptionInfo;
import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class Stack {
    final private ArrayList<Node> pilha = new ArrayList<>();
    private int nElementos = 0; 
    final private int capacity;
    
    public Stack(int cap){
        capacity = cap;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public int getNElementos(){
        return nElementos;
    }
    
    public void push(int data) throws PilhaException{
        if(nElementos<getCapacity()){
            Node aux = new Node(data);
            pilha.add(aux);
            nElementos++;
        }else{
            throw new PilhaException(PilhaExceptionInfo.STACK_FULL);
        }
    }
    
    public int pop() throws PilhaException{
        if(nElementos>0){
            nElementos--;
            Node aux = pilha.remove(nElementos);
            return aux.getElem();
        }else{
            throw new PilhaException(PilhaExceptionInfo.STACK_EMPTY);
        }
    }
    
    @Override
    public String toString(){
        String output = "A pilha tem " + getNElementos() + " elementos. São eles:\n";
        String aux;
        for(int i = 0 ; i<nElementos; i++){
           aux = String.format("%s\n", pilha.get(i));
           output = output + aux;
        }
        return output;
    }
    
}
