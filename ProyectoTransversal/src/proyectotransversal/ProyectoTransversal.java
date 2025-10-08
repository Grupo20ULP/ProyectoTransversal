/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectotransversal;

import Modelo.Alumno;
import Persistencia.AlumnoData;
import Vistas.MenuPrincipal;
import java.time.LocalDate;

/**
 *
 * @author abate, Gomez Heber,CarrerasJuan, Zerdá Nehuen , Galan Federico, Carreño Lucas
 */
public class ProyectoTransversal {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        
        AlumnoData ad = new AlumnoData();
        Alumno alum= new Alumno(23325841, "Gomez", "Heber", LocalDate.of(2000, 1, 20), true);
        ad.agregarAlumno(alum);
        
    }
}
