/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioCatalogoMockLocal.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.teleconsulta.servicios;

import com.teleconsulta.entities.Paciente;
import java.util.List;

/**
 * Contrato funcional de los servicios que se le prestan al catálogo
 * @author Juan Sebastián Urrego
 */
public interface IServicioTeleconsultaMockLocal
{

    /**
     */
    public List<Paciente> darPacientes();

    public Paciente darPaciente(String id);

    /**
     *
     * @param id
     * @param peso
     * @return
     */
    public String pushPeso(String id, int peso);

    /**
     *
     * @param id
     * @param sistolica
     * @param diastolica
     * @return
     */
    public String pushPresion(String id, double sistolica, double diastolica);
}
