/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Cursada;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;

/**
 *
 * @author Federico Galan
 */
public class CursadaData {

    private Connection con = null;
    private AlumnoData ad = new AlumnoData();
    private MateriaData md = new MateriaData();

    public CursadaData () {
        con = Conexion.getConectar();
    }

    public void guardarCursada (Cursada c) {
        String sql
            = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql,
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, c.getNota());
            ps.setInt(2, c.getAlumno().
                getIdAlumno());
            ps.setInt(3, c.getMateria().
                getIdMateria());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setIdInscripto(rs.getInt(1));
            }
            rs.close();
            JOptionPane.showMessageDialog(null, "Cursada guardada con éxito");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cursada: "
                + e.getMessage());
        }
    }

    public void actualizarNota (int idAlumno, int idMateria, double nota) {
        String sql
            = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            ps.executeUpdate();
            JOptionPane.
                showMessageDialog(null, "Nota actualizada correctamente");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar nota: "
                + e.getMessage());
        }
    }

    public List<Cursada> obtenerCursadasPorAlumno (int idAlumno) {
        List<Cursada> cursadas = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cursada c = new Cursada();
                c.setIdInscripto(rs.getInt("idInscripto"));
                c.setNota(rs.getDouble("nota"));
                c.setAlumno(ad.buscarAlumnoPorId(rs.getInt("idAlumno")));
                c.setMateria(md.buscarMateriaPorId(rs.getInt("idMateria")));
                cursadas.add(c);
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar cursadas: "
                + e.getMessage());
        }
        return cursadas;
    }

    public List<Modelo.Alumno> obtenerAlumnosPorMateria (int idMateria) {
        List<Modelo.Alumno> alumnos = new ArrayList<>();
// el modelo.alumno nos dice el tipo de elementos que contiene la lista en este caso objetos de tipo Alumno.
// así cuando iteramos la lista sabemos que cada elemento es un Alumno y podemos hacer cosas como: un for each     
        String sql = "SELECT a.* FROM inscripcion i "
            + "JOIN alumno a ON i.idAlumno = a.idAlumno "
            + "WHERE i.idMateria = ? AND a.estado = true";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Modelo.Alumno alumno = new Modelo.Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").
                    toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al obtener alumnos por materia: " + e.getMessage());
        }
        return alumnos;
    }
}
