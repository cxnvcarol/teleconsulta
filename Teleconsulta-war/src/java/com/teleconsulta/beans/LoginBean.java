/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ LoginBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.beans;

import com.losalpes.excepciones.AutenticacionException;
import com.teleconsulta.entities.Paciente;
import com.teleconsulta.entities.TipoUsuario;
import com.teleconsulta.entities.Usuario;
import com.teleconsulta.servicios.IServicioPersistenciaMockLocal;
import com.teleconsulta.servicios.IServicioSeguridadMockLocal;
import com.teleconsulta.servicios.ServicioPersistenciaMock;
import com.teleconsulta.servicios.ServicioSeguridadMock;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Managed bean encargado de la autenticación en el sistema
 * @author Juan Sebastián Urrego
 */
@Stateful
public class LoginBean implements Serializable
{
    
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Nombre del usuario
     */
    private String usuario;

    /**
     * Contraseña del usuario
     */
    private String contraseña;

    /**
     * Usuario de la sesión
     */
    private Usuario sesion;

    /**
     * Relación con la interfaz adecuada para la autenticación de usuarios
     */
    private IServicioSeguridadMockLocal servicio;

    /**
     * Mensaje de error
     */
    private String mensajeError;
    
    
    /**
     * Mensaje utilizado para mostrar información importante al usuario.
     */
    private String mensaje;

    /**
     * Determina si existe error o no
     */
    private boolean error;
    
    private String fechaNacimientoPaciente;
    
    
    /**
     * Muestra la ventana de estado
     */
    private boolean mostrarVentana;
    
    private Paciente nuevoPaciente;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase
     */
    public String cerrarVentana()
    {
        mostrarVentana=false;
        return sesion!=null&&sesion.getTipoUsuario().equals(TipoUsuario.Medico)?"medico":"administrador";
    }
    public LoginBean()
    {
        persistencia=new ServicioPersistenciaMock();
        mostrarVentana=false;
        error=false;
        servicio=new ServicioSeguridadMock();
        nuevoPaciente=new Paciente();
    }
    
   
    /**
     * Devuelve el estado para mostrar o no la ventana popUp
     * @return mostrarVentana Estado para mostrar o no ventana
     */
    public boolean isMostrarVentana()
    {
        return mostrarVentana;
    }
    
    public void doForward() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    //String redirect = // define the navigation rule that must be used in order to redirect the user to the adequate page...
    NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
    if(sesion!=null)
    {
        if(sesion.getTipoUsuario().equals(TipoUsuario.Medico))
        {
            myNav.handleNavigation(facesContext, null, "catalogoMedico.jsf");
        }
        else
        {
         myNav.handleNavigation(facesContext, null, "index.jsf");   
        }
    }
    
    
    
}

    /**
     * Modifica el estado para mostrar la ventana popUp
     * @param mostrarVentana Nuevo estado
     */
    public void setMostrarVentana(boolean mostrarVentana)
    {
        this.mostrarVentana = mostrarVentana;
    }

    
    /**
     * Devuelve el mensaje que contiene información sobre algún tipo de estado
     * @return mensaje Mensaje a devolver
     */
    public String getMensaje()
    {
        return mensaje;
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Realiza la autenticación de un usuario que desea entrar al sistema
     * @return tipoUsuario Devuelve el tipo de usuario
     */
    public String ingresar()
    {      
        try
        {
            sesion = servicio.ingresar(usuario, contraseña);
            if (sesion.getTipoUsuario() == TipoUsuario.Administrador)
            {
                return "administrador";
            }
            else
            {
                return "medico";
            }
        }
        catch (AutenticacionException ex)
        {
            error=true;
            mensajeError=ex.getMessage();
            return "login";
        }
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el nombre del usuario
     * @return usuario Nombre del usuario
     */
    public String getUsuario()
    {
        return usuario;
    }

    /**
     * Modifica el nombre del usuario
     * @param usuario Nuevo nombre del usuario
     */
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
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
     * Modifica la contraseña de un usuario
     * @param contraseña Nueva contraseña
     */
    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    /**
     * Usuario a quien pertenece la sesión
     * @return sesion Usuario a quien pertenece la sesión
     */
    public Usuario getSesion()
    {
        return sesion;
    }

    /**
     * Modifica el usuario de la sesión
     * @param sesion Nuevo usuario
     */
    public void setSesion(Usuario sesion)
    {
        this.sesion = sesion;
    }

    /**
     * Devuelve el estado de la autenticación (si es error o no)
     * @return error Estado de autenticación
     */
    public boolean isError()
    {
        return error;
    }

    /**
     * Modifica el estado de error
     * @param error Nuevo estado
     */
    public void setError(boolean error)
    {
        this.error = error;
    }

    /**
     * Devuelve un mensaje de error
     * @return mensaje Mensaje de error
     */
    public String getMensajeError()
    {
        return mensajeError;
    }

    /**
     * Modifica el mensaje de error
     * @param mensajeError Nuevo mensaje de error
     */
    public void setMensajeError(String mensajeError)
    {
        this.mensajeError = mensajeError;
    }

    /**
     * Cierra el panel de error
     */
    public void cerrarPanelError()
    {
        error=false;
        mensajeError=null;
    }

    /**
     * Realiza un logout de la sesión del cliente
     * @return login Devuelve a la página de inicio
     */
    public String logout()
    {
        sesion=null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loginBean");
        return "login";
    }

    /**
     * @return the nuevoPaciente
     */
    public Paciente getNuevoPaciente() {
        return nuevoPaciente;
    }

    
    public void agregarPaciente()
    {
        if(sesion!=null && sesion.getTipoUsuario().equals(TipoUsuario.Medico))
        {
            sesion.crearPaciente(nuevoPaciente);
            persistencia.create(nuevoPaciente);
            persistencia.update(sesion);
             mensaje = "El paciente ha sido agregado a sus pacientes.";
            mostrarVentana = true;
            limpiar();
        }
            
    }
    /**
     * @param nuevoPaciente the nuevoPaciente to set
     */
    public void setNuevoPaciente(Paciente nuevoPaciente) {
        this.nuevoPaciente = nuevoPaciente;
    }
    public void limpiar()
    {
        nuevoPaciente=new Paciente();
    }

    /**
     * @return the fechaNacimientoPaciente
     */
    public String getFechaNacimientoPaciente() {
        return fechaNacimientoPaciente;
    }

    /**
     * @param fechaNacimientoPaciente the fechaNacimientoPaciente to set
     */
    public void setFechaNacimientoPaciente(String fechaNacimientoPaciente) {
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            nuevoPaciente.setFechaNacimiento(format.parse(fechaNacimientoPaciente));
        } catch (ParseException ex) {
            nuevoPaciente.setFechaNacimiento(new Date(1992, 12, 19));
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
