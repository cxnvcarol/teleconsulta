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
public class MedidaPeso {
    
    private int valor;
    private Date fecha;
    

    public MedidaPeso(int peso) {
        valor=peso;
        fecha=new Date();
    }
   
    public int getPeso()
    {
        return valor;
    }
    public Date getFecha()
    {
        return fecha;
    }
    
}
