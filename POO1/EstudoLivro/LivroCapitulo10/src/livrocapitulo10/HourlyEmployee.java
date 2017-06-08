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
public class HourlyEmployee extends Employee{
    private double wage;
    private double hours;
    
    public HourlyEmployee (String first, String last, String ssn, double hourlyWage,double hoursWorked){
        super(first,last,ssn);
        setWage(hourlyWage);
        setHours(hoursWorked);
    }
    
    public void setWage(double hourlyWage){
        wage = (hourlyWage>0.0 ? hourlyWage : 0.0);
    }
    
    public double getWage(){
        return wage;
    }
    
    public void setHours(double hoursWorked){
        hours = ((hoursWorked>=0.0 && hoursWorked<=168.0) ? hoursWorked : 0);
    }
    
    public double getHours(){
        return hours;
    }
    
    @Override
    public double getPaymentAmount(){
        if(getHours()<=40)
            return getWage() * getHours();
        else
            return (40*getWage()) + ((getHours() - 40) * getWage())*1.5;
    }
    
    @Override
    public String toString(){
        return String.format("Hourly employee: %s%s:$%,.2f\n%s: %,.2f\n%s %,.2f",
                super.toString(),"Hourly wage employee: ",getWage(), 
                "Hours worked: ", getHours(),
                "Earnings: ",getPaymentAmount());
    }
    
}
