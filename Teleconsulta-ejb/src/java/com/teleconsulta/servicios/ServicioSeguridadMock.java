/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioSeguridadMock.java
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
 * Clase que se encarga de la autenticación de un usuario en el sistema
 * @author Juan Sebastián Urrego
 */
public class ServicioSeguridadMock implements IServicioSeguridadMockLocal
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioSeguridadMock()
    {
        persistencia=new ServicioPersistenciaMock();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Registra el ingreso de un usuario al sistema.
     * @param nombre Login del usuario que quiere ingresar al sistema.
     * @param contraseña Contraseña del usuario que quiere ingresar al sistema.
     * @return usuario Retorna el objeto que contiene la información del usuario que ingreso al sistema.
     */
    @Override
    public Paciente ingresar(String id, String contraseña)
    {
   
        Paciente u = (Paciente) persistencia.findById(Paciente.class, id);

        if (u != null)
        {
            if (u.getId().equals(id) && u.getContrasena().equals(contraseña))
            {
                return u;
            } 
            else
            {
                return null;
            }
        } 
        else
        {
           return null;
        }
    }
}
