package profesor;

import java.util.Scanner;

public class Principal {


    public static void main(String[] args) {


        boolean exit = false;

        while (!exit) {
            System.out.println("\n--------------------");
            System.out.println("1. Alta");
            System.out.println("2. Baja");
            System.out.println("3. Modificar");
            System.out.println("4. Consultar");
            System.out.println("5. Salir");
            System.out.println("--------------------");
            System.out.print("Introduzca opción: ");
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1:
                    Alta.alta();
                    break;
                case 2:
                    Baja.baja();
                    break;
                case 3:
                    Modificar.modificar();
                    break;
                case 4:
                    Consulta.consulta();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no valida!\n\n");
            }
        }
        System.out.println("Se ha cerrado el programa");

    }

}
