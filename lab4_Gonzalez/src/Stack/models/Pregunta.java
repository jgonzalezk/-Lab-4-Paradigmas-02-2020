package Stack.models;

import java.util.ArrayList;

/**
 * Una clase para representar una pregunta dentro de un foro.
 * Cada pregunta esta conformada por un id, respuestas, etiquetas,
 * titulo, contenido, fecha, autor, estado, recompensa y votos positivos
 * y negativos.
 */
public class Pregunta {

    private int id;
    private ArrayList<Respuesta> respuestas = new ArrayList<>();
    private ArrayList<String> etiquetas = new ArrayList<>();
    private String titulo;
    private String contenido;
    private Fecha fecha;
    private String autor;
    private ArrayList<Integer> estado = new ArrayList<>(2); //Primer elemento 0 o 1, segundo elemento id.
    private int recompensa;
    private int votosPositivos;
    private int votosNegativos;

    /**
     * Crea una pregunta con los datos necesarios para esta.
     * @param id Identificador de la pregunta.
     * @param titulo Titulo de la pregunta.
     * @param contenido Contenido de la pregunta.
     * @param fecha Fecha en la cual se creo la pregunta.
     * @param autor Autor que creo la pegunta.
     * @param etiquetas Etiquetas de la pregunta.
     */
    public Pregunta(int id, String titulo, String contenido, Fecha fecha, String autor, ArrayList<String> etiquetas) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.estado.add(0);
        this.estado.add(0);
        this.etiquetas = etiquetas;
        this.recompensa = 0;
        this.votosPositivos = 0;
        this.votosNegativos = 0;
    }

    /**
     * Obtener el id de la pregunta.
     * @return Numero entero del id de la pregunta.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtener las respuestas de la pregunta.
     * @return ArrayList con las respuestas de la pregunta.
     */
    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * Obtener las etiquetas de la pregunta.
     * @return ArrayList con las etiquetas de la pregunta.
     */
    public ArrayList<String> getEtiquetas() {
        return etiquetas;
    }

    /**
     * Obtener el titulo de la pregunta.
     * @return String con el titulo de la pregunta.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtener el contenido de la pregunta.
     * @return String con el contenido de la pregunta.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Obtener la fecha de la pregunta.
     * @return Fecha con la fecha de creacion de la pregunta.
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * Obtener el autor de la pregunta.
     * @return String con el autor de la pregunta.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtener el estado de la pregunta.
     * @return ArrayList con el estado de la pregunta.
     */
    public ArrayList<Integer> getEstado() {
        return estado;
    }

    /**
     * Obtener la recompensa de la pregunta.
     * @return Entero con la recompensa de la pregunta.
     */
    public int getRecompensa() {
        return recompensa;
    }

    /**
     * Obtener la cantidad de votos positivos de la pregunta.
     * @return Entero con la cantidad de votos positivos de la pregunta.
     */
    public int getVotosPositivos() {
        return votosPositivos;
    }

    /**
     * Obtener la cantidad de votos negativos de la pregunta.
     * @return Entero con la cantidad de votos negativos de la pregunta.
     */
    public int getVotosNegativos() {
        return votosNegativos;
    }


    /**
     * Definir el estado de la pregunta.
     * @param estado 0 si la pregunta no esta resuelta, 1 si la pregunta esta resuelta.
     * @param idRespuesta 0 si no esta resuelta la pregunta, id de la respuesta aceptada si esta resuelta.
     */
    public void setEstado(int estado, int idRespuesta) {
        this.estado.set(0, estado);
        this.estado.set(1, idRespuesta);
    }

    /**
     * Agregar recompensa a la pregunta.
     * @param recompensa La cantidad de recompensa a agregar.
     */
    public void setRecompensa(int recompensa) {
        this.recompensa = this.recompensa + recompensa;
    }

    /**
     * Agregar un voto positivo a la pregunta..
     */
    public void setVotosPositivos() {
        this.votosPositivos = this.votosPositivos + 1;
    }

    /**
     * Agregar un voto negativo a la pregunta.
     */
    public void setVotosNegativos() {
        this.votosNegativos = this.votosNegativos + 1;
    }

    /**
     * Agregar una respuesta al ArrayList de respuestas.
     * @param respuesta Respuesta a agregar a la pregunta.
     */
    public void setRespuestas(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }

    /**
     * Transformar el ArrayList de las etiquetas a String.
     * @return String con las etiquetas de la pregunta.
     */
    public String tagsStr(){
        String tags = "";
        for (int i=0; i<etiquetas.size(); i++){
            tags = tags + etiquetas.get(i)+"-";
        }
        return tags;
    }
}
