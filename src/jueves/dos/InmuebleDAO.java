package jueves.dos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InmuebleDAO {
    // Conexion
    private static Connection conexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inmoviliaria990", "root", "Tarde2022");
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return con;
    }

    // Existe
    public static boolean existe(int id) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM Inmueble WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rBoolean = rs.next();
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return rBoolean;
    }

    // CRUD
    public static boolean create(Inmueble inmueble) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Inmueble (id, cliente, direccion, ciudad, provincia, importe) VALUES (?, ?, ?, ?, ?, ?);");
            ps.setInt(1, inmueble.getId());
            ps.setInt(2, inmueble.getCliente());
            ps.setString(3, inmueble.getDireccion());
            ps.setString(4, inmueble.getCiudad());
            ps.setString(5, inmueble.getProvincia());
            ps.setInt(6, inmueble.getImporte());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return rBoolean;
    }

    public static Inmueble read(int id) {
        Inmueble inmueble = new Inmueble();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Inmueble WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            inmueble.setId(id);
            inmueble.setCliente(rs.getInt("cliente"));
            inmueble.setDireccion(rs.getString("direccion"));
            inmueble.setCiudad(rs.getString("ciudad"));
            inmueble.setProvincia(rs.getString("provincia"));
            inmueble.setImporte(rs.getInt("importe"));
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return inmueble;
    }

    public static boolean update(Inmueble inmueble) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Inmueble SET cliente = ?, direccion = ?, ciudad = ?, provincia = ?, importe = ? WHERE id = ?;");
            ps.setInt(1, inmueble.getCliente());
            ps.setString(2, inmueble.getDireccion());
            ps.setString(3, inmueble.getCiudad());
            ps.setString(4, inmueble.getProvincia());
            ps.setInt(5, inmueble.getImporte());
            ps.setInt(6, inmueble.getId());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return rBoolean;
    }

    public static boolean delete(int id) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Inmueble WHERE id = ?;");
            ps.setInt(1, id);
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return rBoolean;
    }

    // Otros
    public static boolean existeCliente(int id) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT cliente FROM Inmueble WHERE cliente = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rBoolean = rs.next();
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return rBoolean;
    }

    public static ArrayList<Inmueble> readAll() {
        ArrayList<Inmueble> listInmuebles = new ArrayList<>();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM Inmueble;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listInmuebles.add(InmuebleDAO.read(rs.getInt("id")));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return listInmuebles;
    }
}
