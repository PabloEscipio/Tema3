package martes.uno;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static void sqlExecute(String pSQL){
        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/bibliotecaexamen";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn;
        PreparedStatement ps;

        try {
            Class.forName(driver);

            cn = DriverManager.getConnection(url,user,password);

            ps = cn.prepareStatement(pSQL);

            int numeroReg = ps.executeUpdate();

            if (numeroReg>0){
                System.out.println("Actualización realizada");
            }else {
                System.out.println("Actualización no realizada");
            }
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public static ArrayList<Libros> leerRegistro(){
        Libros libro = new Libros();
        ArrayList<Libros> listLibros = new ArrayList<>();

        File frRegistro;
        Scanner fsRegistro;

        System.out.println("Añadiendo registros...");

        try {
            frRegistro = new File("txt/dos/registro.txt");
            fsRegistro = new Scanner(frRegistro);

            while (fsRegistro.hasNext()){
                libro.setCodigo(Integer.parseInt(fsRegistro.next()));
                libro.setTitulo(fsRegistro.next());
                libro.setPaginas(Short.parseShort(fsRegistro.next()));
                libro.setPrecio(Float.parseFloat(fsRegistro.next()));
                libro.setCodAutor(Integer.parseInt(fsRegistro.next()));

                listLibros.add(new Libros(libro));
            }

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return listLibros;
    }

    public static void addRegistro(){
        String sql;
        for (Libros valor : leerRegistro()){
            sql = "INSERT INTO libro VALUES(" + valor.toString() + ")";
            sqlExecute(sql);
        }
    }

    public static void main(String[] args) {
        // Utils
        // Others
        // Variables
        byte uIndice =-1;

        // Programa
        do {
            try {
            System.out.println("***** Indice *****");
            uIndice = Byte.parseByte(sc.nextLine());

            switch (uIndice){
                case 0 -> System.out.println("Cerrando el programa...");
                case 1 -> addRegistro();
                case 2 -> System.out.println("test2");
                case 3 -> System.out.println("test3");
                default -> System.out.println("Indice no valido");
            } // switch end

            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }

        }while (uIndice != 0);


        sc.close();
    } // main end
} // class end
