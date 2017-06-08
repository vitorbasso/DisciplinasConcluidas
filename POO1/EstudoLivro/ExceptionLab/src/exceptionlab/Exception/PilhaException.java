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
public class PilhaException extends Exception{
    public PilhaException(PilhaExceptionInfo exception){
        super(exception.cause);
    }
}
