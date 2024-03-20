package martes.dos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class pirncipal {
    // Scannner
    public static Scanner sc = new Scanner(System.in);

    // Main
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
                    case 0 -> System.out.println("Cerrando programa...");
                    case 1 -> System.out.println(AvionesDAO.readAll());
                    case 2 -> addFlota();
                    case 3 -> eliminarFlota();
                    case 4 -> System.out.println(consultarFlota());
                    case 5 -> modificarFlota();
                    default -> System.out.println("Indice incorrecto");
                } // switch end

            } catch (InputMismatchException e) {
                System.out.println("Indice erroneo");
            } catch (Exception e) {
                System.out.println("Fatal Error: " + e.getLocalizedMessage());
            }
        } while (uIndice != 0);
    } // main end

    // Pedir datos
    public static int pedirId() throws InputMismatchException {
        System.out.println("Introduce ID:");
        return Integer.parseInt(sc.nextLine());
    }

    public static String pedirMarca() throws InputMismatchException {
        System.out.println("Introduce marca:");
        return sc.nextLine();
    }

    public static String pedirModelo() throws InputMismatchException {
        System.out.println("Introduce modelo: ");
        return sc.nextLine();
    }

    public static short pedirCapacidad() throws InputMismatchException {
        System.out.println("Introduce capacidad:");
        return Short.parseShort(sc.nextLine());
    }

    // Metodos
    public static void addFlota() {
        AvionesDAO avion = new AvionesDAO();
        avion.setId(pedirId());
        avion.setMarca(pedirMarca());
        avion.setModelo(pedirModelo());
        avion.setCapacidad(pedirCapacidad());
        avion.create();
    }

    public static void eliminarFlota() {
        String modelos;
        AvionesDAO avion = new AvionesDAO();
        modelos = AvionesDAO.buscarPorModelo(pedirModelo());
        System.out.println(modelos);
        avion.setId(pedirId());
        avion.delete();
    }

    public static String consultarFlota() {
        AvionesDAO avion = new AvionesDAO();
        avion.setId(pedirId());
        avion.read();
        return avion.toString();
    }

    public static void modificarFlota() {
        AvionesDAO avion = new AvionesDAO();
        avion.setModelo(pedirModelo());
        AvionesDAO.buscarPorModelo(avion.getModelo());
        avion.setId(pedirId());
        avion.setMarca(pedirMarca());
        avion.setModelo(pedirModelo());
        avion.setCapacidad(pedirCapacidad());
    }
} // class end
