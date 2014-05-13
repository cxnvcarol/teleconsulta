/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teleconsulta.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Estudiante
 */

@Entity
public class MedidaPeso implements Serializable {
    
    private int valor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Id
    @GeneratedValue
    private Long id;

    public MedidaPeso() {
        fecha=new Date();
    }

    public MedidaPeso(int peso) {
        valor=peso;
        fecha=new Date();
    }
    public void setPeso(int pesoKilos)
    {
        valor=pesoKilos;
    }
   
    public int getPeso()
    {
        return valor;
    }
    public Date getFecha()
    {
        return fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
