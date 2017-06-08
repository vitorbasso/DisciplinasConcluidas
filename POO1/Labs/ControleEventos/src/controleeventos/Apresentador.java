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
public class Apresentador extends Participante {

    private String titulo;

    public Apresentador(String n, Evento e, String t) {
        super(n, e);
        titulo = "\"" + t + "\"";
    }

    protected String getTitulo() {
        return titulo;
    }
}
