package viernes.uno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class daoVehiculo extends Vehiculo {
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
            ps = cn.prepareStatement("INSERT INTO vehiculo VALUES (?,?,?,?,?);");
            ps.setInt(1, getIdVehiculo());
            ps.setInt(2, getConductor());
            ps.setString(3, getMarca());
            ps.setString(4, getModelo());
            ps.setString(5, getMatricula());
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
            ps = cn.prepareStatement("SELECT * FROM vehiculo WHERE idVehiculo = ?");
            ps.setInt(1, getIdVehiculo());
            rs = ps.executeQuery();
            rs.next();
            setConductor(rs.getInt("conductor"));
            setMarca(rs.getString("marca"));
            setModelo(rs.getString("modelo"));
            setMatricula(rs.getString("matricula"));
            cn.close();
        } catch (Exception e) {
            System.out.println("Error ps: " + e.getLocalizedMessage() + ", " + e.getCause());
        }
    }

    public void update() {
        PreparedStatement ps;
        Connection cn = cn();
        try {
            ps = cn.prepareStatement("UPDATE vehiculo SET conductor = ?, marca = ?, modelo = ?, matricula = ? WHERE idVehiculo = ?");
            ps.setInt(1, getConductor());
            ps.setString(2, getMarca());
            ps.setString(3, getModelo());
            ps.setString(4, getMatricula());
            ps.setInt(5, getIdVehiculo());
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
            ps = cn.prepareStatement("DELETE FROM vehiculo WHERE idVehiculo = ?");
            ps.setInt(1, getIdVehiculo());
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
            ps = cn.prepareStatement("SELECT * FROM vehiculo WHERE idVehiculo = ?;");
            ps.setInt(1, getIdVehiculo());
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
