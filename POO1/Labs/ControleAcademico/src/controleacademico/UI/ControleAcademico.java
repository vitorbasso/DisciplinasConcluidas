/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleacademico.UI;
import controleacademico.dominio.Aluno;
import controleacademico.dominio.Disciplina;
import controleacademico.dominio.GradeCurricular;
import controleacademico.dominio.OfertaTurma;
import controleacademico.dominio.Professor;
import controleacademico.dominio.Semestre;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcelo
 */
public class ControleAcademico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Disciplina pp = new Disciplina("PP");
        Disciplina aed1 = new Disciplina("AED1");
        Disciplina poo1 = new Disciplina("POO1");
        Disciplina aed2 = new Disciplina("AED2");
        aed2.criaPrerequisito(aed1);
        GradeCurricular grade = new GradeCurricular();
        grade.incluiDisciplina(pp);
        //pp.incluiNaGrade(grade); Seria um bad smell: feature envy
        grade.incluiDisciplina(aed1);
        grade.incluiDisciplina(poo1);
        grade.incluiDisciplina(aed2);
        String nomeProfessor = JOptionPane.showInputDialog("Entre com o nome do professor: ");
        Professor profMarcelo = new Professor(nomeProfessor, "FACOM");
        String nomeAluno = JOptionPane.showInputDialog("Entre com o nome do aluno: ");
        Aluno alunoSeverino = new Aluno(nomeAluno, grade);
        
        JOptionPane.showMessageDialog(null, "O aluno " + alunoSeverino.getNomeAluno() + " esta' " + (alunoSeverino.verificaSeFormou() ? "formado!" : "ainda cursando"));
        
        OfertaTurma ofertaPP = new OfertaTurma(2015, Semestre.PRIMEIRO, pp);
        alunoSeverino.matricula(ofertaPP);
        JOptionPane.showMessageDialog(null, "O aluno " + alunoSeverino.getNomeAluno() + " esta' " + (alunoSeverino.verificaSeFormou() ? "formado!" : "ainda cursando"));

        OfertaTurma ofertaAED1 = new OfertaTurma(2016, Semestre.SEGUNDO, aed1);
        alunoSeverino.matricula(ofertaAED1);

        OfertaTurma ofertaPOO1 = new OfertaTurma(2016, Semestre.SEGUNDO, poo1);
        alunoSeverino.matricula(ofertaPOO1);
        
        JOptionPane.showMessageDialog(null, "O aluno " + alunoSeverino.getNomeAluno() + " esta' " + (alunoSeverino.verificaSeFormou() ? "formado!" : "ainda cursando"));
        
        OfertaTurma ofertaAED2 = new OfertaTurma(2016, Semestre.SEGUNDO, aed2);
        aed2.criaPrerequisito(aed1);
        alunoSeverino.matricula(ofertaAED2);
        alunoSeverino.nota(aed2, 80);
        alunoSeverino.forma();
        
        alunoSeverino.nota(pp, 60);
        alunoSeverino.forma();
        JOptionPane.showMessageDialog(null, "O aluno " + alunoSeverino.getNomeAluno() + " esta' " + (alunoSeverino.verificaSeFormou() ? "formado!" : "ainda cursando"));
        alunoSeverino.nota(aed1, 60);
        alunoSeverino.forma();
        alunoSeverino.matricula(ofertaAED2);
        JOptionPane.showMessageDialog(null, "O aluno " + alunoSeverino.getNomeAluno() + " esta' " + (alunoSeverino.verificaSeFormou() ? "formado!" : "ainda cursando"));
        alunoSeverino.nota(poo1, 60);
        alunoSeverino.forma();
        alunoSeverino.nota(aed2, 80);
        alunoSeverino.forma();
        JOptionPane.showMessageDialog(null, "O aluno " + alunoSeverino.getNomeAluno() + " esta' " + (alunoSeverino.verificaSeFormou() ? "formado!" : "ainda cursando"));
    }
    
}
