/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleacademico.dominio;
import java.util.*;
/**
 *
 * @author Marcelo
 */
public class Disciplina {
    private String nome;
    private ArrayList<Disciplina> prerequisitos;
    
    public Disciplina (String nome) {
        this.nome = nome;
        prerequisitos = new ArrayList<Disciplina>();
    }
    
    public void criaPrerequisito (Disciplina d) {
        prerequisitos.add(d);
    }
    
    public ArrayList<Disciplina> getRequisitos(){
        return this.prerequisitos;
    }

    public String getNome() {
        return nome;
    }
}
