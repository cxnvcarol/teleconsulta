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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Estudiante
 */

@Entity
public class MedidaPresion implements Serializable {
    private double sistolica;
    private double diastolica;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Id
    @GeneratedValue
    private Long id;

    public MedidaPresion() {
    }
    
    public MedidaPresion(double sist, double diast) {
        sistolica=sist;
        diastolica=diast;
        fecha=new Date();
    }
    
    public double getPresion()
    {
        return sistolica-diastolica;
    }
    public Date getFecha()
    {
        return fecha;
    }
    
    @Override
    public String toString()
    {
        return getPresion()+"";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}

