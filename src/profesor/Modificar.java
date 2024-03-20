package profesor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Modificar {

    public static void modificar() {
        try {
            System.out.println("Modificar");
            System.out.print("Introduzca nombre a buscar: ");
            String nombre = new Scanner(System.in).nextLine();


            boolean resultado = Consulta.consulta(nombre);
            Connection con = Conexion.conectar();
            if (resultado) {
                System.out.print("Introduzca id a modificar: ");
                int id = new Scanner(System.in).nextInt();
                Statement sentencia = con.createStatement();
                System.out.print("Introduzca nombre: ");
                String nombreModificar = new Scanner(System.in).nextLine();
                System.out.print("Introduzca apellidos: ");
                String apellidos = new Scanner(System.in).nextLine();
                System.out.print("Introduzca edad: ");
                int edad = new Scanner(System.in).nextInt();

                String sql = "UPDATE estudiantes SET nombre = '" + nombreModificar + "' , apellidos = '" + apellidos
                        + "' , edad = " + edad + " WHERE id = " + id;

                int esModificado = sentencia.executeUpdate(sql);
                if (esModificado != 0) {
                    System.out.println("Modificado correcto.");
                } else {
                    System.out.println("Modificado incorrecto.");
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
