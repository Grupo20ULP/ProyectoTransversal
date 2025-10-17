package proyectotransversal;

import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.CursadaData;
import Persistencia.MateriaData;
import java.time.LocalDate;

/**
 *
 * @author Gomez Heber,Carreras Juan, Zerdá Nehuen , Galan Federico, Carreño
 * Lucas
 */
public class ProyectoTransversal {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        AlumnoData ad = new AlumnoData();
        Alumno alm = new Alumno(874849565, "gomez", "heber", LocalDate.of(2020,
            10, 15), true);
        ad.guardarAlumno(alm);
        MateriaData md = new MateriaData();
        CursadaData cd = new CursadaData();
        // main mas ordenado para que se entienda
        // Crear y guardar un alumno
        Alumno alumno1 = new Alumno(45678900, "Gomez", "Heber", LocalDate.of(
            2000, 5, 10), true);
        ad.guardarAlumno(alumno1);
        // Crear y guardar una materia
        Materia materia1 = new Materia("Programacion I", 2025, true);
        md.insertarMateria(materia1);
        // Inscribir al alumno en esa materia (creo una cursada)
        Cursada cursada1 = new Cursada(alumno1, materia1, 8.5);
        cd.guardarCursada(cursada1);
    }
}
