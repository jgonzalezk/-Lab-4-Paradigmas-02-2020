package Stack.models;

import java.util.ArrayList;

/**
 * Una clase para representar el foro Stackoverflow.
 * Esta conformado por un ArrayList con los usuarios registrados,
 * un ArrayList con las preguntas y respuestas, y un ArrayList
 * con el usuario con sesion iniciada.
 */
public class Stack {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private ArrayList<UsuarioActivo> usuarioActivo = new ArrayList<>();

    public Stack() {
    }

    /**
     * Obtener el ArrayList de usuarios registrados.
     * @return ArrayList de los usuarios registrados.
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Obtener el ArrayList de preguntas y respuestas.
     * @return ArrayList de las preguntas y respuestas.
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Obtener el ArrayList con el usuario activo.
     * @return ArrayList del usuario activo.
     */
    public ArrayList<UsuarioActivo> getUsuarioActivo() {
        return usuarioActivo;
    }

    /**
     * Agregar un usuario nuevo al ArrayList de registrados.
     * @param nuevoUsuario Usuario nuevo a agregar al ArrayList de usuarios.
     */
    public void setUsuarios(Usuario nuevoUsuario) {
        this.usuarios.add(nuevoUsuario);
    }

    /**
     * Agregar una pregunta nueva al ArrayList de preguntas y respuestas.
     * @param nuevaPregunta Respuesta nuea a agregar al ArrayList de preguntas y respuestas.
     */
    public void setPreguntas(Pregunta nuevaPregunta) {
        this.preguntas.add(0,nuevaPregunta);//Agregar al comienzo como si fuera un stack
    }

    /**
     * Agregar un usuario a activo.
     * @param usuarioActivo UsuarioActivo a agregar a la sesion activa.
     */
    public void setUsuarioActivo(UsuarioActivo usuarioActivo) {
        this.usuarioActivo.add(usuarioActivo);
    }
}
