/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleacademico.dominio;
import java.util.Vector;
/**
 *
 * @author Marcelo
 */
public class GradeCurricular {
    private Vector<Disciplina> disciplinas = new Vector<Disciplina>();
    
    public void incluiDisciplina (Disciplina d) {
        disciplinas.add(d);
    }
    
    public void removeDisciplina (Disciplina d) {
        disciplinas.remove(d);
    }

    public int nroDisciplinas() {
        return disciplinas.size();
    }
    
    public Vector<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }
    
    public void faltaForma(Aluno aluno){
        for(Disciplina d : disciplinas){
            if(!aluno.aprovado(d)){
                System.out.println("Falta: " + d.getNome());
            }
        }
    }
}
