package com.primerosauxilios.udec.appprimerosauxilios.logica;

import java.util.ArrayList;

/**
 * Created by daniel on 3/10/17.
 */

public class Caso {

    private int id;
    private String nombre;
    private String procedimiento;
    private ArrayList<String> palabrasClaveBusqueda;
    private String nombreAudioProcedimiento;

    public Caso(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void asignarProcedimiento(String procedimiento){
        this.procedimiento = procedimiento;
    }

    public String getProcedimiento(){
        return procedimiento;
    }

    public void asignarAudioAProcedimiento(String nombreAudio){
        this.nombreAudioProcedimiento = nombreAudio;
    }


    public void agregarPalabraClave(String palabraClave){

    }

    public boolean eliminarPalabraClave(String palabraClave){
        return false;
    }

    public boolean esPalabraClave(String palabraClave){
        return false;
    }
}
