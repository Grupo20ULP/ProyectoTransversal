package proyectotransversal;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import Vistas.VistaAlumnos;
import Modelo.Alumno;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.MateriaData;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author abate, Gomez Heber,Carreras Juan, Zerdá Nehuen , Galan Federico,
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
        MateriaData md = new MateriaData();
        Materia mate = new Materia("Matematica", 2025,
            true);
        md.insertarMateria(mate);
    }
}
