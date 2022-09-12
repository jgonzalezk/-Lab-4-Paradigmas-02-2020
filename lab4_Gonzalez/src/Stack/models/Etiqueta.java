package Stack.models;

/**
 * Una clase para representar etiquetas de un foro.
 * Cada etiqueta posee un nombre y una descripcion.
 * de esta.
 */

public class Etiqueta {
    public String nombre;
    public String descripcion;

    /**
     * Crear una etiqueta.
     * @param nombre Nombre de la etiqueta.
     * @param descripcion Descripcion de la etiqueta.
     */
    public Etiqueta(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     *Obtener el nombre de la etiqueta.
     * @return Se retorna el nombre de la etiqueta como tipo String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *Obtener la descripcion de la etiqueta.
     * @return Se retorna la descripcion de la etiqueta como tipo String.
     */
    public String getDescripcion() {
        return descripcion;
    }
}
