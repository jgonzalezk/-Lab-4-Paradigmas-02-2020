package Stack.models;

/**
 * Una clase para representar a un usuario de un foro.
 * Esta conformado por el nombre del usuario, su contrasenia
 * y el puntaje que posee.
 */
public class Usuario {
    private String nombre;
    private String password;
    private int puntos;

    /**
     * Crea un nuevo usuario.
     * @param nombre Nombre del usuario.
     * @param password Contrasenia del usuario.
     */
    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.puntos = 300;
    }

    /**
     * Obtener el nombre de usuario.
     * @return String con el nombre de usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener la contrasenia del usuario.
     * @return String con la contrasenia del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtener el puntaje el usuario.
     * @return Sting con el puntaje del usuario.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Agregar puntos al puntaje del usuario.
     * @param puntos Entero de los puntos a agregar al usuario.
     */
    public void setPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }

}
