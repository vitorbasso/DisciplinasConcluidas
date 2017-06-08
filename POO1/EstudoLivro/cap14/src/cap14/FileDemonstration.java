/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap14;
import java.io.File;

/**
 *
 * @author vitor
 */
public class FileDemonstration {
    
    public void analyzePath(String path){
        File name = new File(path);
        
        if(name.exists()){
            System.out.printf("%s %s\n%s\n%s\n%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n", name.getName(), "exists", (name.isFile() ? "Is a file" : "Is not a file"),
                    (name.isDirectory() ? "Is directory" : "Is not a directory"),
                    (name.isAbsolute() ? "Is absolute path" : "Is not an absolute path"),
                    "Last modified: ", name.lastModified(), "Length: ", name.length(),"Path: ", name.getPath(),"Absolute path: ",
                    name.getAbsolutePath(), "Parent: ", name.getParent());
            
            if(name.isDirectory()){
                String directory[] = name.list();
                System.out.println("\n\nDirectory contents: \n");
                
                for(String directoryName : directory){
                    System.out.printf("%s\n", directoryName);
                }
            }
        }else{
            System.out.printf("%s %s", path, "does not exist.");
        }
    }
    
}
