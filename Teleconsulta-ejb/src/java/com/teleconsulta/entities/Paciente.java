/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * Usuario.java Universidad de los Andes (Bogotá - Colombia) Departamento de
 * Ingeniería de Sistemas y Computación Licenciado bajo el esquema Academic Free
 * License version 3.0
 *
 * Ejercicio: Muebles de los Alpes Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.teleconsulta.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente {

    private final static String ALARMA_HIP_BAJA = "El paciente presenta condicienes anormales de hipertension baja";
    private final static String ALARMA_HIP_ALTA = "El paciente presenta condicienes anormales de hipertension alta";
    private final static String ALARMA_HIP_ETA1 = "El paciente presenta Hipertension Etapa 1. Confirmar dentro de dos meses";
    private final static String ALARMA_HIP_ETA2 = "El paciente presenta Hipertension Etapa 2. Evualuar dentro de un meses";
    private final static String ALARMA_HIP_ETA3 = "El paciente presenta Hipertension Etapa 3. Evualuar maximo en una semana";
    private final static String ALARMA_PESO_ALTO = "El paciente presenta sobrepeso. Recomendar dieta balanceada para normalización";
    private final static String ALARMA_PESO_OBESO = "El paciente presenta obesidad. Recomendar dieta balanceada para normalización";
    private final static String ALARMA_PESO_BAJO = "El paciente presenta infrapeso. Recomendar dieta balanceada para normalización";
    private final static String TIPO_ALARMA_HIP_ETA1 = "Hipertension Etapa 1";
    private final static String TIPO_ALARMA_HIP_ETA2 = "Hipertension Etapa 2";
    private final static String TIPO_ALARMA_HIP_ETA3 = "Hipertension Etapa 3";
    private final static String TIPO_ALARMA_HIP_BAJA = "Hipertension Baja";
    private final static String TIPO_ALARMA_HIP_ALTA = "Hipertension Alta";
    private final static String TIPO_ALARMA_PESO_ALTO = "IMC alto";
    private final static String TIPO_ALARMA_PESO_OBESO = "IMC muy alto (obesidad)";
    private final static String TIPO_ALARMA_PESO_BAJO = "IMC bajo";
    public static final String MASCULINO = "Masculino";
    public static final String FEMENINO = "Femenino";
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Nombre del usuario
     */
    private String nombre;
    private String id;
    private Date fechaNacimiento;
    private String genero;
    private int estatura;
    /**
     * Devuelve un lista con todos las compras del usuario
     */
    private ArrayList<MedidaPeso> pesos;
    private ArrayList<MedidaPresion> presiones;
    private ArrayList<Alarma> alarmas;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor de la clase sin argumentos
     */
    public Paciente() {
    }

    /**
     * Constructor de la clase con argumentos
     *
     * @param nombre Nombre del usuario
     * @param contraseña Constraseña del usuario
     * @param tipo Tipo de usuario
     */
    public Paciente(String documento, String nombre, Date fechaNacimiento, String genero, int estatura) {
        this.id = documento;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;

        pesos = new ArrayList<MedidaPeso>();
        presiones = new ArrayList<MedidaPresion>();
        alarmas=new ArrayList<Alarma>();
        this.estatura=estatura;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @return the pesos
     */
    public ArrayList<MedidaPeso> getPesos() {
        return pesos;
    }

    /**
     * @return the presiones
     */
    public List<MedidaPresion> getPresiones() {
        return presiones;
    }

    public int getEdad() {
        return (new Date().getYear()) - fechaNacimiento.getYear();
    }
    public int getEstatura()
    {
        return estatura;
    }

    public void addPresion(double sist, double diast) {
        presiones.add(new MedidaPresion(sist, diast));
    }
    
    public void addPeso(int peso) {
        pesos.add(new MedidaPeso(peso));
    }

    public List<Alarma> getAlarmas() {
        return alarmas;
    }

    public String ultimaAlarma() {
        return alarmas.get(alarmas.size() - 1).getDescripcion();
    }

    /**
     * El metodo indica si hay o no alarma, segun la edad y los valores de la
     * sistolica y diastolica del paciente. En caso de haber alarma, este agrega
     * el tipo de alarma a el contenedor de alarmas.
     *
     * @param sistolica Valor de la sistolica
     * @param diastolica Valor de la diastolica
     * @return True si se agregó alarma, false de lo contrario.
     */
    public boolean alarmaPresion(double sistolica, double diastolica) {
        int edad = getEdad();
        if (edad < 18) {
            if (genero.equals(MASCULINO)) {
                if (sistolica >= 105 && sistolica <= 135 || diastolica >= 60 && diastolica <= 86) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_BAJA, ALARMA_HIP_BAJA);
                    alarmas.add(a);
                    return true;
                } else if (sistolica >= 145 || diastolica >= 90) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_ALTA, ALARMA_HIP_ALTA);
                    alarmas.add(a);
                    return true;
                }
            } else if (genero.equals(FEMENINO)) {
                if (sistolica >= 100 && sistolica <= 130 || diastolica >= 60 && diastolica <= 85) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_BAJA, ALARMA_HIP_BAJA);
                    alarmas.add(a);
                    return true;
                } else if (sistolica >= 140 || diastolica >= 90) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_ALTA, ALARMA_HIP_ALTA);
                    alarmas.add(a);
                    return true;
                }
            }
        } //Sí es mayor a 18 se usan lo criterios de la 3a tabla
        else {
            if (sistolica >= 140 && sistolica <= 159 || diastolica >= 90 && diastolica <= 99) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_ETA1, ALARMA_HIP_ETA1);
                alarmas.add(a);
                return true;
            } else if (sistolica >= 160 && sistolica <= 179 || diastolica >= 100 && diastolica <= 109) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_ETA2, ALARMA_HIP_ETA2);
                alarmas.add(a);
                return true;
            } else if (sistolica >= 180 || diastolica >= 110) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_HIP_ETA3, ALARMA_HIP_ETA3);
                alarmas.add(a);
                return true;
            }
        }
        return false;
    }

    /**
     * El metodo indica si hay o no alarma, segun la edad y los valores de la
     * sistolica y diastolica del paciente. En caso de haber alarma, este agrega
     * el tipo de alarma a el contenedor de alarmas.
     *
     * @param peso
     * @param estatura
     * @return
     */
    public boolean alarmaPeso(double peso) {
        double IMC = peso / (estatura * estatura)/10000;// /10000 porque la estatura está en cm
        int edad = getEdad();
        if (edad < 20) {
            if (genero.equals(MASCULINO)) {
                if (IMC <= 19) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_BAJO, ALARMA_PESO_BAJO);
                    alarmas.add(a);
                    return true;
                } else if (IMC >= 27 && IMC < 31) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_ALTO, ALARMA_PESO_ALTO);
                    alarmas.add(a);
                    return true;
                } else if (IMC >= 31) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_OBESO, ALARMA_PESO_OBESO);
                    alarmas.add(a);
                    return true;
                }
            }
            if (genero.equals(FEMENINO)) {
                if (IMC <= 18) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_BAJO, ALARMA_PESO_BAJO);
                    alarmas.add(a);
                    return true;
                } else if (IMC >= 26.5 && IMC < 32) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_ALTO, ALARMA_PESO_ALTO);
                    alarmas.add(a);
                    return true;
                } else if (IMC >= 32) {
                    Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_OBESO, ALARMA_PESO_OBESO);
                    alarmas.add(a);
                    return true;
                }
            }
        } else if (edad >= 20 && edad <= 60) {
            if (IMC <= 18.5) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_BAJO, ALARMA_PESO_BAJO);
                alarmas.add(a);
                return true;
            } else if (IMC >= 25 && IMC < 30) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_ALTO, ALARMA_PESO_ALTO);
                alarmas.add(a);
                return true;
            } else if (IMC >= 30) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_OBESO, ALARMA_PESO_OBESO);
                alarmas.add(a);
                return true;
            }
        } else if (edad > 60) {
            if (IMC < 25) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_BAJO, ALARMA_PESO_BAJO);
                alarmas.add(a);
                return true;
            } else if (IMC > 27) {
                Alarma a = new Alarma(new Date(), TIPO_ALARMA_PESO_ALTO, ALARMA_PESO_ALTO);
                alarmas.add(a);
                return true;
            }
        }
        return false;
    }
    
}

