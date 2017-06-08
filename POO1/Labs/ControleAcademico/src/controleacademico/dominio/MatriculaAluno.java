/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleacademico.dominio;

import java.util.Iterator;

/**
 *
 * @author Marcelo
 */
public class MatriculaAluno {
    private Aluno aluno;
    private OfertaTurma oferta;
    private StatusMatriculaDisciplina  status;
    private float nota;
    
    public MatriculaAluno (Aluno a, OfertaTurma o) {
        boolean confirma = true;
        for (Disciplina d : o.getDisciplina().getRequisitos()) {
            if(!a.aprovado(d))
                confirma = false;
        }
        if(confirma){
            aluno = a;
            oferta = o;
            status = StatusMatriculaDisciplina.CURSANDO;
            System.out.println("O aluno " + a.getNomeAluno() + " foi matriculado"
                    + " com sucesso na disciplina: " + 
                    o.getDisciplina().getNome());
        }
        else if(!confirma){
            aluno = a;
            oferta = o;
            status = StatusMatriculaDisciplina.RECUSADA;
            System.out.println("O aluno " + a.getNomeAluno() + " nao foi matriculado"
                    + " na disciplina: " + o.getDisciplina().getNome() 
                    + " pois nao tinha os prerequisitos.");
        }
    }
    
    public Aluno alunoMatriculado () {
        return aluno;
    }
    
    public OfertaTurma getOferta(){
        return this.oferta;
    }
    
    public StatusMatriculaDisciplina getStatus(){
        return this.status;
    }
    
    public OfertaTurma turmaMatriculada() {
        return oferta;
    }
    
    public void registraNota (float n) {
        this.nota = n;
        if (this.nota >= 60)
            status = StatusMatriculaDisciplina.APROVADA;
        else
            status = StatusMatriculaDisciplina.REPROVADA;            
    }
    
    public void trancaMatricula () {
        this.status = StatusMatriculaDisciplina.TRANCADA;
    }    
    
    public boolean aprovado (Disciplina d) {
        return d.getNome().equals(oferta.nomeDisciplina()) && status == StatusMatriculaDisciplina.APROVADA;
    }
}
