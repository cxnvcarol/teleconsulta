package com.teleconsulta.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Alarma implements Serializable {
	
    @Temporal(javax.persistence.TemporalType.DATE)
	private Date fecha;
	private String tipo;
	private String descripcion;
    @Id
    @GeneratedValue
    private Long id;

    public Alarma() {
    }
	
	public Alarma(Date fecha, String tipo, String descripcion){
		this.fecha = fecha;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public String getTipo(){
		return tipo;
	}

    public Long getId() {
        return id;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
