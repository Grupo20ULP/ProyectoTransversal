package Persistencia;

import Modelo.Alumno;
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
 * @author Gomez Heber,Carreras Juan, Zerdá Nehuen , Galan Federico, Carreño
 * Lucas
 */
public class AlumnoData {

    private Connection con = null;

    public AlumnoData () {
        con = Conexion.getConectar();
    }

    public void guardarAlumno (Alumno alumno) {
        String sql
            = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    alumno.setIdAlumno(rs.getInt(1));
                    JOptionPane.showMessageDialog(null,
                        "Alumno guardado exitosamente. ID: " + alumno.
                            getIdAlumno());
                }
                rs.close();
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar alumno: " + e.
                getMessage());
        }
    }

    public Alumno buscarAlumnoPorId (int idAlumno) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE idAlumno = ? AND estado = true";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").
                    toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró alumno con ID: " + idAlumno);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar alumno: " + e.
                getMessage());
        }
        return alumno;
    }

    public List<Alumno> actualizarAlumno () {
        List<Alumno> alumnos = new ArrayList<>();
        String sql
            = "SELECT * FROM alumno WHERE estado = true ORDER BY apellido, nombre";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").
                    toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar alumnos: " + e.
                getMessage());
        }
        return alumnos;
    }

    public void altaLogica (Alumno alumno) {
        String sql
            = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento = ?, estado = ? WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Alumno editado exitosamente");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se pudo editar el alumno");
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar alumno: " + e.
                getMessage());
        }
    }

    public void bajaLogica (int idAlumno) {
        String sql = "UPDATE alumno SET estado = false WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Alumno eliminado correctamente");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró alumno con ID: " + idAlumno);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar alumno: "
                + e.getMessage());
        }
    }
}
