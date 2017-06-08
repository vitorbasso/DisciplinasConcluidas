/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionlab;

/**
 *
 * @author vitor
 */
public class Node {
    private int elem;
    public Node (int elemento){
        elem = elemento;
    }
    
    int getElem(){
        return elem;
    }
    
    @Override
    public String toString(){
        return String.format("%d", elem);
    }
}
