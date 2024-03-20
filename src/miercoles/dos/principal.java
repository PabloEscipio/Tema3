package miercoles.dos;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class principal {
    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // Main
    public static void main(String[] args) {
        // Variables
        byte uIndice = -1;
        do {
            try {
                indice();
                uIndice = Byte.parseByte(sc.nextLine());
                switch (uIndice) {
                    case 0 -> System.out.println("Cerrando progama...");
                    case 1 -> System.out.println(listProductosToString());
                    case 2 -> System.out.println(buscarCodigo(pedirCodigo()));
                    case 3 -> incrementarPrecio(pedirCodigo());
                    case 4 -> borrarPorCodigo(pedirCodigo());
                    case 5 -> System.out.println("El precio medio es: " + calcularPrecioMedio());
                    case 6 -> crearProducto(pedirCodigo());
                    case 7 -> modificarProducto(pedirCodigo());
                    case 8 -> System.out.println("Test8");
                    case 9 -> baseDatosToFichero();
                    case 10 -> System.out.println("Test10");
                    default -> System.out.println("Indice incorrecto");
                } // switch end
            } catch (InputMismatchException e) {
                System.out.println("Numeros enteros porfavor");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (uIndice != 0);
        // Programa
        sc.close();
    } // main end

    // Mostrar Indice
    private static void indice() {
        System.out.println("""
                ***** Indice *****
                1: Mostrar por pantalla todos los productos
                2: Busca un codigo en la base de datos
                3: Incrementamos el precio en un 21%
                4: Borrar por codigo
                5: Calcular el precio medio
                6: Crear producto nuevo
                7: Modificar un producto
                8:
                9: Volcar datos a un fichero
                10:
                """);
    } // indice end

    // Pedir al usuario
    public static int pedirCodigo() {
        System.out.println("Introduce codigo: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static String pedirNombre() {
        System.out.println("Introduce nombre: ");
        return sc.nextLine();
    }

    public static String pedirDescripcion() {
        System.out.println("Introduce descripción:");
        return sc.nextLine();
    }

    public static float pedirPrecio() {
        System.out.println("Introduce precio: ");
        return Float.parseFloat(sc.nextLine());
    }

    public static boolean pedirConfirmacion() {
        boolean rBoolean = true;
        boolean estado = true;
        String respuesta;
        System.out.println("¿Seguro que deseas realizar la operación? Y/n");
        do {
            respuesta = sc.nextLine();
            switch (respuesta.toLowerCase()) {
                case "":
                case "y":
                case "yes":
                    estado = false;
                    break;
                case "n":
                case "no":
                    rBoolean = false;
                    estado = false;
                    break;
                default:
                    System.out.println("Y/n");
            }
        } while (estado);
        return rBoolean;
    }

    // readAllToString
    public static String listProductosToString() {
        StringBuilder rString = new StringBuilder();
        for (Productos producto : ProductosDAO.readAll()) {
            rString.append(producto.toString(true)).append("\n");
        }
        return rString.toString();
    }

    // Buscar por Codigo
    public static String buscarCodigo(int codigo) {
        String rString;
        if (ProductosDAO.existe(codigo)) {
            rString = ProductosDAO.read(codigo).toString(false) + "\n";
        } else {
            rString = "No existe un producto con ese codigo" + "\n";
        }
        return rString;
    }

    // Incrementar el precio de un articulo
    public static void incrementarPrecio(int codigo) {
        if (ProductosDAO.existe(codigo)) {
            Productos producto = ProductosDAO.read(codigo);
            producto.setPrecio(producto.getPrecio() * 1.21f);
            ProductosDAO.update(producto);
            System.out.println("---Precio cambiado---");
            System.out.println(producto.toString(true));
        } else {
            System.out.println("No existe un producto con ese codigo" + "\n");
        }
    }

    // Borrar un producto por codigo
    public static void borrarPorCodigo(int codigo) {
        if (ProductosDAO.existe(codigo)) {
            System.out.println(buscarCodigo(codigo));
            if (pedirConfirmacion()) {
                ProductosDAO.delete(codigo);
                System.out.println("Operación realizada");
            } else {
                System.out.println("Operación cancelada...");
            }
        } else {
            System.out.println("No existe un producto con ese codigo" + "\n");
        }
    }

    // Calcular el precio medio
    public static float calcularPrecioMedio() {
        ArrayList<Productos> listProductos = ProductosDAO.readAll();
        float rFloat = 0;
        for (Productos producto : listProductos) {
            rFloat += producto.getPrecio();
        }
        rFloat /= listProductos.size();
        return rFloat;
    }

    // Crear prducto
    public static void crearProducto(int codigo) {
        if (!ProductosDAO.existe(codigo)) {
            Productos producto = new Productos();
            producto.setCodigo(codigo);
            producto.setNombre(pedirNombre());
            producto.setDescripcion(pedirDescripcion());
            producto.setPrecio(pedirPrecio());
            System.out.println(producto.toString(false));
            if (pedirConfirmacion()) {
                ProductosDAO.create(producto);
                System.out.println("Producto creado!!");
            } else {
                System.out.println("Operacion cancelada...");
            }
        } else {
            System.out.println("El codigo ya existe");
        }
    }

    public static void modificarProducto(int codigo) {
        if (ProductosDAO.existe(codigo)) {
            System.out.println(ProductosDAO.read(codigo).toString(false));
            Productos producto = new Productos();
            producto.setCodigo(codigo);
            producto.setNombre(pedirNombre());
            producto.setDescripcion(pedirDescripcion());
            producto.setPrecio(pedirPrecio());
            System.out.println(producto.toString(false));
            if (pedirConfirmacion()) {
                ProductosDAO.update(producto);
                System.out.println("Producto modificado");
            } else {
                System.out.println("Operacion cancelada...");
            }
        } else {
            System.out.println("El producto no existe");
        }
    }

    public static void baseDatosToFichero() {
        FileWriter fwListProductos;
        try {
            fwListProductos = new FileWriter("txt/cinco/ListProductos.txt");
            fwListProductos.write(listProductosToString());
            fwListProductos.close();
            System.out.println("Hecho!!");
        } catch (Exception e) {
            System.out.println("Error Fichero: " + e.getMessage());
        }
    }
} // class end
