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
public class PayableInterfaceTest {
    
    public static void main (String args[]){
        //Cria array Payable de quatro elementos
        Payable payableObjects[] = new Payable[6];
        
        //Preenche o array com objetos que implementam Payable
        payableObjects[0] = new Invoice("01234", "seat", 2, 375.00);
        payableObjects[1] = new Invoice("56789", "tire", 4, 79.95);
        payableObjects[2] = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
        payableObjects[3] = new HourlyEmployee("Silvester", "Stalone", "222-22-2222", 29.5, 40);
        payableObjects[4] = new ComissionEmployee("Jessica", "Parker", "333-33-3333", 10000, .06);
        payableObjects[5] = new BasePlusComissionEmployee("Sue", "Mom", "444-44-4444", 5000, .05, 400);
        
        System.out.println("Invoices and Employees processed polymorphically:\n");
        
        //Processa genericamente cada elemento no array payableObjects
        double total = 0;
        for(Payable currentPayable : payableObjects){
            System.out.printf("%s \n%s: $%,.2f\n\n",
                    currentPayable,
                    "Payment due", currentPayable.getPaymentAmount());
            total += currentPayable.getPaymentAmount();
        }
        System.out.printf("Total due: $%,.2f\n\n", total);
        
    }//Fim de main 
    
}
