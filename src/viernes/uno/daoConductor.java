package viernes.uno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class daoConductor extends Conductor {
    // Conexion
    private Connection cn() {
        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/taxi990";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
        }
        return cn;
    }

    // CRUD
    public void create() {
        PreparedStatement ps;
        Connection cn = cn();
        try {
            ps = cn.prepareStatement("INSERT INTO conductor VALUES (?,?,?,?,?);");
            ps.setInt(1, getIdConductor());
            ps.setString(2, getNombre());
            ps.setString(3, getNif());
            ps.setString(4, getTelefeno());
            ps.setInt(5, getEdad());
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error ps: " + e.getLocalizedMessage());
        }
    }

    public void read() {
        PreparedStatement ps;
        Connection cn = cn();
        ResultSet rs;
        try {
            ps = cn.prepareStatement("SELECT * FROM conductor WHERE idConductor = ?;");
            ps.setInt(1, getIdConductor());
            rs = ps.executeQuery();
            rs.next();
            setNombre(rs.getString("nombre"));
            setNif(rs.getString("nif"));
            setTelefeno(rs.getString("telefono"));
            setEdad(rs.getShort("edad"));
            cn.close();
        } catch (Exception e) {
            System.out.println("Error ps: " + e.getLocalizedMessage());
        }
    }

    public void update() {
        PreparedStatement ps;
        Connection cn = cn();
        try {
            ps = cn.prepareStatement("UPDATE conductor SET nombre = ?, nif = ?, telefono = ?, edad = ? WHERE idConductor = ?;");
            ps.setString(1, getNombre());
            ps.setString(2, getNif());
            ps.setString(3, getTelefeno());
            ps.setInt(4, getEdad());
            ps.setInt(5, getIdConductor());
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error ps: " + e.getLocalizedMessage());
        }
    }

    public void delete() {
        PreparedStatement ps;
        Connection cn = cn();
        try {
            ps = cn.prepareStatement("DELETE FROM conductor WHERE idConductor = ?;");
            ps.setInt(1, getIdConductor());
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error ps: " + e.getLocalizedMessage());
        }
    }

    // Metodos
    public boolean existe() {
        PreparedStatement ps;
        Connection cn = cn();
        ResultSet rs;
        boolean rBoolean = false;

        try {
            ps = cn.prepareStatement("SELECT * FROM conductor WHERE idConductor = ?");
            ps.setInt(1, getIdConductor());
            rs = ps.executeQuery();
            if (rs.next()) {
                rBoolean = true;
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error boolean: " + e.getLocalizedMessage());
        }
        return rBoolean;
    }
} // class end
