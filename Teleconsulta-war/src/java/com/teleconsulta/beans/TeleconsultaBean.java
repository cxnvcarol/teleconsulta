/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ CatalogoBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.beans;

import com.teleconsulta.entities.Paciente;
import com.teleconsulta.servicios.IServicioTeleconsultaMockLocal;
import com.teleconsulta.servicios.ServicioTeleconsultaMock;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Managed bean encargado del catálogo de muebles en el sistema
 * @author Juan Sebastián Urrego
 */
public class TeleconsultaBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    private IServicioTeleconsultaMockLocal servicio;
    private Paciente pacienteDetalle;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase principal
     */
    public TeleconsultaBean()
    {
        servicio=new ServicioTeleconsultaMock();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    public List<Paciente> getPacientes()
    {

        return servicio.darPacientes();
    }
    
    public String consultarPaciente(String id)
    {
        pacienteDetalle= servicio.darPaciente(id);
        return "paciente";
    }
    public Paciente getPacienteDetalle()
    {
        return pacienteDetalle;
    }
    
    
}
