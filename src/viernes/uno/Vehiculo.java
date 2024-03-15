package viernes.uno;

public class Vehiculo {
    // Atributos
    protected int idVehiculo;
    protected int conductor;
    protected String marca;
    protected String modelo;
    protected String matricula;


    // Constructor
    public Vehiculo() {
        setIdVehiculo(-1);
        setConductor(-1);
        setMarca("");
        setModelo("");
        setMatricula("");
    }

    // Getters & Setters
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getConductor() {
        return conductor;
    }

    public void setConductor(int conductor) {
        this.conductor = conductor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    // toString
    public String toString() {
        return "--- Vehículo ---" + "\nID: " + getIdVehiculo() + "\nConductor: " + getConductor() + "\nMarca: " + getMarca() + "\nModelo: " + getModelo() + "\nMatricula: " + getMatricula();
    }

    // Metodos
}
