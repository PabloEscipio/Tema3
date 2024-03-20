package profesor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Baja {

    public static void baja() {
        try {
            System.out.println("BAJA");
            System.out.print("Introduzca nombre a buscar: ");
            String nombre = new Scanner(System.in).nextLine();


            boolean resultado = Consulta.consulta(nombre);
            Connection con = Conexion.conectar();
            if (resultado) {
                System.out.print("Introduzca id a borrar: ");
                int id = new Scanner(System.in).nextInt();
                Statement sentencia = con.createStatement();

                String sql = "DELETE FROM estudiantes WHERE id = " + id;

                int esBorrado = sentencia.executeUpdate(sql);
                if (esBorrado != 0) {
                    System.out.println("Borrado correcto.");
                } else {
                    System.out.println("Borrado incorrecto.");
                }
            }

            con.close();


        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error!");
        } catch (InputMismatchException e) {
            System.out.println("Datos erroneos!");
        }
    }
}



