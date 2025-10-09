/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Juan
 */
public class Materia {

    private int idMateria;
    private String nombre;
    private LocalDate fechaMateria;
    private boolean estado;

    public Materia () {
    }

    public Materia (String nombre, LocalDate fechaMateria, boolean estado) {
        this.nombre = nombre;
        this.fechaMateria = fechaMateria;
        this.estado = estado;
    }

    public Materia (int idMateria, String nombre, LocalDate fechaMateria, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.fechaMateria = fechaMateria;
        this.estado = estado;
    }

    public int getIdMateria () {
        return idMateria;
    }

    public void setIdMateria (int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaMateria () {
        return fechaMateria;
    }

    public void setFechaMateria (LocalDate fechaMateria) {
        this.fechaMateria = fechaMateria;
    }

    public boolean isEstado () {
        return estado;
    }

    public void setEstado (boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString () {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre +
            ", fechaMateria=" + fechaMateria + ", estado=" + estado + '}';
    }
    


}
