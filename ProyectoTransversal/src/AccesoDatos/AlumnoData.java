/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author abate
 */
public class AlumnoData {
    private Connection con=null;

    public AlumnoData() {
        con = Conexion.getConectar();
    }
    
}
