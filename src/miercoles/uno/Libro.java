package miercoles.uno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Libro {
    // Atributos
    private int ISBN;
    private String titulo;
    private String autor;
    private float precio;

    // Consructores
    /*
    public Libro(int pISBN, String pTitulo, String pAutor, float pPrecio) {
        setISBN(pISBN);
        setTitulo(pTitulo);
        setAutor(pAutor);
        setPrecio(pPrecio);
    }

     */

    public Libro() {
        this.ISBN = -1;
        this.titulo = "";
        this.autor = "";
        this.precio = -1;
    }

    // Getters & Setters
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        if (ISBN < 0) {
            this.ISBN = -1;
        } else {
            this.ISBN = ISBN;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        if (precio < 0) {
            this.precio = -1;
        } else {
            this.precio = precio;
        }
    }

    // toString
    public String toSting() {
        return "ISBN: " + getISBN() + "\nTitulo: " + getTitulo() + "\nAutor: " + getAutor() + "\nPrecio: " + getPrecio() + "\nPrecio en 2025: " + calcularPrecio2025();
    }

    // Metodos
    public float calcularPrecio2025() {
        float rFloat;
        if (getPrecio() < 30) {
            rFloat = getPrecio() * 1.2f;
        } else {
            rFloat = getPrecio() * 1.15f;
        }
        return rFloat;
    }

    public void create() {
        sqlUpdate("INSERT INTO libro VALUES (" + getISBN() + ", '" + getTitulo() + "', '" + getAutor() + "', " + getPrecio() + ")");
    }

    public void read() {
        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/biblio990";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn;
        PreparedStatement ps;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
            ps = cn.prepareStatement("SELECT * FROM libro WHERE isbn = " + getISBN());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.ISBN = rs.getInt("isbn");
                this.titulo = rs.getString("titulo");
                this.autor = rs.getString("autor");
                this.precio = rs.getFloat("precio");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void update() {
        sqlUpdate("UPDATE libro SET titulo = \"" + getTitulo() + "\", autor = \"" + getAutor() + "\", precio =" + getPrecio() + " WHERE isbn = " + getISBN());
    }

    public void delete() {
        sqlUpdate("DELETE FROM libro WHERE isbn = " + getISBN());
    }

    public void sqlUpdate(String pSQL) {
        String user = "root";
        String password = "Tarde2022";
        String url = "jdbc:mysql://localhost:3306/biblio990";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection cn;
        PreparedStatement ps;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
            ps = cn.prepareStatement(pSQL);
            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
} // class end
