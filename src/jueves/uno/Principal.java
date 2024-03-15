package jueves.uno;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static String pedirNombre() {
        System.out.println("Introduce el nombre deseado");
        return sc.nextLine();
    }

    public static short pedirCodAlumno() {
        System.out.println("Introduce el codigo deseado");
        return Short.parseShort(sc.nextLine());
    }

    public static byte pedirCodAsignatura() {
        System.out.println("Introduce el codigo deseado");
        return Byte.parseByte(sc.nextLine());
    }

    public static short pedirEdad() {
        System.out.println("Introduce la edad deseada");
        return Short.parseShort(sc.nextLine());
    }

    public static byte pedirHoraSemanales() {
        System.out.println("Introduce las horas semanales deseadas");
        return Byte.parseByte(sc.nextLine());
    }

    public static void nuevoAlumnoFichero() {
        File frCodigo;
        Scanner fsCodigo;
        Alumno alumno = new Alumno();
        Asignatura asignatura = new Asignatura();

        try {
            frCodigo = new File("txt/tres/codigo.txt");
            fsCodigo = new Scanner(frCodigo);

            alumno.setCodAlumno(fsCodigo.nextShort());
            if (!alumno.existe(alumno.getCodAlumno())) {
                alumno.setNombre(fsCodigo.next());
                alumno.setEdad(fsCodigo.nextShort());
                asignatura.setCodAsignatura(fsCodigo.nextByte());
                asignatura.read();
                alumno.setAsignatura(asignatura);
                alumno.create();
                fsCodigo.close();
            } else {
                System.out.println("El alumno ya existe");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void alumnosToFichero() {
        Alumno alumno = new Alumno();
        ResultSet rs = alumno.sqlQuery("SELECT * FROM alumno");

        FileWriter fwAlumnos;
        try {
            fwAlumnos = new FileWriter("txt/tres/listAlumnos.txt");
            while (rs.next()) {
                alumno.setCodAlumno(rs.getShort("codAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setEdad(rs.getShort("edad"));
                alumno.getAsignatura().setCodAsignatura(rs.getByte("asignatura"));
                fwAlumnos.write(alumno.toString() + "\n");
            }
            fwAlumnos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void test2() {
        Alumno alumno = new Alumno();
        alumno.setCodAlumno(pedirCodAlumno());
        if (alumno.existe(alumno.getCodAlumno())) {
            alumno.read();
            alumno.setEdad((short) (alumno.getEdad() + 1));
            alumno.update();
        } else {
            alumnosToFichero();
        }
    }

    public static void main(String[] args) {
        // Others
        // Variables
        byte uIndice = -1;

        // Programa
        do {
            System.out.println("**** Indice *****");
            try {
                uIndice = Byte.parseByte(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Solo se permiten numeros enteros");
            }

            switch (uIndice) {
                case 0 -> System.out.println("Cerrando el programa...");
                case 1 -> nuevoAlumnoFichero();
                case 2 -> test2();
                case 3 -> System.out.println("Test3");
                default -> System.out.println("Indice Incorrecto");
            } // switch end
        } while (uIndice != 0);

        sc.close();
    } // main end
} // class end
