package miercoles.dos;

public class ExcepcioVoid extends Exception {
    @Override
    public String toString() {
        return "No existe nigun producto por ese codigo";
    }
}
