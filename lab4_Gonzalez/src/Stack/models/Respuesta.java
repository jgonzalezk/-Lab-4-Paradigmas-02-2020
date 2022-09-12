package Stack.models;

/**
 * Una clase para representar una respuesta a una pregunta dentro de un foro.
 * La respuesta posee un identificador, el autor, fecha de creacion,
 * contenido de la respuesta y votos positivos y negativos.
 */
public class Respuesta {
    private int id;
    private String autor;
    private Fecha fecha;
    private String contenido;
    private int votosPositivos;
    private int votosNegativos;

    /**
     * Crea una respuesta.
     * @param id Identificador de la respuesta.
     * @param autor Autor de la respuesta.
     * @param fecha Fecha de creacion de la respuesta.
     * @param contenido Contenido de la respuesta.
     */
    public Respuesta (int id, String autor,Fecha fecha, String contenido) {
        this.id = id;
        this.autor = autor;
        this.fecha = fecha;
        this.contenido = contenido;
        this.votosPositivos = 0;
        this.votosNegativos = 0;
    }

    /**
     * Obtener el identificador de la respuesta.
     * @return Entero con el identificador de la respuesta.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtener el autor de la respuesta.
     * @return String con el autor de la respuesta.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtener la fecha de creacion de la respuesta.
     * @return Fecha con la fecha de creacion de la respuesta.
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * Obtener el contenido de la respuesta.
     * @return String con el contenido de la respuesta.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Obtener la cantidad de votos positivos de la respuesta.
     * @return Entero con la cantidaad de votos positivos de la respuesta.
     */
    public int getVotosPositivos() {
        return votosPositivos;
    }

    /**
     * Obtener la cantidad de votos negativos de la respuesta.
     * @return Entero con la cantidaad de votos negativos de la respuesta.
     */
    public int getVotosNegativos() {
        return votosNegativos;
    }

    /**
     * Agregar un voto positivo a la respuesta.
     */
    public void setVotosPositivos() {
        this.votosPositivos = this.votosPositivos + 1;
    }

    /**
     * Agregar un voto negativo a la respuesta.
     */
    public void setVotosNegativos() {
        this.votosNegativos = this.votosPositivos + 1;
    }
}
