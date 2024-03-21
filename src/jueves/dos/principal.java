package jueves.dos;

import java.io.FileWriter;
import java.util.Scanner;

public class principal {
    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // Main
    public static void main(String[] args) {
        // Variables
        byte uIndice = -1;

        // Programa
        do {
            try {
                indice();
                uIndice = Byte.parseByte(sc.nextLine());

                switch (uIndice) {
                    case 0 -> System.out.println("Cerrando el programa...");
                    case 1 -> addCliente(pedirID());
                    case 2 -> addInmueble(pedirID());
                    case 3 -> eliminarCliente(pedirID());
                    case 4 -> eliminarInmueble(pedirID());
                    case 5 -> modificarCliente(pedirID());
                    case 6 -> modificarInmueble(pedirID());
                    case 7 -> mostrarClientes();
                    case 8 -> mostrarInmuebles(pedirCiudad());
                    case 9 -> maximoMedioMin();
                    case 10 -> incrementarPorciento(pedirIncremento());
                    case 11 -> volcarDatos();
                    default -> System.out.println("Indice incorrecto");
                }// switch end

            } catch (Exception e) {
                System.out.println("Main Error: " + e.getMessage());
            }
        } while (uIndice != 0);
        sc.close();
    } // main end

    // Indice
    public static void indice() {
        System.out.println("""
                                
                ***** INDICE *****
                1: Alta de cliente
                2: Alta de un inmueble
                3: Baja de un cliente
                4: Baja de un inmueble
                5: Modificar un cliente
                6: Modificar un inmueble
                7: Visualizar todos los clientes
                8: Visualizar los inmuebles de una ciudad
                9: Mostrar el importe medio, maximo y minimo
                10: Incrementar el % que se pedirá por teclado el importe de los inmuebles (todos)
                11: Volcar la base completa una fichero
                """);
    } // indice end

    // Pedir usuario
    public static boolean confirmacion() {
        boolean rBoolean = false;
        boolean estate = true;
        String value;
        System.out.println("\n" + "Desea realizar la operación: Y/n");
        do {
            value = sc.nextLine().toLowerCase();
            switch (value) {
                case "":
                case "y":
                case "yes":
                    estate = false;
                    rBoolean = true;
                    break;
                case "n":
                case "no":
                    estate = false;
                    break;
                default:
                    System.out.println("Y/n");
            }
        } while (estate);
        return rBoolean;
    }

    public static int pedirIncremento() {
        System.out.println("Porcentaje a aumentar");
        return Integer.parseInt(sc.nextLine());
    }

    public static int pedirID() {
        System.out.println("Introduce ID:");
        return Integer.parseInt(sc.nextLine());
    }

    public static String pedirNombre() {
        System.out.println("Introduce nombre:");
        return sc.nextLine();
    }

    public static String pedirCIF() {
        System.out.println("Introduce CIF:");
        return sc.nextLine();
    }

    public static String pedirCiudad() {
        System.out.println("Introduce ciudad:");
        return sc.nextLine();
    }

    public static int pedirCliente() {
        System.out.println("Introduce ID de cliente:");
        return Integer.parseInt(sc.nextLine());
    }

    public static String pedirDireccion() {
        System.out.println("Introduce dirección:");
        return sc.nextLine();
    }

    public static String pedirProvincia() {
        System.out.println("Introduce provincia:");
        return sc.nextLine();
    }

    public static int pedirImporte() {
        System.out.println("Introduce importe:");
        return Integer.parseInt(sc.nextLine());
    }

    // Añadir cliente
    public static void addCliente(int id) {
        if (!ClienteDAO.existe(id)) {
            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente.setNombre(pedirNombre());
            cliente.setCif(pedirCIF());
            cliente.setCiudad(pedirCiudad());
            System.out.println(cliente.toString(false));
            if (confirmacion()) {
                if (ClienteDAO.create(cliente)) {
                    System.out.println("Cliente creado");
                }
            } else {
                System.out.println("Operacion cancelada");
            }
        } else {
            System.out.println("El cliente ya existe");
        }
    }

    public static void addInmueble(int id) {
        if (!InmuebleDAO.existe(id)) {
            Inmueble inmueble = new Inmueble();
            inmueble.setId(id);
            inmueble.setCliente(pedirCliente());
            if (ClienteDAO.existe(inmueble.getCliente())) {
                inmueble.setDireccion(pedirDireccion());
                inmueble.setCiudad(pedirCiudad());
                inmueble.setProvincia(pedirProvincia());
                inmueble.setImporte(pedirImporte());
                System.out.println(inmueble.toString(false));
                if (confirmacion()) {
                    if (InmuebleDAO.create(inmueble)) {
                        System.out.println("Inmueble creado");
                    }
                } else {
                    System.out.println("Operacion cancelada");
                }
            } else {
                System.out.println("El cliente no existe");
            }
        } else {
            System.out.println("El inmueble ya existe");
        }
    }

    public static void eliminarCliente(int id) {
        if (ClienteDAO.existe(id)) {
            Cliente cliente = ClienteDAO.read(id);
            System.out.println(cliente.toString(false));
            if (confirmacion()) {
                if (InmuebleDAO.existeCliente(id)) {
                    System.out.println("El cliente existe en la tabla \"Inmueble\". Operacion cancelda");
                } else {
                    if (ClienteDAO.delete(id)) {
                        System.out.println("Cliente eliminado");
                    }
                }
            } else {
                System.out.println("Operacion cancelada");
            }
        } else {
            System.out.println("El cliente no existe");
        }
    }

    public static void eliminarInmueble(int id) {
        if (InmuebleDAO.existe(id)) {
            Inmueble inmueble = InmuebleDAO.read(id);
            System.out.println(inmueble.toString(false));
            if (confirmacion()) {
                if (InmuebleDAO.delete(id)) {
                    System.out.println("Inmueble eliminado");
                }
            } else {
                System.out.println("Operacion cancelada");
            }
        } else {
            System.out.println("El inmueble no existe");
        }
    }

    public static void modificarCliente(int id) {
        if (ClienteDAO.existe(id)) {
            Cliente cliente = ClienteDAO.read(id);
            System.out.println(cliente.toString(false));
            cliente.setId(id);
            cliente.setNombre(pedirNombre());
            cliente.setCif(pedirCIF());
            cliente.setCiudad(pedirCiudad());
            System.out.println(cliente.toString(false));
            if (confirmacion()) {
                if (ClienteDAO.update(cliente)) {
                    System.out.println("Cliente modificado");
                }
            } else {
                System.out.println("Operacion cancelada");
            }
        } else {
            System.out.println("El cliente no existe");
        }
    }

    public static void modificarInmueble(int id) {
        if (InmuebleDAO.existe(id)) {
            Inmueble inmueble = InmuebleDAO.read(id);
            System.out.println(inmueble.toString(false));
            inmueble.setId(id);
            inmueble.setCliente(pedirCliente());
            inmueble.setDireccion(pedirDireccion());
            inmueble.setCiudad(pedirCiudad());
            inmueble.setProvincia(pedirProvincia());
            inmueble.setImporte(pedirImporte());
            System.out.println(inmueble.toString(false));
            if (confirmacion()) {
                if (InmuebleDAO.update(inmueble)) {
                    System.out.println("Inmueble modificado");
                }
            } else {
                System.out.println("Operacion cancelada");
            }
        } else {
            System.out.println("El inmueble no existe");
        }
    }

    public static void mostrarClientes() {
        for (Cliente cliente : ClienteDAO.readAll()) {
            System.out.println(cliente.toString(true));
        }
    }

    public static void mostrarInmuebles(String ciudad) {
        for (Inmueble inmueble : InmuebleDAO.readAll()) {
            if (inmueble.getCiudad().equalsIgnoreCase(ciudad)) {
                System.out.println(inmueble.toString(true));
            }
        }
    }

    public static void maximoMedioMin() {
        int importe;
        int max = 0;
        int min = 10000000;
        int medio = 0;
        for (Inmueble inmueble : InmuebleDAO.readAll()) {
            importe = inmueble.getImporte();
            max = Math.max(importe, max);
            min = Math.min(importe, min);
            medio += importe;
        }
        medio /= InmuebleDAO.readAll().size();
        System.out.println("Maximo:" + max + "\nMinimo: " + min + "\nMedio: " + medio);
    }

    public static void incrementarPorciento(int incremento) {
        for (Inmueble inmueble : InmuebleDAO.readAll()) {
            inmueble.setImporte(inmueble.getImporte() * (1 + incremento / 100));
            InmuebleDAO.update(inmueble);
        }
    }

    public static void volcarDatos() {
        StringBuilder stringCliente = new StringBuilder();
        StringBuilder stringInmueble = new StringBuilder();
        FileWriter fwBD;
        try {
            for (Cliente cliente : ClienteDAO.readAll()) {
                stringCliente.append(cliente.toString(true)).append("\n");
            }
            fwBD = new FileWriter("txt/seis/Clientes.txt");
            fwBD.write(stringCliente.toString());
            fwBD.close();
            for (Inmueble inmueble : InmuebleDAO.readAll()) {
                stringInmueble.append(inmueble.toString(true)).append("\n");
            }
            fwBD = new FileWriter("txt/seis/Inmueble.txt");
            fwBD.write(stringInmueble.toString());
            fwBD.close();
            System.out.println("Hecho!!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

} // class end
