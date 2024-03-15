package viernes.uno;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    // Conexion
    public static Connection cn() {
        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/taxi990";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
        }
        return cn;
    }

    // Pedir al Usuario
    public static int pedirId() {
        System.out.println("Introduzca ID");
        return Integer.parseInt(sc.nextLine());
    }

    // Metodos
    public static void deleteVehiculoXConductorCS() {
        daoVehiculo vehiculo = new daoVehiculo();
        Connection cn = cn();
        PreparedStatement ps;
        ResultSet rs;
        FileWriter fwCopiaSeguridadBorrado;
        try {
            fwCopiaSeguridadBorrado = new FileWriter("txt/cuatro/CopiaSeguridadBorrado.txt", true);
            vehiculo.setConductor(pedirId());
            ps = cn.prepareStatement("SELECT idVehiculo FROM vehiculo WHERE conductor = ?");
            ps.setInt(1, vehiculo.getConductor());
            rs = ps.executeQuery();

            if (rs.next()) {
                vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));
                rs.close();
                vehiculo.read();
                fwCopiaSeguridadBorrado.write(vehiculo.toString());
                vehiculo.delete();
                fwCopiaSeguridadBorrado.close();
                cn.close();
            } else {
                System.out.println("No hay un vehiculo con ese conductor");
            }
        } catch (Exception e) {
            System.out.println("Error Main: " + e.getLocalizedMessage() + ", " + e.getCause());
        }
    }

    public static void test3() {
        PreparedStatement ps;
        Connection cn = cn();
        ResultSet rs;
        daoConductor conductor = new daoConductor();

        try {
            ps = cn.prepareStatement("SELECT conductor, count(*) 'Total' FROM vehiculo GROUP BY conductor");
            rs = ps.executeQuery();
            while (rs.next()) {
                conductor.setIdConductor(rs.getInt("conductor"));
                conductor.read();
                if (rs.getInt("Total") > 1) {
                    conductor.setEdad((short) (conductor.getEdad() + 10));
                } else {
                    conductor.setEdad((short) (conductor.getEdad() - 5));
                }
                conductor.update();
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error main: " + e.getLocalizedMessage());
        }
    }

    // MAIN
    public static void main(String[] args) {
        // Other
        // Variables
        byte uIndice = -1;

        // Programa
        do {
            System.out.println("***** INDICE *****");
            try {
                uIndice = Byte.parseByte(sc.nextLine());

                switch (uIndice) {
                    case 0 -> System.out.println("Cerrando el programa...");
                    case 1 -> deleteVehiculoXConductorCS();
                    case 2 -> test3();
                    case 3 -> System.out.println("Test3");
                } // switch end
            } catch (Exception e) {
                System.out.println("Error: " + e.getLocalizedMessage());
            } // try end
        } while (uIndice != 0);

        sc.close();
    } // main end
} // class end
