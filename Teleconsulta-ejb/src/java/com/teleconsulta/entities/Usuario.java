/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ Usuario.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Clase que representa un usuario del sistema
 * @author Juan Sebastián Urrego
 */
@Entity
public class Usuario implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Nombre del usuario
     */
    @Id
    private String login;

    /**
     * Contraseña del usuario
     */
    private String contraseña;

    /**
     * Tipo de usuario
     */
    private TipoUsuario tipoUsuario;

    /**
     * Nombres y apellidos del usuario
     */
    private String nombreCompleto;

    /**
     * Número de documento de identidad
     */
    private long documento;

    /**
     * Tipo de documento
     */
    private TipoDocumento tipoDocumento;

    
    
    @OneToMany(mappedBy = "medico")
    private List<Paciente> pacientes;
    /**
     * Profesión del usuario
     */
    //Todos son medicos, incluyendo el admin

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor de la clase sin argumentos
     */
    public Usuario()
    {
        pacientes=new ArrayList<Paciente>();

    }

    /**
     * Constructor de la clase con argumentos
     * @param nombre Nombre del usuario
     * @param contraseña Constraseña del usuario
     * @param tipo Tipo de usuario
     */
    public Usuario(String login, String contraseña, TipoUsuario tipoUsuario)
    {
        this.login = login;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        pacientes=new ArrayList<Paciente>();
    }
    
    public void crearPaciente(Paciente p)
    {
        p.setMedico(this);
        pacientes.add(p);
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el nombre de usuario
     * @return nombre Nombre del usuario
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * Modifica el nombre del usuario
     * @param nombre Nuevo nombre de usuario
     */
    public void setLogin(String nombre)
    {
        this.login = nombre;
    }

    /**
     * Devuelve la contraseña del usuario
     * @return contraseña Contraseña del usuario
     */
    public String getContraseña()
    {
        return contraseña;
    }

    /**
     * Modifica la contraseña del usuario
     * @param contraseña Nueva contraseña
     */
    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    /**
     * Devuelve el tipo de usuario
     * @return tipo Tipo de usuario
     */
    public TipoUsuario getTipoUsuario()
    {
        return tipoUsuario;
    }

    /**
     * Modifica el tipo de usuario
     * @param tipo Nuevo tipo de usuario
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Devuelve el número de identificación del usuario
     * @return documento Número de identificación del usuario
     */
    public long getDocumento()
    {
        return documento;
    }

    /**
     * Modifica el número de identificación del usuario
     * @param documento Número de identificación
     */
    public void setDocumento(long documento)
    {
        this.documento = documento;
    }

    /**
     * Devuelve el nombre completo del usuario
     * @return nombreCompleto Nombre completo del usuario
     */
    public String getNombreCompleto()
    {
        return nombreCompleto;
    }

    /**
     * Modifica el nombre de un usuario
     * @param nombreCompleto Nuevo nombre del usuario
     */
    public void setNombreCompleto(String nombreCompleto)
    {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Devuelve el tipo de documento que tiene un usuario
     * @return tipoDocumento Tipo de documento del usuario
     */
    public TipoDocumento getTipoDocumento()
    {
        return tipoDocumento;
    }

    /**
     * Modifica el tipo de documento del usuario
     * @param tipoDocumento Nuevo tipo de documento
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the pacientes
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

}
