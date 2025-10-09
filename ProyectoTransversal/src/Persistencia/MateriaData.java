/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Materia;
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
 * @author Juan
 */
public class MateriaData {

    private Connection con = null;

    public MateriaData () {
        con = (Connection) Conexion.getConectar();
    }

    public void insertarMateria (Materia materia) {
        String sql
            = "INSERT INTO materia(nombre, fechaMateria, estado) VALUES( ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setDate(2, java.sql.Date.valueOf(materia.getFechaMateria()));
            ps.setBoolean(3, materia.isEstado());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    materia.setIdMateria(rs.getInt(1));
                    JOptionPane.showMessageDialog(null,
                        "Materia guardado exitosamente. ID: " + materia.
                            getIdMateria());
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

    public List<Materia> actualizarmateria () {
        List<Materia> materias = new ArrayList<>();
        String sql
            = "SELECT * FROM materia WHERE estado = true ORDER BY nombre";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setFechaMateria(rs.getDate("fechaMateria").
                    toLocalDate());
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Materias: "
                + e.
                    getMessage());
        }
        return materias;
    }

    public Materia buscarMateriaPorId (int idmateria) {
        Materia materia = null;
        String sql
            = "SELECT * FROM materia WHERE idMateria = ? AND estado = true";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idmateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setFechaMateria(rs.getDate("fechaMateria").
                    toLocalDate());
                materia.setEstado(rs.getBoolean("estado"));
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró materia con ID: " + idmateria);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar materia: " + e.
                getMessage());
        }
        return materia;
    }

    public void altaLogica (Materia materia) {
        String sql
            = "UPDATE materia SET nombre = ?, fechamateria = ?, estado = ? WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setDate(2, java.sql.Date.valueOf(materia.getFechaMateria()));
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getIdMateria());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Materia editada exitosamente");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se pudo editar la Materia");
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar Materia: " + e.
                getMessage());
        }
    }
//borrar

    public void bajaLogica (int idMateria) {
        String sql = "UPDATE materia SET estado = false WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Materia eliminada correctamente");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró Materia con ID: " + idMateria);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Materia: "
                + e.getMessage());
        }
    }

    public void borrarMateria (int idMateria) {
        String sql = "DELETE FROM materia WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Materia eliminada físicamente.");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró Materia con ID: " + idMateria);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar materia: " + e.
                getMessage());
        }
    }
}
