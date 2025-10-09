/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectotransversal;

import Modelo.Alumno;
import Persistencia.AlumnoData;
import java.time.LocalDate;
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
        Alumno alm = new Alumno(645645645, "gomez", "heber", LocalDate.of(2020,
            10, 15), true);
        ad.guardarAlumno(alm);
        ad.bajaLogica(1);
        Alumno alumno1 = new Alumno(879879797, "Perez", "Juan", LocalDate.
            of(1999, 5, 15), true);
        ad.guardarAlumno(alumno1);
        List<Alumno> alumnos = ad.actualizarAlumno();
        for (Alumno a : alumnos) {
            System.out.println(a.getApellido() + ", " + a.getNombre());
        }
    }
}
