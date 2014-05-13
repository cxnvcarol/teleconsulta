/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioSeguridadRemote.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.servicios;

import com.teleconsulta.entities.Paciente;

/**
 * Contrato funcional de los servicios de autenticación en el sistema
 * @author Juan Sebastián Urrego
 */
public interface IServicioSeguridadMockRemote
{

    /**
     * Registra el ingreso de un usuario al sistema.
     * @param nombre Login del usuario que quiere ingresar al sistema.
     * @param contraseña Contraseña del usuario que quiere ingresar al sistema.
     * @return usuario Retorna el objeto que contiene la información del usuario que ingreso al sistema.
     */
    public Paciente ingresar(String nombre, String contraseña);

    
}
