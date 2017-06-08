/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrocapitulo10;

/**
 *
 * @author vitor
 */
public abstract class Employee implements Payable{
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    
    public Employee(String first,String last, String ssn){
        firstName=first;
        lastName=last;
        socialSecurityNumber=ssn;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getSocialSecurityNumber(){
        return socialSecurityNumber;
    }
    
    @Override
    public String toString(){
        return String.format("%s %s\nSocial security number: %s\n", 
                getFirstName(),getLastName(),
                getSocialSecurityNumber());
    }
    
}
