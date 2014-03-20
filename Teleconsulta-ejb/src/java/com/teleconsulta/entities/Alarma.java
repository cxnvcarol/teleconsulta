package com.teleconsulta.entities;

import java.util.Date;



public class Alarma {
	
	private Date fecha;
	private String tipo;
	private String descripcion;
	
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
}
