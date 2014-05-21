/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioPersistenciaMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.servicios;

import com.teleconsulta.entities.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Implementación de los servicios de persistencia
 * @author Juan Sebastián Urrego
 */
@Stateless
@EJB(beanInterface = IServicioPersistenciaMockLocal.class, beanName = 
"ServicioPersistenciaMock", name = "servicioPersistencia")
public class ServicioPersistenciaMock implements  IServicioPersistenciaMockLocal, Serializable {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * La entidad encargada de persistir en la base de datos
     */
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * Lista con los vendedores del sistema
     */
    //private static ArrayList<Paciente> pacientes;

    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistenciaMock()
    {
        if (entityManager == null)
        {
            EntityManagerFactory ef=Persistence.createEntityManagerFactory("Teleconsulta-ejbPU");
            entityManager=ef.createEntityManager();
            if(findAll(Usuario.class).isEmpty())
            {
                createScenario();
            }
            entityManager.setFlushMode(FlushModeType.COMMIT);
        }
    }

    public void createScenario2(){
         for(int i=0;i<10;i++)
            {
                Date fNac=new Date();
                fNac.setYear(fNac.getYear()-5-i*i);
                Paciente nuevo=new Paciente("000"+i,"Maria Jose "+(i+1), fNac,(i%3==0?Paciente.MASCULINO:Paciente.FEMENINO),150);
                create(nuevo);
            }
    }
    
    public void createScenario(){
        Usuario administrador=new Usuario("admin", "adminadmin", TipoUsuario.Administrador);
                create(administrador);
         for(int i=0;i<10;i++)
            {
                Usuario medico=new Usuario("frank0"+i, "frankestein", TipoUsuario.Medico);
                create(medico);                
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
    public void create(Object t) {
        try {
            UserTransaction transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
            // persist object - add to entity manager
            entityManager.persist(t);
            // flush em - save to DB
            entityManager.flush();
            transaction.commit();
        } catch (RollbackException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
        }
    }

    /**
     * Permite modificar un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere modificar.
     */
    @Override
    public void update(Object obj)
    {
        try {
            UserTransaction transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();
            // persist object - add to entity manager
            entityManager.merge(obj);
            // flush em - save to DB
            entityManager.flush();
            transaction.commit();
        } catch (RollbackException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(ServicioPersistenciaMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
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
        return entityManager.createQuery("select O from " + c.getSimpleName() + " as O").getResultList();
    }

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de la entidadi.
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    @Override
    public Object findById(Class c, Object id) {
        Object returned=entityManager.find(c, id);
        if(returned!=null) entityManager.refresh(returned);
        return returned;
    }

    @Override
    public Paciente findByLogin(String login) {
        List aa = findAll(Paciente.class);
        for (Iterator it = aa.iterator(); it.hasNext();) {
            Paciente object = (Paciente)it.next();
            if(object.getLogin().equals(login))
                return object;            
        }
        return null;
        //return (Paciente) entityManager.createQuery("select O from " + Paciente.class.getSimpleName() + " as O where login="+login).getResultList().get(0);
    }

    @Override
    public void delete(Paciente pacienteDetalle) {
        entityManager.remove(pacienteDetalle);
    }
}
