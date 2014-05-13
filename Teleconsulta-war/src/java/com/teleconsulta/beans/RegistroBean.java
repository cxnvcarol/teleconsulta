/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ RegistroBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.beans;

import com.teleconsulta.entities.Paciente;
import com.teleconsulta.entities.TipoDocumento;
import com.teleconsulta.entities.TipoUsuario;
import com.teleconsulta.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * Managed Bean encargado de la administración de los usuarios del sistema
 * @author Juan Sebastián Urrego
 */
public class RegistroBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    

    /**
     * Mensaje utilizado para mostrar información importante al usuario.
     */
    private String mensaje;
    
    /**
     * Referencia al objeto que se está mostrando al cliente. En este objeto se almacenan los datos del cliente.
     */
    private Usuario usuario;

    /**
     * Muestra la ventana de estado
     */
    private boolean mostrarVentana;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /*
     * Constructor sin argumentos de la clase
     */
    public RegistroBean() 
    {

        //usuarioServices = new ServicioRegistroMock();

          
        mostrarVentana=false;
        usuario = new Usuario();

    }

    //-----------------------------------------------------------
    // Getter y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el usuario actual.
     * @return usuario Usuario actual
     */
    public Usuario getUsuario()
    {
        return usuario;
    }

    /**
     * Modifica el usuario actual
     * @param usuario Nuevo usuario
     */
    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    /**
     * Método que devuelve los tipos de documentos a ser visualizados
     * @return sitems Tipos de documentos
     */
    public SelectItem[] getTiposDocumentos()
    {
        TipoDocumento[] tipos = TipoDocumento.values();
        SelectItem[] sitems = new SelectItem[tipos.length];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }

    
    /**
     * Método que retorna los tipos de usuario a ser visualizados 
     * @return
     */
    public SelectItem[] getTiposUsuario()
    {

        TipoUsuario[] tipos = TipoUsuario.values();
        SelectItem[] sitems = new SelectItem[tipos.length];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }

   

    /**
     * Devuelve el mensaje que contiene información sobre algún tipo de estado
     * @return mensaje Mensaje a devolver
     */
    public String getMensaje()
    {
        return mensaje;
    }

   
    /**
     * Devuelve el estado para mostrar o no la ventana popUp
     * @return mostrarVentana Estado para mostrar o no ventana
     */
    public boolean isMostrarVentana()
    {
        return mostrarVentana;
    }

    /**
     * Modifica el estado para mostrar la ventana popUp
     * @param mostrarVentana Nuevo estado
     */
    public void setMostrarVentana(boolean mostrarVentana)
    {
        this.mostrarVentana = mostrarVentana;
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Crea un usuario con los datos proporcionados por el cliente.
     * @return login Redirecciona a la página principal
     */
    public String registrarUsuario()
    {
        try
        {
            if(usuario.getTipoUsuario() == null)
            {
                usuario.setTipoUsuario(TipoUsuario.Medico);
            }
            //usuarioServices.registrar(usuario);          
            mensaje = "Su cuenta ha sido creada exitosamente.";
            mostrarVentana = true;
            destroyBean();            
            return "login";
        } 
        catch (Exception e)
        {
            mensaje = "Ocurrió un error al momento de crear su cuenta.";
            
            mostrarVentana = true;
            return "registro";
        }
    }

        /**
     * Crea un usuario con los datos proporcionados por el cliente.
     * @return login Redirecciona a la página principal
     */
    public void registrarPaciente()
    {
        try
        {

//            Paciente toadd=new Paciente(mensaje, mensaje, null, mensaje, estatura)
  //          usuarioServices.registrar(usuario);
            mensaje = "Su cuenta ha sido creada exitósamente.";
            mostrarVentana = true;
            limpiar();
           
        }
        catch (Exception e)
        {
            mensaje = "Ocurrió un error al momento de crear su cuenta.";

            mostrarVentana = true;
            
        }
    }


    public List<Usuario> getClientes()
    {
        //return usuarioServices.darClientes();
        return null;
    }

    public void eliminar(ActionEvent evento) //throws OperacionInvalidaException
    {
      
       // try
        //{
            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            String clientId = (String) map.get("clientId");

            //usuarioServices.eliminarCliente(clientId);
/*
        }
        catch(OperacionInvalidaException e)
        {
            throw new OperacionInvalidaException(e.getMessage());
            
        }
        */
    
    }
    /**
     * Método que remueve este bean del contexto.
     */
    public void destroyBean()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("registroBean");
    }

    /**
     * Cierra la ventana popUp
     * @return login Devuelve a la ventana de inicio
     */
    public String cerrarVentana()
    {
        mostrarVentana=false;
        return "login";
    }

    /**
     * Elimina la información del usuario
     */
    public void limpiar()
    {
        usuario=new Usuario();
    }

}
