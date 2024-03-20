package profesor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consulta {

    public static void consulta() {
        try {
            System.out.println("CONSULTA");

            Connection con = Conexion.conectar();
            Statement sentencia = con.createStatement();

            String sql = "SELECT * FROM estudiantes";

            ResultSet rs = sentencia.executeQuery(sql);
            System.out.println("Resultados:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                int edad = rs.getInt("edad");
                System.out.println("ID: " + id + "\tNombre: " + nombre + "\tApellidos: "
                        + apellidos + "\tEdad: " + edad);
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error!");
        }
    }

    public static boolean consulta(String dato) {
        try {

            Connection con = Conexion.conectar();
            Statement sentencia = con.createStatement();

            String sql = "SELECT * FROM estudiantes WHERE nombre LIKE '%" + dato + "%'";

            ResultSet rs = sentencia.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("No se encontró ningún registro.");
                con.close();
                return false;
            } else {
                System.out.println("Resultados:");
                do {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    int edad = rs.getInt("edad");
                    System.out.println("ID: " + id + "\tNombre: " + nombre + "\tApellidos: "
                            + apellidos + "\tEdad: " + edad);
                } while (rs.next());

                con.close();
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error!");
            return false;
        }
    }


}
