package miercoles.dos;

import java.sql.*;
import java.util.ArrayList;

public class ProductosDAO {
    // Conexion
    private static Connection conexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ferreteria990", "root", "Tarde2022");
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }

    // CRUD
    public static void create(Productos producto) {
        Connection con = conexion();
        try {
            // 1: Codigo | 2: Nombre | 3: Descripcion | 4: Precio
            PreparedStatement ps = con.prepareStatement("INSERT INTO productos VALUES(?, ?, ?, ?);");
            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setFloat(4, producto.getPrecio());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static Productos read(int codigo) {
        Connection con = conexion();
        Productos producto = new Productos();
        try {
            // 1: Codigo
            PreparedStatement ps = con.prepareStatement("SELECT * FROM productos WHERE codigo = ?;");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            producto.setCodigo(rs.getInt("codigo"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getFloat("precio"));
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return producto;
    }

    public static void update(Productos producto) {
        Connection con = conexion();
        try {
            // 1: Nombre | 2: Descripcion | 3: Precio | 4: Codigo
            PreparedStatement ps = con.prepareStatement("UPDATE productos SET nombre = ?, descripcion = ?, precio = ? WHERE codigo = ?;");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getCodigo());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void delete(int codigo) {
        Connection con = conexion();
        try {
            // 1: Codigo
            PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE codigo = ?;");
            ps.setInt(1, codigo);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Consultas
    // Otros
    public static ArrayList<Productos> readAll() {
        ArrayList<Productos> listProductos = new ArrayList<>();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT codigo FROM productos;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listProductos.add(read(rs.getInt("codigo")));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return listProductos;
    }

    public static boolean existe(int codigo) {
        Connection con = conexion();
        boolean rBoolean = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT codigo FROM productos WHERE codigo = ?;");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            rBoolean = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return rBoolean;
    }

} // class end
