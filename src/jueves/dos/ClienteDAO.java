package jueves.dos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {
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
            PreparedStatement ps = con.prepareStatement("SELECT id FROM Cliente WHERE id = ?;");
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
    public static boolean create(Cliente cliente) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Cliente (id, nombre, cif, ciudad) VALUES (?, ?, ?, ?);");
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getCif());
            ps.setString(4, cliente.getCiudad());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return rBoolean;
    }

    public static Cliente read(int id) {
        Cliente cliente = new Cliente();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Cliente WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setCif(rs.getString("cif"));
            cliente.setCiudad(rs.getString("ciudad"));
            ps.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return cliente;
    }

    public static boolean update(Cliente cliente) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Cliente SET nombre = ?, cif = ?, ciudad = ? WHERE id = ?;");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCif());
            ps.setString(3, cliente.getCiudad());
            ps.setInt(4, cliente.getId());
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM Cliente WHERE id = ?;");
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

    public static ArrayList<Cliente> readAll() {
        ArrayList<Cliente> listClientes = new ArrayList<>();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM Cliente;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listClientes.add(ClienteDAO.read(rs.getInt("id")));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR SQL: " + e.getMessage());
        }
        return listClientes;
    }
}
