package com.chat.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/5/16.
 */
@XmlRootElement
public class Quantite {
    private Long nombre;

    public Quantite(){
        /* On a un constructeur par défault por generer le XML*/
    }

    /**
     * @param nombre
     */
    public Quantite(Long nombre) {
        this.nombre = nombre;
    }

    public Long getNombre() {
        return nombre;
    }

    public void setNombre(Long nombre) {
        this.nombre = nombre;
    }
}
