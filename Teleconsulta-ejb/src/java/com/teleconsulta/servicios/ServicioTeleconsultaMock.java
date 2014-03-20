/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioCatalogoMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.servicios;

import com.teleconsulta.entities.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementacion de los servicios del catálogo de muebles que se le prestan al sistema.
 * @author Juan Sebastián Urrego
 */
public class ServicioTeleconsultaMock implements IServicioTeleconsultaMockLocal
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioTeleconsultaMock()
    {
        persistencia=new ServicioPersistenciaMock();
        //Inicializa el arreglo de los muebles
  
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------


    /**
     * Devuelve los muebles del sistema
     * @return muebles Arreglo con todos los muebles del sistema
     */
    @Override
    public List<Paciente> darPacientes()
    {
        return persistencia.findAll(Paciente.class);
    }
    
    @Override
    public Paciente darPaciente(String id)
    {
        return (Paciente) persistencia.findById(Paciente.class, id);
        
    }
    
    @Override
    public String pushPeso(String id, int peso)
    {
        Paciente p=darPaciente(id);
        p.addPeso(peso);
        boolean alarma=p.alarmaPeso(peso);
        return alarma?p.ultimaAlarma():"peso: sin alarmas";
    }

    @Override
    public String pushPresion(String id, double sistolica, double diastolica)
    {
         Paciente p=darPaciente(id);
        p.addPresion(sistolica, diastolica);
        boolean alarma=p.alarmaPresion(sistolica, diastolica);
        return alarma?p.ultimaAlarma():"presion: sin alarmas";
    }

}
