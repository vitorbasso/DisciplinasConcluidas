/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap14;
import java.util.Scanner;


/**
 *
 * @author vitor
 */
public class Cap14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       Scanner input = new Scanner(System.in);
       FileDemonstration application = new FileDemonstration();
       
       System.out.print("Enter file or directory name here: ");
       application.analyzePath(input.nextLine());
        
    }
    
}
