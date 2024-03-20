package martes.dos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AvionesDAO extends Aviones {
    // Conexion
    private static Connection conexion() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/aviones990";
        try {
            con = DriverManager.getConnection(url, "root", "Tarde2022");
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return con;
    }

    // CRUD
    public void create() {
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO flota VALUES(?, ?, ?, ?);");
            ps.setInt(1, getId());
            ps.setString(2, getMarca());
            ps.setString(3, getModelo());
            ps.setInt(4, getCapacidad());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("ErrorSQL: " + e.getLocalizedMessage());
        }
    }

    public void read() {
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM flota WHERE id = ?;");
            ps.setInt(1, getId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            rsRead(rs);
            con.close();
        } catch (Exception e) {
            System.out.println("ErrorSQL: " + e.getLocalizedMessage());
        }
    }

    public void update() {
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE flota SET marca = ?, modelo = ?, capacidad = ? WHERE id = ?;");
            ps.setString(1, getMarca());
            ps.setString(2, getModelo());
            ps.setInt(3, getCapacidad());
            ps.setInt(4, getId());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("ErrorSQL: " + e.getLocalizedMessage());
        }
    }

    public void delete() {
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM flota WHERE id = ?;");
            ps.setInt(1, getId());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("ErrorSQL: " + e.getLocalizedMessage());
        }
    }

    // Consultas estaticas
    public static String readAll() {
        StringBuilder rString = new StringBuilder();
        AvionesDAO avion = new AvionesDAO();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM flota;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                avion.rsRead(rs);
                rString.append(avion.toString(true)).append("\n");

            }
            con.close();
        } catch (Exception e) {
            System.out.println("ErrorSQL: " + e.getLocalizedMessage());
        }
        return rString.toString();
    }

    public static String buscarPorModelo(String modelo) {
        AvionesDAO avion = new AvionesDAO();
        StringBuilder rString = new StringBuilder();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM flota WHERE modelo = ?;");
            ps.setString(1, modelo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                avion.rsRead(rs);
                rString.append(avion.toString(true)).append("\n");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return rString.toString().toString();
    }

    // Otros

    private void rsRead(ResultSet rs) throws Exception {
        setId(rs.getInt("id"));
        setMarca(rs.getString("marca"));
        setModelo(rs.getString("modelo"));
        setCapacidad(rs.getShort("capacidad"));
    }
} // class end
