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
public class Historico {
    private ArrayList<MatriculaAluno> disciplinasMatriculadas;
    private Aluno aluno;
     
    public Historico (Aluno a) {
         aluno = a;
         disciplinasMatriculadas = new ArrayList<MatriculaAluno>();
    }
    
    public void matriculaDisciplina (OfertaTurma oferta) {
        MatriculaAluno m = new MatriculaAluno (aluno, oferta);
         this.disciplinasMatriculadas.add(m);
         oferta.matriculaAluno(m);
    }
     
    public boolean constaAprovacao (Disciplina d) {
         for (MatriculaAluno m: disciplinasMatriculadas) {
             if (m.aprovado(d))
                 return true;
         }
         return false;
    }
    
    public void registraResultado (Disciplina d, int nota) {
        for (MatriculaAluno m: disciplinasMatriculadas) {
            if (m.getOferta().nomeDisciplina().equals(d.getNome()) && m.getStatus() == StatusMatriculaDisciplina.CURSANDO)
                m.registraNota(nota);
        }
    }
    
}
    
     
     

