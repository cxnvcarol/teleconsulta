/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioPersistenciaMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.servicios;

import com.teleconsulta.entities.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementación de los servicios de persistencia
 * @author Juan Sebastián Urrego
 */
//@Stateless
@EJB(beanInterface = IServicioPersistenciaMockLocal.class, beanName = 
"ServicioPersistenciaMock", name = "servicioPersistencia")
public class ServicioPersistenciaMock implements  IServicioPersistenciaMockLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Lista con los vendedores del sistema
     */
    private static ArrayList<Paciente> pacientes;

    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock()
    {
        if (pacientes == null)
        {
            pacientes = new ArrayList<Paciente>();
            for(int i=0;i<10;i++)
            {
                Date fNac=new Date();
                fNac.setYear(fNac.getYear()-5-i*i);
                Paciente nuevo=new Paciente("000"+i,"Maria Jose "+(i+1), fNac,(i%3==0?Paciente.MASCULINO:Paciente.FEMENINO),150);
                pacientes.add(nuevo);
            }
        }
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    
    /**
     * Permite crear un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
     */
    @Override
    public void create(Object obj)
    {
        if (obj instanceof Paciente)
        {
            Paciente p = (Paciente) obj;
            pacientes.add(p);
        }
        //else if (obj instanceof Mueble)...
       
    }

    /**
     * Permite modificar un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere modificar.
     */
    @Override
    public void update(Object obj)
    {
        if (obj instanceof Paciente)
        {
            Paciente editar = (Paciente) obj;
            Paciente vendedor;
            for (int i = 0; i < pacientes.size(); i++)
            {
                vendedor = pacientes.get(i);
                if (vendedor.getId().equals(editar.getId()))
                {
                    pacientes.set(i, editar);
                    break;
                }

            }

        }
        
    }


    /**
     * Retorna la lista de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se encuentran en el sistema.
     */
    @Override
    public List findAll(Class c)
    {
        if (c.equals(Paciente.class))
        {
            return pacientes;
        } 
        //else if (c.equals(Vendedor.class))
        
        else
        {
            return null;
        }
    }

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de la entidadi.
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    @Override
    public Object findById(Class c, Object id)
    {
        if (c.equals(Paciente.class))
        {
            for (Object v : findAll(c))
            {
                Paciente ven = (Paciente) v;
                if (ven.getId().equals(id))
                {
                    return ven;
                }
            }
        } 
        //else if (c.equals(Mueble.class))
        
        return null;
    }
}
