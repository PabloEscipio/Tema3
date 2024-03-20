package profesor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Alta {


    public static void alta() {
        try {
            System.out.println("ALTA");
            System.out.print("Introduzca nombre: ");
            String nombre = new Scanner(System.in).nextLine();
            System.out.print("Introduzca apellidos: ");
            String apellidos = new Scanner(System.in).nextLine();
            System.out.print("Introduzca edad: ");
            int edad = new Scanner(System.in).nextInt();

            Connection con = Conexion.conectar();
            Statement sentencia = con.createStatement();

            String sql = "INSERT INTO estudiantes (nombre, apellidos, edad) "
                    + "VALUES ('" + nombre + "', '" + apellidos + "', " + edad + ")";

            sentencia.executeUpdate(sql);
            System.out.println("Alta correcta.");
            con.close();

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error!");
        } catch (InputMismatchException e) {
            System.out.println("Datos erroneos!");
        }
    }

}
