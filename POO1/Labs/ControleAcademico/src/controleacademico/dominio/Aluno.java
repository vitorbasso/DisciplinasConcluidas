/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleacademico.dominio;

/**
 *
 * @author Marcelo
 */
public class Aluno {
    private String nome;
    private GradeCurricular grade;
    private Historico historico;
    
    public Aluno (String n, GradeCurricular g) {
        nome = n;
        grade = g;
        historico = new Historico(this);
    }
    
    public String getNomeAluno(){
        return this.nome;
    }
    
    public void forma(){  
        this.grade.faltaForma(this);
    }
    
    public boolean aprovado(Disciplina disciplina){
        return this.historico.constaAprovacao(disciplina);
    }
    
    public void nota(Disciplina disciplina,int nota){
        this.historico.registraResultado(disciplina, nota);
    }
    
    public void matricula(OfertaTurma oferta){
        this.historico.matriculaDisciplina(oferta);
    }
    
    public boolean verificaSeFormou () {
        // for (int i = 0; i < grade.nroDisciplinas(); i++) {
        for (Disciplina d:   grade.getDisciplinas()) {
            if (! historico.constaAprovacao(d))
                return false;
        }
        return true;
    }
}