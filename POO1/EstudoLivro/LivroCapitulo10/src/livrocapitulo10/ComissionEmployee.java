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
public class ComissionEmployee extends Employee {
    private double grossSales;
    private double comissionRate;
    
    public ComissionEmployee(String first,String last,String ssn,double sales,double rate){
        super(first,last,ssn);
        setGrossSales(sales);
        setComissionRate(rate);
    }
    
    public final void setGrossSales(double sales){
        grossSales = (sales>0.0 ? sales : 0.0);
    }
    
    public double getGrossSales(){
        return grossSales;
    }
    
    public final void setComissionRate(double rate){
        comissionRate = ((rate>0.0 && rate<1.0) ? rate : 0.0);
    }
    
    public double getComissionRate(){
        return comissionRate;
    }
    
    @Override
    public double getPaymentAmount(){
        return (getGrossSales() * getComissionRate());
    }
    
    @Override
    public String toString(){
        return String.format("%s %s%s: %.2f\n%s: %.2f",
                "Comissioned employee:", super.toString(),
                "Gross sales: ",getGrossSales(),
                "Earnings: ",getPaymentAmount());
    }
    
}
