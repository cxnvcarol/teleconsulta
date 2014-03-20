/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teleconsulta.entities;

import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class MedidaPresion {
    private double sistolica;
    private double diastolica;
    private Date fecha;
    
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
    
}

