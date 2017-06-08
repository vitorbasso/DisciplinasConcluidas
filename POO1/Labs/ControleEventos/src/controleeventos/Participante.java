/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleeventos;

/**
 *
 * @author marcmaia
 */
public class Participante {

    private String nome;
    private Evento evento;

    public Participante(String n, Evento e) {
        nome = n;
        evento = e;
    }

    public String certificado() {
        return "Declaramos que " + nome + " participou do "
                + evento.getNome();
    }

    protected String getNome() {
        return nome;
    }

    protected Evento getEvento() {
        return evento;
    }
    
    protected String lista(){
        return nome + " assinou a lista de presenca.";
    }
}
