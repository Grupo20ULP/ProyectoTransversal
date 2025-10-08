/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectotransversal;

import Modelo.Alumno;
import Persistencia.AlumnoData;
import Vistas.MenuPrincipal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author abate, Gomez Heber,CarrerasJuan, Zerdá Nehuen , Galan Federico,
 * Carreño Lucas
 */
public class ProyectoTransversal {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        AlumnoData ad = new AlumnoData();
        Alumno alm = new Alumno(1231231312, "gomez", "heber", LocalDate.of(2020,
            10, 15), true);
        ad.guardarAlumno(alm);
        ad.eliminarAlumno(1);
        Alumno alumno1 = new Alumno(40123456, "Perez", "Juan", LocalDate.
            of(1999, 5, 15), true);
        ad.guardarAlumno(alumno1);
        List<Alumno> alumnos = ad.listarAlumnosActivos();
        for (Alumno a : alumnos) {
            System.out.println(a.getApellido() + ", " + a.getNombre());
        }
    }
}
