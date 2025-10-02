package models;

import java.io.Serializable;

public class Especialidad implements Serializable {
    private String nombre;

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void modificarNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
