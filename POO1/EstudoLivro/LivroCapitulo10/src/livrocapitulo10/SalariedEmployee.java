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
public class SalariedEmployee extends Employee{
    private double weeklySalary;
    
    public SalariedEmployee(String first, String last, String ssn, double salary){
        super(first,last,ssn);
        setWeeklySalary(salary);
    }
    
    public void setWeeklySalary(double salary){
        weeklySalary = (salary>0.0 ? salary : 0.0);
    }
    
    public double getWeeklySalary(){
        return weeklySalary;
    }
    
    @Override
    public double getPaymentAmount(){
        return getWeeklySalary();
    }
    
    @Override
    public String toString(){
        return String.format("Salaried employee: %s%s: $%,.2f",
                super.toString(), "weekly salary employee: ", getWeeklySalary());
    }
    
}
