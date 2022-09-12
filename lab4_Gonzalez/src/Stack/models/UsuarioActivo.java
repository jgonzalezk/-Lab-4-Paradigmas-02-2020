package Stack.models;

/**
 * Una clase para representar a un usuario con sesion iniciada de un foro.
 * Esta conformado por el nombre del usuario activo, su contrasenia
 * y el puntaje que posee.
 */
public class UsuarioActivo {
    public String nombre;
    public String password;
    public int puntos;

    /**
     * Crea un usuario con sesion iniciada.
     * @param nombre Nombre del usuario con sesion iniciada.
     * @param password Contrasenia del usuario con sesion iniciada.
     * @param puntos Puntaje que posee el usuario con sesion iniciada.
     */
    public UsuarioActivo(String nombre, String password, int puntos) {
        this.nombre = nombre;
        this.password = password;
        this.puntos = puntos;
    }

    /**
     * Obtener el nombre del usuario con la sesion iniciada.
     * @return String con el nombre del usuario con sesion iniciada.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener el puntaje del usuario con sesion iniciada.
     * @return Entero con la cantidad de puntaje que posee el usuario con sesion iniciada.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Agregar puntos al puntaje del usuario con sesion iniciada.
     * @param puntos Entero de los puntos a agregar al usuario con sesion iniciada.
     */
    public void setPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }
}
