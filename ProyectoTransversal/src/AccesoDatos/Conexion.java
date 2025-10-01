/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;


import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

/**
 *
 * @author abate
 */
public class Conexion {
    
    private static final String URL="jdbc:mariadb://localhost:3306/";
    private static final String DB="gp20uni";
    private static final String USUARIO="root";
    private static final String CLAVE="";
    private static Connection conexion;

    public Conexion() {
    }
    
    public static Connection getConectar(){
        if(conexion == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion=(Connection) DriverManager.getConnection(URL+DB,USUARIO,CLAVE);
                JOptionPane.showMessageDialog(null, "Se conectó");
                
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Problemas accediendo a la clase "+e.getMessage());
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos "+ex.getMessage());
            }
        }
        return conexion;
    }
}