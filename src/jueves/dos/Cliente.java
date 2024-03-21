package jueves.dos;

public class Cliente {
    // Atributos
    private int id;
    private String nombre;
    private String cif;
    private String ciudad;

    // Constructores
    public Cliente() {
        this.id = -1;
        this.nombre = null;
        this.cif = null;
        this.ciudad = null;
    }

    // Getters & Setters
    // TODO restringir sets
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // toString
    public String toString(boolean linea) {
        String rString;
        if (linea) {
            rString = "ID: " + getId() + "\tNombre: " + getNombre() + "\tCIF: " + getCif() + "\tCiudad: " + getCiudad();
        } else {
            rString = "--- CLIENTE ---" + "\nID: " + getId() + "\nNombre: " + getNombre() + "\nCIF: " + getCif() + "\nCiudad: " + getCiudad();
        }
        return rString;
    }

    // Metodos
}

