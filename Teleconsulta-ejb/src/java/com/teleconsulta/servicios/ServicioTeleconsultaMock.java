/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * ServicioCatalogoMock.java Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación Licenciado bajo el
 * esquema Academic Free License version 3.0
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.teleconsulta.servicios;

import com.teleconsulta.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * sistema.
 *
 * @author Juan Sebastián Urrego
 */
public class ServicioTeleconsultaMock implements IServicioTeleconsultaMockLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    //@EJB
    private IServicioPersistenciaMockLocal persistencia;
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioTeleconsultaMock() {
        persistencia = new ServicioPersistenciaMock();
        
            //try { 
    // persistencia = (IServicioPersistenciaMockLocal) new 
    //InitialContext().lookup(prop.getProperty("jndi_persistencia")); 
         ////       persistencia=(IServicioPersistenciaMockLocal) new  ServicioPersistenciaNoSql();
     /*} catch (NamingException ex) { 

    Logger.getLogger(ServicioTeleconsultaMock.class.getName()).log(Level.SEVERE, 
    null, ex); 
     } 
     * */


    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     *
     */
    @Override
    public List<Paciente> darPacientes() {
        return persistencia.findAll(Paciente.class);
    }

    @Override
    public Paciente darPaciente(String id) {
        return (Paciente) persistencia.findById(Paciente.class, id);

    }

    @Override
    public String pushPeso(String id, int peso) {
        
        Paciente p = darPaciente(id);
        p.addPeso(peso);
        boolean alarma = p.alarmaPeso(peso);
        persistencia.update(p);
        return alarma ? p.ultimaAlarma() : "peso: sin alarmas";
    }

    @Override
    public String pushPresion(String id, double sistolica, double diastolica) {
        Paciente p = darPaciente(id);
        p.addPresion(sistolica, diastolica);
        boolean alarma = p.alarmaPresion(sistolica, diastolica);
        persistencia.update(p);
        return alarma ? p.ultimaAlarma() : "presion: sin alarmas";
    }

    @Override
    public Paciente darPorLogin(String login) {
        return persistencia.findByLogin(login);
    }

    @Override
    public void delete(Paciente pacienteDetalle) {
        persistencia.delete(pacienteDetalle);
    }
    
}
