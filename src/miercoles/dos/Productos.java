package miercoles.dos;

public class Productos {
    // Atributos
    protected int codigo;
    protected String nombre;
    protected String descripcion;
    protected float precio;

    // Constructores
    public Productos() {
        this.codigo = -1;
        this.nombre = "";
        this.descripcion = "";
        this.precio = -1;
    }

    // Getters & Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        try {
            if (codigo < 0) {
                throw new ExcepcionDatos();
            } else {
                this.codigo = codigo;
            }
        } catch (ExcepcionDatos e) {
            System.out.println(e);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try {
            if (nombre.length() > 50) {
                throw new ExcepcionDatos();
            } else {
                this.nombre = nombre;
            }
        } catch (ExcepcionDatos e) {
            System.out.println(e + " ¡No pueden ser mas de 50 caracteres!");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        try {
            if (nombre.length() > 150) {
                throw new ExcepcionDatos();
            } else {
                this.descripcion = descripcion;
            }
        } catch (ExcepcionDatos e) {
            System.out.println(e + " ¡No pueden ser mas de 150 caracteres!");
        }
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        try {
            if (precio < 0) {
                throw new ExcepcionDatos();
            } else {
                this.precio = precio;
            }
        } catch (ExcepcionDatos e) {
            System.out.println(e);
        }
    }

    // toString
    public String toString(boolean linea) {
        String rString;
        if (linea) {
            rString = "Codigo: " + getCodigo() + "\tNombre: " + getNombre() + "\tPrecio: " + getPrecio();
        } else {
            rString = "--- Producto ---" + "\nCodigo: " + getCodigo() + "\nNombre: " + getNombre() + "\nDescripción: " + getDescripcion() + "\nPrecio: " + getPrecio();
        }
        return rString;
    }
    // Metodos
}
