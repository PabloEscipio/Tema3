package lunes.uno;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejemplo3 {
    public static Scanner sc = new Scanner(System.in);

    public static String nuevoArticulo(){
            System.out.println("Introduzca codigo del articulo");
            String codArt = sc.nextLine();
            System.out.println("Introduzca nombre del articulo");
            String nombreArt = sc.nextLine();
            System.out.println("Introduzca precio del articulo");
            String precioArt = sc.nextLine();
            System.out.println("Introduzca fabricante");
            String fabArt = sc.nextLine();

            return "INSERT INTO articulo VALUES (" + codArt + ",\"" + nombreArt + "\"," + precioArt + "," + fabArt + ")";
    } // nuevoArticulo

    public static String updateXfichero(){
        File frActualizar;
        Scanner fsActualizar;

        String codArt = "0";
        String precioArt = "0";

        try {
            frActualizar = new File("txt/uno/actualizar.txt");
            fsActualizar = new Scanner(frActualizar);

            codArt = fsActualizar.next();
            precioArt = fsActualizar.next();

            fsActualizar.close();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return "UPDATE articulo SET precio = " + precioArt + " WHERE codigoArticulo = " + codArt;
    }

    public static String incrementoFabricante(){
        System.out.println("Introduzca el fabricante");
        String fabArt = sc.nextLine();

        return "UPDATE articulo SET precio = precio*1.2 WHERE fabricante = " + fabArt;
    }

    public static String deletePrecioBajo(){
        System.out.println("Introduzca el precio minimo");
        String precioArt = sc.nextLine();

        return "DELETE FROM articulo WHERE precio < " + precioArt;
    }

    public static ArrayList<String> deleteXcodigo(){
        File frCodigo;
        Scanner fsCodigo;
        ArrayList<String> listCod = new ArrayList();


        try {
            frCodigo = new File("txt/uno/codigo.txt");
            fsCodigo = new Scanner(frCodigo);

            while (fsCodigo.hasNext()){
                listCod.add("DELETE FROM articulo WHERE  codigoArticulo like " + fsCodigo.next());
            }
        }catch (Exception e){
            System.out.println("Error1:" + e.getMessage());
        }
        return listCod;
    }

    public static ArrayList<String> updateXCodigoPrecio(){
        ArrayList<String> listUpdate = new ArrayList<>();
        File frUpdate;
        Scanner fsUpdate;

        try {
            frUpdate = new File("txt/uno/actualizar2.txt");
            fsUpdate = new Scanner(frUpdate);

            while (fsUpdate.hasNext()){
                listUpdate.add(fsUpdate.next());
            }
        }catch (Exception e){
            System.out.println("ERROR3: " + e.getMessage());
        }

        return listUpdate;
    }

    public static void sqlTiendaInformatica(String pSQL){
        String user  = "root";
        String password = "Tarde2022";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tiendainformatica";

        Connection cn;
        PreparedStatement ps;
        try {
            Class.forName(driver);
            cn= DriverManager.getConnection(url,user,password);
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
    } // nuevoArticulo

    public static void main(String[] args) {
        // Utils
        // SQL

        // Variables
        byte uIndice;

        int cod = 0;
        int precio = 0;
        // Programa
        do {
            System.out.println("Introduce una opción");
            uIndice = Byte.parseByte(sc.nextLine());

            switch (uIndice){
                case 0 -> System.out.println("Cerrando el programa...");
                case 1 -> sqlTiendaInformatica(nuevoArticulo());
                case 2 -> sqlTiendaInformatica(incrementoFabricante());
                case 3 -> sqlTiendaInformatica(deletePrecioBajo());
                case 4 -> sqlTiendaInformatica(updateXfichero());
                case 5 -> {
                    for (String valor : deleteXcodigo()){
                        sqlTiendaInformatica(valor);
                    }
                }
                case 6 ->{
                    for (String valor : updateXCodigoPrecio()){
                        sqlTiendaInformatica(valor);
                    }
                }
            } // switch end
        }while (uIndice != 0);

        sc.close();
    } // main end
} // class end
