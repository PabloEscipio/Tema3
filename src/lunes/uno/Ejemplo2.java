package lunes.uno;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Ejemplo2 {
    public static void main(String[] args) {
        String usuario = "root";
        String password = "Tarde2022";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/alumnos";

        String sql = "update profesor set salario=salario+200 where nombre =?";

        Connection cn;
        PreparedStatement ps;
        Scanner sc = new Scanner(System.in);

        try {
            // Cargar driver
            Class.forName(driver);

            // Establecer conexion
            cn = DriverManager.getConnection(url,usuario,password);

            // Cargar instrucción
            ps=cn.prepareStatement(sql);
            System.out.println("Introduzca el nombre del profesor");
            String nombre = sc.nextLine();
            ps.setString(1,nombre);

            // Ejecutamos la instrucción
            int numeroReg = ps.executeUpdate();

            if (numeroReg>0){
                System.out.println("Actualización realizada");
            }else {
                System.out.println("Actualización no realizada");
            }

        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    } // main end
} // class end
