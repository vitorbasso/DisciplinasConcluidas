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
public class Palestrante extends Apresentador {

    public Palestrante(String n, Evento e, String t) {
        super(n, e, t);
    }

    public String certificado() {
        return "Declaramos que " + getNome() + " apresentou a palestra "
                + getTitulo() + " no " + getEvento().getNome();
    }
    
    public String lista(){
        return "";
    }
}
