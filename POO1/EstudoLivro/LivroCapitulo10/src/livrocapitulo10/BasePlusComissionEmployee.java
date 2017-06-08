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
public class BasePlusComissionEmployee extends ComissionEmployee{
    private double baseSalary;
    
    public BasePlusComissionEmployee (String first,String last, String ssn, double sales, double rate, double salary){
        super(first,last,ssn,sales,rate);
        setBaseSalary(salary);
    }
    
    public final void setBaseSalary(double salary){
        baseSalary = (salary>0.0 ? salary : 0.0);
    }
    
    public double getBaseSalary(){
        return baseSalary;
    }
    
    @Override
    public double getPaymentAmount(){
        return (super.getPaymentAmount() + getBaseSalary());
    }
    
    @Override
    public String toString(){
        return String.format("Base Salaried %s\n%s: %.2f",
                super.toString(),
                "Base salary: ",getBaseSalary());
    }
    
}
