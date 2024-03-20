package profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static Connection con;
    static String url;

    public static Connection conectar() {
        url = "jdbc:mysql://localhost:3306/asata";

        try {
            con = DriverManager.getConnection(url, "root", "Tarde2022");
            //System.out.println("Conexión correcta.");
        } catch (SQLException e) {
            System.out.println("Error de conexión.");
        } finally {
            return con;
        }
    }

    // Hecho por el alumno
    public static Connection conectar(String pUrl, String pUser, String pPassword) {
        try {
            con = DriverManager.getConnection(pUrl, pUser, pPassword);
        } catch (Exception e) {
            System.out.println("Error en la conexión");
        } finally {
            return con;
        }
    }
}
