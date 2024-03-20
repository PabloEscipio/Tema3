package martes.dos;

public class Aviones {
    // Atributos
    protected int id;
    protected String marca;
    protected String modelo;
    protected short capacidad;

    // Constructores
    public Aviones() {
        this.id = -1;
        this.marca = "";
        this.modelo = "";
        this.capacidad = -1;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        try {
            if (id < 0) {
                throw new ExceptionDatos();
            } else {
                this.id = id;
            }
        } catch (ExceptionDatos e) {
            System.out.println(e);
        }
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

    public short getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(short capacidad) {
        try {
            if (capacidad < 0) {
                throw new ExceptionDatos();
            } else {
                this.capacidad = capacidad;
            }
        } catch (ExceptionDatos e) {
            System.out.println(e);
        }
    }

    // toString
    public String toString(boolean linea) {
        String rString;
        if (linea) {
            rString = "ID: " + getId() + "\tMarca: " + getMarca() + "\tModelo: " + getModelo() + "\tCapacidad: " + getCapacidad();
        } else {
            rString = "--- Avion ---" + "\nID: " + getId() + "\nMarca: " + getMarca() + "\nModelo: " + getModelo() + "\nCapacidad: " + getCapacidad();
        }
        return rString;
    }
    // Metodos
}
