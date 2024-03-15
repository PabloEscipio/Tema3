package jueves.uno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Alumno {
    // Atributos
    private short codAlumno;
    private String nombre;
    private short edad;
    private Asignatura asignatura;

    // Constructores
    public Alumno() {
        setCodAlumno((short) -1);
        setNombre("");
        setEdad((short) -1);
        setAsignatura(new Asignatura());
    }

    // Getters & Setters
    public short getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(short codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    // toString
    public String toString() {
        return "--- " + getNombre() + " ---" + "\nCodigo: " + getCodAlumno() + "\nEdad: " + getEdad() + "\nAsignatura: " + getAsignatura().getCodAsignatura();
    }

    // Metodos
    public Connection sql() {
        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/Academia990";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cn;
    }

    public void sqlUpdate(String pSQL) {
        PreparedStatement ps;
        Connection cn = sql();
        try {
            ps = cn.prepareStatement(pSQL);
            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    } // sqlUpdate end

    public ResultSet sqlQuery(String pSQL) {
        Connection cn = sql();
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement(pSQL);
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rs;
    }

    public void create() {
        if (!existe(getCodAlumno())) {
            sqlUpdate("INSERT INTO alumno VALUES(" + getCodAlumno() + ", '" + getNombre() + "', " + getEdad() + ", " + getAsignatura().getCodAsignatura() + ")");
            System.out.println("Alumno creado");
        } else {
            System.out.println("El alumno ya existe");
        }
    }

    public void read() {
        if (existe(getCodAlumno())) {
            ResultSet rs = sqlQuery("SELECT * FROM alumno WHERE codAlumno = " + getCodAlumno());
            try {
                if (rs.next()) {
                    this.codAlumno = rs.getShort("codAlumno");
                    this.nombre = rs.getString("nombre");
                    this.edad = rs.getShort("edad");
                    getAsignatura().setCodAsignatura(rs.getByte("asignatura"));
                    getAsignatura().read();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("El alumno no existe");
        }

    }

    public void update() {
        if (existe(getCodAlumno())) {
            sqlUpdate("UPDATE alumno SET nombre = '" + getNombre() + "', edad = " + getEdad() + ", asignatura = " + getAsignatura().getCodAsignatura() + " WHERE codAlumno = " + getCodAlumno());
            System.out.println("Alumno actualizado");
        } else {
            System.out.println("El alumno no existe");
        }
    }

    public void delete() {
        if (existe(getCodAlumno())) {
            sqlUpdate("DELETE FROM alumno WHERE codAlumno = " + getCodAlumno());
            System.out.println("Alumno borrado");
        } else {
            System.out.println("El alumno no existe");
        }
    }

    public boolean existe(int codAlumno) {
        boolean rBoolean = false;
        ResultSet rs = sqlQuery("SELECT * FROM alumno WHERE codAlumno = " + codAlumno);
        try {
            rBoolean = rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rBoolean;
    }
}
