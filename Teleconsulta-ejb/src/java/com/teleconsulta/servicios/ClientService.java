/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teleconsulta.servicios;

import com.teleconsulta.entities.Paciente;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
/**
 * REST Web Service
 *
 * @author Carol
 */
@Path("data")
public class ClientService {

    @Context
    private UriInfo context;

    //@EJB
    private IServicioTeleconsultaMockLocal servicio;
    /**
     * Creates a new instance of ClientService
     */
    public ClientService() {
        servicio=new ServicioTeleconsultaMock();
    }

    /**
     * Retrieves representation of an instance of com.teleconsulta.beans.ClientService
     * @return an instance of java.lang.String
     */
    
    
    /**
     *
     * @param peso
     * @return
     */
    
    @GET
    @Produces("text/html")
    @Path("{id}")
    public String data(@PathParam("id")String id) {
        MultivaluedMap<String, String> c = context.getQueryParameters();
        
        String login = c.getFirst("login");
        String password = c.getFirst("password");
        
        Paciente paciente=servicio.darPorLogin(login);
        if(paciente.getContrasena().equals(password))
        {
             String id2 = paciente.getId();
            String pesoS = c.getFirst("peso");
        String sistolicaS = c.getFirst("sistolica");
        String diastolicaS = c.getFirst("diastolica");
        int peso=0;
        double diastolica,sistolica;
        String ansa="";
        if(pesoS!=null)
        {
            try {
                peso = Integer.parseInt(pesoS);
                
                ansa=servicio.pushPeso(id2,peso);
            } catch (Exception e) {
            }
        }
        if(sistolicaS!=null&&diastolicaS!=null)
        {
            try {
                sistolica=Double.parseDouble(sistolicaS);
                diastolica=Double.parseDouble(diastolicaS);
                ansa=servicio.pushPresion(id2,sistolica,diastolica);
            } catch (Exception e) {
            }
        }
        return "<h1>Hola que tal, el id del paciente es "+id+"y la respuesta es "+ansa+"</h1>";
        }
        else
        {
            String pesoS = c.getFirst("peso");
        String sistolicaS = c.getFirst("sistolica");
        String diastolicaS = c.getFirst("diastolica");
        int peso=0;
        double diastolica,sistolica;
        String ansa="";
        if(pesoS!=null)
        {
            try {
                peso = Integer.parseInt(pesoS);
                
                ansa=servicio.pushPeso(id,peso);
            } catch (Exception e) {
            }
        }
        if(sistolicaS!=null&&diastolicaS!=null)
        {
            try {
                sistolica=Double.parseDouble(sistolicaS);
                diastolica=Double.parseDouble(diastolicaS);
                ansa=servicio.pushPresion(id,sistolica,diastolica);
            } catch (Exception e) {
            }
        }
        return "<h1>Hola que tal, el id del paciente es "+id+"y la respuesta es "+ansa+"</h1>";
        }
        
        
    }
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object historialPresiones()
    {
        MultivaluedMap<String, String> c = context.getQueryParameters();
        String login = c.getFirst("login");
        String password = c.getFirst("password");
        
        Paciente paciente=servicio.darPorLogin(login);
        if(paciente.getContrasena().equals(password))
        {
            return paciente.getPresiones();
        
        }
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object historialPesos()
    {
        MultivaluedMap<String, String> c = context.getQueryParameters();
        String login = c.getFirst("login");
        String password = c.getFirst("password");
        
        Paciente paciente=servicio.darPorLogin(login);
        if(paciente.getContrasena().equals(password))
        {
            return paciente.getPesos();
        
        }
        return null;
    }
    */
    
    @POST
    @Produces("text/html")
    public String postdata() {
        MultivaluedMap<String, String> c = context.getQueryParameters();
        String login = c.getFirst("login");
        String password = c.getFirst("password");
        
        Paciente paciente=servicio.darPorLogin(login);
        if(paciente.getContrasena().equals(password))
        {
            String id = paciente.getId();
            String pesoS = c.getFirst("peso");
        String sistolicaS = c.getFirst("sistolica");
        String diastolicaS = c.getFirst("diastolica");
        int peso=0;
        double diastolica,sistolica;
        String ansa="";
        if(pesoS!=null)
        {
            try {
                peso = Integer.parseInt(pesoS);
                
                ansa=servicio.pushPeso(id,peso);
            } catch (Exception e) {
            }
        }
        if(sistolicaS!=null&&diastolicaS!=null)
        {
            try {
                sistolica=Double.parseDouble(sistolicaS);
                diastolica=Double.parseDouble(diastolicaS);
                ansa=servicio.pushPresion(id,sistolica,diastolica);
            } catch (Exception e) {
            }
        }
        return "<h1>Hola que tal, el id del paciente es "+id+"y la respuesta es "+ansa+"</h1>";
        }
        else
        {
            return "<h1>Lo sentimos, las credenciales no son correctas.</h1>";
        }
        
        
    }
}
