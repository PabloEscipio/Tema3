package martes.uno;

public class Libros {
    // Atributos
    private int codigo;
    private String titulo;
    private short paginas;
    private float precio;
    private int codAutor;

    // Constructor
    public Libros(int codigo, String titulo, short paginas, float precio, int codAutor){
        setCodigo(codigo);
        setTitulo(titulo);
        setPaginas(paginas);
        setPrecio(precio);
        setCodigo(codAutor);
    }

    public Libros(){
        this.codigo = -1;
        this.titulo = "";
        this.paginas = -1;
        this.precio = -1;
        this.codAutor = -1;
    }

    public Libros(Libros libro){
        setCodigo(libro.getCodigo());
        setTitulo(libro.getTitulo());
        setPaginas(libro.getPaginas());
        setPrecio(libro.getPrecio());
        setCodAutor(libro.getCodAutor());
    }

    // Getters & Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public short getPaginas() {
        return paginas;
    }

    public void setPaginas(short paginas) {
        this.paginas = paginas;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCodAutor() {
        return codAutor;
    }

    public void setCodAutor(int codAutor) {
        this.codAutor = codAutor;
    }

    // toString
    public String toString(){
        return getCodigo() + ", \"" + getTitulo() + "\", " + getPaginas() + ", " + getPrecio() + ", " + getCodAutor();
    }

    // metodos
}
