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
public class Invoice implements Payable{
    private final String partNumber;
    private final String partDescription;
    private int quantity;
    private double pricePerItem;
    
    //Construtor com 4 argumentos
    public Invoice(String part, String description, int count, double price){
        partNumber = part;
        partDescription = description;
        setQuantity(count);     //Valida e armazena a quantidade
        setPricePerItem(price);    //Valida e armazena o preco por item
    }//Fim do construtor Invoice de quatro argumentos
    
    //obtemo numero da peca
    public String getPartNumber(){
        return partNumber;
    }
    
    public String getPartDescription(){
        return partDescription;
    }
    
    public void setQuantity(int count){
        quantity = (count>0 ? count : 0);
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setPricePerItem(double price){
        pricePerItem = (price>0.0 ? price : 0.0);
    }
    
    public double getPricePerItem(){
        return pricePerItem;
    }
    
    @Override
    public String toString(){
        return String.format("%s: \n%s: %s (%s) \n%s: %d \n%s: $%,.2f",
                "Invoice", "Part number", getPartNumber(), getPartDescription(),
                "Quantity", getQuantity(), "Price per item", getPricePerItem());
    }
    
    @Override
    public double getPaymentAmount(){
        return (getQuantity() * getPricePerItem());
    }
    
}
