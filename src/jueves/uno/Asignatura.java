package jueves.uno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Asignatura {
    // Atributos
    private byte codAsignatura;
    private String nombre;
    private byte horaSemanales;

    // Constructores
    public Asignatura() {
        setCodAsignatura((byte) -1);
        setNombre("");
        setHoraSemanales((byte) -1);
    }

    // Getters & Setters
    public byte getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(byte codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getHoraSemanales() {
        return horaSemanales;
    }

    public void setHoraSemanales(byte horaSemanales) {
        this.horaSemanales = horaSemanales;
    }

    // toString
    public String toString() {
        return "--- " + getNombre() + " ---" + "\nCodigo: " + getCodAsignatura() + "\nHoras semanales: " + getHoraSemanales();
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
        sqlUpdate("INSERT INTO asignatura VALUES(" + getCodAsignatura() + ", '" + getNombre() + "', " + getHoraSemanales() + ")");
    }


    public void read() {
        ResultSet rs = sqlQuery("SELECT * FROM asignatura WHERE codAsignatura = " + getCodAsignatura());
        try {
            if (rs.next()) {
                this.codAsignatura = rs.getByte("codAsignatura");
                this.nombre = rs.getString("nombre");
                this.horaSemanales = rs.getByte("horaSemanales");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void update() {
        sqlUpdate("UPDATE asignatura SET nombre = " + getNombre() + ", horaSemanales = " + getHoraSemanales() + " WHERE codAsignatura = " + getCodAsignatura());
    }

    public void delete() {
        sqlUpdate("DELETE FROM asignatura WHERE codAsignatura =" + getCodAsignatura());
    }

    public boolean existe(int codAsignatura) {
        boolean rBoolean = false;
        ResultSet rs = sqlQuery("SELECT * FROM asignatura WHERE codAsignatura = " + codAsignatura);
        try {
            rBoolean = rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rBoolean;
    }
} // class end
