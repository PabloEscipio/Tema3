package miercoles.uno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static int pedirISBN() {
        System.out.println("Introduce el ISBN deseado:");
        return Integer.parseInt(sc.nextLine());
    }

    public static String pedirTitulo() {
        System.out.println("Introduce el titulo deseado:");
        return sc.nextLine();
    }

    public static String pedirAutor() {
        System.out.println("Introduce el autor deseado:");
        return sc.nextLine();
    }

    public static float pedirPrecio() {
        System.out.println("Introduce precio deseado:");
        return Float.parseFloat(sc.nextLine());
    }

    public static boolean existe(int pISBN) {
        boolean rBoolean = false;

        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/biblio990";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn;
        PreparedStatement ps;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
            ps = cn.prepareStatement("SELECT * FROM libro WHERE isbn = " + pISBN);
            ResultSet rs = ps.executeQuery();

            rBoolean = rs.next();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return rBoolean;
    }

    public static void test3() {
        int isbn = pedirISBN();
        Libro libro = new Libro();

        if (existe(isbn)) {
            libro.setISBN(isbn);
            libro.read();
            libro.setPrecio(libro.getPrecio() + 30);
            libro.update();
            System.out.println("Hecho!!\nEl nuevo precio es: " + libro.getPrecio());
        } else {
            System.out.println("El libro no existe");
        }
    }

    // Faltaria hacer que si no existe el isbn reste a todos los libros un 5% de su precio
    public static void test4() {
        int isbn = pedirISBN();
        float precio = pedirPrecio();

        Libro libro = new Libro();

        if (existe(isbn)) {
            libro.read();
            if (libro.getPrecio() < precio) {
                libro.delete();
            }
        }
    }

    public static String busquedaLibro() {
        String rString;
        int isbn;
        Libro libro = new Libro();

        isbn = pedirISBN();
        if (existe(isbn)) {
            libro.setISBN(isbn);
            libro.read();
            rString = libro.toSting();
        } else {
            rString = "El libro buscado no existe";
        }
        return rString;
    }

    public static void crearLibro() {
        Libro libro = new Libro();

        libro.setISBN(pedirISBN());
        if (!existe(libro.getISBN())) {
            libro.setTitulo(pedirTitulo());
            libro.setAutor(pedirAutor());
            libro.setPrecio(pedirPrecio());
            libro.create();
            System.out.println("Libro creado");
        } else {
            System.out.println("El libro ya existe");
        }
    }

    public static void main(String[] args) {
        // Other
        // Variables
        byte uIndice = -1;

        // Programa
        do {
            try {
                System.out.println("***** Indice *****");
                uIndice = Byte.parseByte(sc.nextLine());

                switch (uIndice) {
                    case 0 -> System.out.println("Cerrando el programa...");
                    case 1 -> System.out.println(busquedaLibro());
                    case 2 -> crearLibro();
                    case 3 -> test3();
                    case 4 -> test4();
                    default -> System.out.println("Indice incorrecto");
                } // switch end
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (uIndice != 0);
        sc.close();
    } // main end
} // class end
