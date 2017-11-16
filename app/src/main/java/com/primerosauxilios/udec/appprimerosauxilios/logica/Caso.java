package com.primerosauxilios.udec.appprimerosauxilios.logica;

import java.util.ArrayList;

public class Caso {
    private int id;
    private String nombre;
    private String nombreAudioProcedimiento;
    private ArrayList<String> palabrasClaveBusqueda;
    private String procedimiento;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void asignarProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getProcedimiento() {
        return this.procedimiento;
    }

    public void asignarAudioAProcedimiento(String nombreAudio) {
        this.nombreAudioProcedimiento = nombreAudio;
    }

    public String getAudioProcedimiento() {
        return this.nombreAudioProcedimiento;
    }

    public void agregarPalabraClave(String palabraClave) {
    }

    public boolean eliminarPalabraClave(String palabraClave) {
        return false;
    }

    public boolean esPalabraClave(String palabraClave) {
        return false;
    }
}
