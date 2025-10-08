/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Alumno;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

/**
 *
 * @author abate, Gomez Heber, Galan Federico, Carreño Lucas
 */
public class AlumnoData {

    private Connection con = null;

    public AlumnoData () {
        con = Conexion.getConectar();
    }

    public void agregarAlumno (Alumno alum) {
        String sql
            = "INSERT INTO alumno(dni,apellido,nombre,fechaNacimiento,estado)"
            + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alum.getDni());
            ps.setString(2, alum.getApellido());
            ps.setString(3, alum.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alum.getFechaNacimiento()));
            ps.setBoolean(5, alum.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alum.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null,
                    "Se agrego correctamente el alumno");
            }
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el alumno "
                + e.getMessage());
        }
    }
}
