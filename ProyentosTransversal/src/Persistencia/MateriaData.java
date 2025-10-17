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
 * @author Carreño Lucas
 */
public class MateriaData {

    private Connection con = null;

    public MateriaData () {
        con = (Connection) Conexion.getConectar();
    }
    //////////////////INSERTAR MATERIA///////////////////////
    public void insertarMateria (Materia materia) {
        String sql
            = "INSERT INTO materia(nombre, anio, estado) VALUES( ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isEstado());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    materia.setIdMateria(rs.getInt(1));
                    JOptionPane.showMessageDialog(null,
                        "Materia guardada, ID: " + materia.
                            getIdMateria());
                }
                rs.close();
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar materia: " + e.
                getMessage());
        }
    }
    //////////////////////CONTROL DE DUPLICADO///////////////////
    public boolean materiaDuplicada(String nombre, int anio) {
    String sql = "SELECT COUNT(*) FROM materia WHERE LOWER(nombre) = LOWER(?) AND anio = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nombre); 
        ps.setInt(2, anio);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException ex) {
        System.err.println("Error existe otra materia con el mismo nombre y año: " + ex.getMessage());
    } 
    return false;
    }
    //////////////////////////MATERIAS ACTIVAS/////////////////////////
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
                materia.setAnio(rs.getInt("anio"));
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
    ///////////////////MATERIAS INACTIVAS//////////////////////
    public List<Materia> materiasInactivas() {
    List<Materia> materias = new ArrayList<>();
    String sql 
            = "SELECT * FROM materia ORDER BY nombre"; 
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Materia materia = new Materia();
            materia.setIdMateria(rs.getInt("idMateria"));
            materia.setNombre(rs.getString("nombre"));
            materia.setAnio(rs.getInt("anio"));
            materia.setEstado(rs.getBoolean("estado")); 
            materias.add(materia);
        }
        ps.close();
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error en actualizar materias: " + e.getMessage());
        }
        return materias;
    }

    /////////////////BUSCAR POR ID//////////////////////////
    public Materia buscarMateriaPorId(int idMateria) {
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE idMateria = ? AND estado = true";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró materia con ID: " + idMateria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar materia: " + e.getMessage());
        }
        return materia;
    }

    /////////////////ALTA LOGICA//////////////////////////
    public void altaLogica (Materia materia) {
        String sql 
                = "UPDATE materia SET estado = true WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, materia.getIdMateria()); 
        int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Materia dada de alta.");
            } else {
            JOptionPane.showMessageDialog(null, "No se encontro la materia para dar de alta");
            }
        
            ps.close();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al dar de alta a la materia: " + e.getMessage());
        }
    }
    
//////////////////////////BAJA/////////////////////////////

    public void bajaLogica (int idMateria) {
        String sql = "UPDATE materia SET estado = false WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Materia dada de baja");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontro materia con ID: " + idMateria);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al dar de baja a la materia: "
                + e.getMessage());
        }
    }
        
    //////////////////////BORRAR////////////////////////////
    public void borrarMateria (int idMateria) {
        String sql = "DELETE FROM materia WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "Materia eliminada.");
            }
            else {
                JOptionPane.showMessageDialog(null,
                    "No se encontro materia con ID: " + idMateria);
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar materia: " + e.
                getMessage());
        }
    }

    public List<Materia> obtenerMaterias() {
            List<Materia> materias = new ArrayList<>();
    String sql = "SELECT * FROM materia"; // o agregar WHERE activo=true si corresponde
    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Materia m = new Materia();
            m.setIdMateria(rs.getInt("idMateria"));
            m.setNombre(rs.getString("nombre"));
            // agregá más campos si tu modelo los tiene
            materias.add(m);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar materias: " + e.getMessage());
    }
    return materias;
    }
}