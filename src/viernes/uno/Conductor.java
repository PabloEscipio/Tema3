package viernes.uno;

public class Conductor {
    // Atributos
    protected int idConductor;
    protected String nombre;
    protected String nif;
    protected String telefeno;
    protected short edad;

    // Constructor
    public Conductor() {
        setIdConductor(-1);
        setNombre("");
        setNif("");
        setTelefeno("");
        setEdad((short) -1);
    }

    // Getters & Setters
    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefeno() {
        return telefeno;
    }

    public void setTelefeno(String telefeno) {
        this.telefeno = telefeno;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    // toString
    public String toString() {
        return "--- Conductor ---" + "\nID: " + getIdConductor() + "\nNombre: " + getNombre() + "\nNIF: " + getNif() + "\nTeléfono: " + getTelefeno() + "\nEdad: " + getEdad();
    }
    // Metodos
}
