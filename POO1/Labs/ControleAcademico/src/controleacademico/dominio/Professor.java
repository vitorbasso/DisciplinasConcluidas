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
public class Professor {
    private String nome;
    private String departamento;
    private ArrayList<OfertaTurma> turmasOfertadas;
    
    public Professor (String nome, String d) {
        this.nome = nome;
        departamento = d;
    }    
    
    public void ficaAlocadoTurma (OfertaTurma o) {
        turmasOfertadas.add(o);
        o.vinculaProfessor(this);
    }
}
