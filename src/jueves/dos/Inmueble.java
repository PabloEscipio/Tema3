package jueves.dos;

public class Inmueble {
    // Atrinutos
    private int id;
    private int cliente;
    private String direccion;
    private String ciudad;
    private String provincia;
    private int importe;

    // Constructores
    public Inmueble() {
        this.id = -1;
        this.cliente = -1;
        this.direccion = null;
        this.ciudad = null;
        this.provincia = null;
        this.importe = -1;
    }

    // Getters & Setters
    // TODO Restringir SETS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    // toString
    public String toString(boolean linea) {
        String rString;
        if (linea) {
            rString = "ID: " + getId() + "\tCliente: " + getCliente() + "\tDirección: " + getDireccion() + "\tImporte: " + getImporte();
        } else {
            rString = "--- INMUEBLE ---" + "\nID: " + getId() + "\nCliente: " + getCliente() + "\nDirección: " + getDireccion() + "\nCiudad: " + getCiudad() + "\nProvincia: " + getProvincia() + "\nImporte: " + getImporte();
        }
        return rString;
    }
    // Metodos
}
