package Stack.controllers;

import Stack.models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Una clase que representa al controlador del foro.
 */
public class StackController {
    private Stack stack;
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();

    /**
     * Crea el controllador del foro
     * @param stack Stack que sera modificado.
     * @param etiquetas ArrayList de etiquetas creadas.
     */
    public StackController(Stack stack, ArrayList<Etiqueta> etiquetas){
        this.stack = stack;
        this.etiquetas = etiquetas;
    }

    /**
     * Obtener el nombre del usuario activo.
     * @return String con el nombre del usuario activo.
     */
    public String usuarioActivoStr(){
        return stack.getUsuarioActivo().get(0).getNombre();
    }

    /**
     * Obtener el puntaje del usuario activo.
     * @return Entero con la cantidad de puntaje de usuario activo
     */
    public int puntosActivoStr(){ return stack.getUsuarioActivo().get(0).getPuntos(); }

    /**
     * Crea una etiqueta si es que esta no existe previamente.
     * @param tagName String con el nombre de la etiqueta.
     * @param descripcion String con la descripcion de la etiqueta.
     * @return Entero, si es 1 se crea la etiqueta, 0 si la etiqueta existe previamente.
     */
    public int crearEtiqueta(String tagName, String descripcion){
        boolean verificador = false;

        for(int i=0; i< etiquetas.size(); i++){
            if (tagName.equalsIgnoreCase(etiquetas.get(i).getNombre())) {
                verificador = true;
                break;
            }
        }

        if(!verificador){
            Etiqueta newEtiqueta = new Etiqueta(tagName, descripcion);
            etiquetas.add(newEtiqueta);
            return 1;
        }

        return 0;
    }

    /**
     * Verifica si una etiqueta existe o no a la hora de hacer una pregunta.
     * @param tagName Nombre de la etiqueta.
     * @return Entero, 1 si la etiqueta ya existe, 0 si no existe.
     */
    public int existeEtiqueta(String tagName){
        boolean verificador = false;
        for(int i=0; i< etiquetas.size(); i++){
            if (tagName.equalsIgnoreCase(etiquetas.get(i).getNombre())) {
                verificador = true;
                break;
            }
        }
        if (verificador){
            return 1;
        }
        else return 0;
    }

    /**
     * Transformar el String de los nombres de las etiquetas a un ArrayList con los nombres de las etiquetas.
     * @param strEtiquetas String con los nombres de las etiquetas separados por coma.
     * @return ArrayList con los nombres de las etiquetas.
     */
    public ArrayList<String> strTagsToArray ( String strEtiquetas){
        ArrayList<String> arrayEtiquetas = new ArrayList<>(Arrays.asList(strEtiquetas.split(",")));
        return arrayEtiquetas;
    }

    /**
     * Registra a un usuario en el foro si es posible.
     * @param usuario String con el nombre de usuario.
     * @param password String con la contrasenia del usuario.
     * @return Entero, 1 si se registro al usuario, 0 si el usuario ya existia previamente.
     */
    public int registrarUsuario(String usuario, String password){
        boolean verificador = false;

        for(int i=0; i< stack.getUsuarios().size(); i++){
            if (usuario.equalsIgnoreCase(stack.getUsuarios().get(i).getNombre())) {
                verificador = true;
                break;
            }
        }

        if(!verificador){
            Usuario newUser = new Usuario(usuario, password);
            stack.setUsuarios(newUser);
            return 1;
        }

        return 0;
    }

    /**
     * Inicia sesion de un usuario si es posible.
     * @param usuario String con el nombre de usuario.
     * @param password String con la contrasenia del usuario.
     * @return Enter, 1 si se inicio sesion, 0 si el usuario o contrasenia no coinciden con un usuario.
     */
    public int loginUsuario(String usuario, String password){
        boolean verificador = false;
        int puntosUsuario = 0;

        for(int i=0; i< stack.getUsuarios().size(); i++){
            if (usuario.equals(stack.getUsuarios().get(i).getNombre()) && password.equals(stack.getUsuarios().get(i).getPassword()) ) {
                puntosUsuario = stack.getUsuarios().get(i).getPuntos();
                verificador = true;
                break;
            }
        }

        if(verificador){

            UsuarioActivo activo = new UsuarioActivo(usuario, password, puntosUsuario);
            stack.setUsuarioActivo(activo);
            return 1;
        }
        return 0;
    }

    /**
     * Elimina al usuario con la sesion activa.
     */
    public void logoutUsuario(){
        stack.getUsuarioActivo().remove(0);

    }

    /**
     * Crea y agrega una pregunta en el foro si es posible.
     * @param titulo String con el titulo de la pregunta.
     * @param contenido String con el contenido de la pregunta.
     * @param tags String con las etiquetas de la pregunta separados por coma.
     * @return Entero, 1 si la pregunta se creo y agrego al foro, 0 si una de las etiquetas no existe.
     */
    public int ask(String titulo, String contenido, String tags){
        ArrayList<String> etiquetas = strTagsToArray(tags);

        int id = stack.getPreguntas().size() + 1;
        Fecha fecha = new Fecha();
        String autor = stack.getUsuarioActivo().get(0).getNombre();

        if(etiquetas.size()== 0 ||(etiquetas.get(0).equals("") && etiquetas.size()==1)){
            Pregunta newPregunta = new Pregunta(id,titulo,contenido,fecha,autor,etiquetas);
            stack.setPreguntas(newPregunta);
            return 1;
        }

        for(int i=0; i<etiquetas.size(); i++){
            if(existeEtiqueta(etiquetas.get(i))==0){
                return 0;
            }
        }

        Pregunta newPregunta = new Pregunta(id,titulo,contenido,fecha,autor,etiquetas);
        stack.setPreguntas(newPregunta);
        return 1;
    }

    /**
     * Crea y agrega una respuesta a una pregunta si es posible.
     * @param id String con el identificador de la pregunta que se quiere responder.
     * @param contenido String con el contenido de la respuesta.
     * @return Entero, 1 si la respuesta se agrego correctamente, 0 si la pregunta no existe.
     */
    public int answer(String id, String contenido){
        int idPregunta;
        int aux =0;

        int idRespuesta=-1;
        String autor = stack.getUsuarioActivo().get(0).getNombre();
        Fecha fecha = new Fecha();

        try{
            idPregunta = Integer.parseInt(id.trim());
        }
        catch (NumberFormatException e){
            return 0;
        }

        boolean verificador = false;
        for(int i=0; i < stack.getPreguntas().size(); i++){
            if(stack.getPreguntas().get(i).getId() == idPregunta){
                idRespuesta = stack.getPreguntas().get(i).getRespuestas().size() + 1;
                aux = i;
                verificador = true;
                break;
            }
        }

        if(verificador){
            Respuesta newRespuesta = new Respuesta(idRespuesta, autor, fecha, contenido);
            stack.getPreguntas().get(aux).setRespuestas(newRespuesta);
            return 1;
        }
        return 0;
    }

    /**
     * Agrega recompensa a una pregunta si es posible.
     * @param id String con el identificador de la pregunta a agregar recompensa.
     * @param recompensa String con la cantidad de recompensa a agregar.
     * @return Entero, 1 si se agrego la recompensa correctamente, -1 si la recompensa es mayor al
     * puntaje del usuario activo, 0 si el id es incorrecto y -2 si la pregunta ya esta resuelta.
     */
    public int reward(String id, String recompensa){
        int idPregunta;
        int canRecompensa;

        try{
            idPregunta = Integer.parseInt(id.trim());
            canRecompensa = Integer.parseInt(recompensa.trim());
        }
        catch (NumberFormatException e){
            return 0;
        }

        if((stack.getUsuarioActivo().get(0).getPuntos() < canRecompensa) || (canRecompensa<=0)){
            return -1;
        }

        boolean verificador = false;
        for(int i=0; i < stack.getPreguntas().size(); i++){
            if(stack.getPreguntas().get(i).getId() == idPregunta  && stack.getPreguntas().get(i).getEstado().get(0)==0){
                verificador = true;
                break;
            }
        }

        if(verificador){

            for (int i=0; i<stack.getUsuarios().size(); i++){
                if(stack.getUsuarios().get(i).getNombre().equals(stack.getUsuarioActivo().get(0).getNombre())){
                    stack.getUsuarios().get(i).setPuntos(-canRecompensa);
                    stack.getUsuarioActivo().get(0).setPuntos(-canRecompensa);
                }
            }

            for (int i=0;i<stack.getPreguntas().size(); i++){
                if(stack.getPreguntas().get(i).getId() == idPregunta){
                    stack.getPreguntas().get(i).setRecompensa(canRecompensa);
                }
            }
            return 1;
        }

        return -2;
    }

    /**
     * Acepta una respuesta a la pregunta elegida si es posible.
     * @param idPreg String con el identificador de la pregunta selecionada.
     * @param idRes String con el identificador de la respuesta que sera aceptada.
     * @return Entero, 1 si se acepto la respuesta, 0 si uno de los id esta incorrecto.
     */
    public int accept(String idPreg, String idRes){
        int idPregunta;
        int idRespuesta;

        try{
            idPregunta = Integer.parseInt(idPreg.trim());
            idRespuesta = Integer.parseInt(idRes.trim());
        }
        catch (NumberFormatException e){
            return 0;
        }

        boolean verificador = false;
        String autorResp = "";
        int recompensa = 0;

        for (int i=0; i<stack.getPreguntas().size(); i++){
            if(stack.getPreguntas().get(i).getId() == idPregunta && stack.getPreguntas().get(i).getAutor().equals(stack.getUsuarioActivo().get(0).getNombre())){

                for (int j=0; j<stack.getPreguntas().get(i).getRespuestas().size(); j++){
                    if(stack.getPreguntas().get(i).getRespuestas().get(j).getId() == idRespuesta && !stack.getPreguntas().get(i).getRespuestas().get(j).getAutor().equals(stack.getUsuarioActivo().get(0).getNombre())){
                        verificador = true;
                        recompensa = stack.getPreguntas().get(i).getRecompensa();
                        stack.getPreguntas().get(i).setRecompensa(-recompensa);
                        stack.getPreguntas().get(i).setEstado(1, idRespuesta);
                        autorResp = stack.getPreguntas().get(i).getRespuestas().get(j).getAutor();
                        break;
                    }
                }
                break;
            }
        }

        if(verificador){

            for (int i=0; i<stack.getUsuarios().size(); i++){
                if (stack.getUsuarios().get(i).getNombre().equals(autorResp)){
                    int totalAgregar = recompensa + 15;
                    stack.getUsuarios().get(i).setPuntos(totalAgregar);
                    break;
                }
            }

            for (int i=0; i<stack.getUsuarios().size(); i++){
                if (stack.getUsuarios().get(i).getNombre().equals(stack.getUsuarioActivo().get(0).getNombre())){
                    stack.getUsuarios().get(i).setPuntos(2);
                    stack.getUsuarioActivo().get(0).setPuntos(2);
                    break;
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * Agrega un voto positivo o negativo a la pregunta o respuesta selecionada, si es posible.
     * @param ids String con el id de la pregunta o con el id de la pregunta y respuesta separados por coma.
     * @param voto String con el tipo de voto, "+" si es positivo, "-" si es negativo (sin comillas).
     * @return Entero, 1 si se agrego el voto correctamente, 0 si la(s) id son incorrectas, -1 si el tipo de voto es incorrecto.
     */
    public int vote(String ids, String voto){
        String[] listaIDs = ids.split(",");
        List<Integer> nvaListaIDs = new ArrayList<>();

        for ( String entero : listaIDs){
            try {
                nvaListaIDs.add(Integer.valueOf(entero));
            }
            catch (NumberFormatException e){
                return 0;
            }
        }

        boolean verificador = false;
        int posPreg = 0;
        int posResp = 0;
        String autorVoto = stack.getUsuarioActivo().get(0).getNombre();
        String autorVotado = "";

        if(nvaListaIDs.size()==2){
            for(int i=0; i<stack.getPreguntas().size(); i++){
                if(!stack.getPreguntas().get(i).getAutor().equals(autorVoto) && stack.getPreguntas().get(i).getId() == nvaListaIDs.get(0)){
                    for (int j=0; j<stack.getPreguntas().get(i).getRespuestas().size(); j++){
                        if(!stack.getPreguntas().get(i).getRespuestas().get(j).getAutor().equals(autorVoto) && stack.getPreguntas().get(i).getRespuestas().get(j).getId() == nvaListaIDs.get(1)){
                            posPreg = i;
                            posResp = j;
                            autorVotado = stack.getPreguntas().get(i).getRespuestas().get(j).getAutor();
                            verificador = true;
                            break;
                        }
                    }
                }
            }

            if(verificador){
                if(voto.equals("+")){
                    for(int i=0; i<stack.getUsuarios().size(); i++){
                        if(stack.getUsuarios().get(i).getNombre().equals(autorVotado)){
                            stack.getUsuarios().get(i).setPuntos(10);
                            stack.getPreguntas().get(posPreg).getRespuestas().get(posResp).setVotosPositivos();
                            return 1;
                        }
                    }
                }

                else if(voto.equals("-")){
                    for(int i=0; i<stack.getUsuarios().size(); i++){
                        if(stack.getUsuarios().get(i).getNombre().equals(autorVotado)){
                            stack.getUsuarios().get(i).setPuntos(-2);
                        }
                    }

                    for(int i=0; i<stack.getUsuarios().size(); i++){
                        if(stack.getUsuarios().get(i).getNombre().equals(autorVoto)){
                            stack.getUsuarios().get(i).setPuntos(-1);
                            stack.getUsuarioActivo().get(0).setPuntos(-1);
                        }
                    }
                    stack.getPreguntas().get(posPreg).getRespuestas().get(posResp).setVotosNegativos();
                    return 1;
                }
                return -1;
            }
        }

        else if (nvaListaIDs.size() == 1){
            for(int i=0; i<stack.getPreguntas().size(); i++){
                if(!stack.getPreguntas().get(i).getAutor().equals(autorVoto) && stack.getPreguntas().get(i).getId() == nvaListaIDs.get(0)){
                    posPreg = i;
                    autorVotado = stack.getPreguntas().get(i).getAutor();
                    verificador = true;
                    break;
                }
            }

            if (verificador){
                if(voto.equals("+")){
                    for(int i=0; i<stack.getUsuarios().size(); i++){
                        if(stack.getUsuarios().get(i).getNombre().equals(autorVotado)){
                            stack.getUsuarios().get(i).setPuntos(10);
                            stack.getPreguntas().get(posPreg).setVotosPositivos();
                            return 1;
                        }
                    }
                }

                else if(voto.equals("-")){
                    for(int i=0; i<stack.getUsuarios().size(); i++){
                        if(stack.getUsuarios().get(i).getNombre().equals(autorVotado)){
                            stack.getUsuarios().get(i).setPuntos(-2);
                        }
                    }
                    for(int i=0; i<stack.getUsuarios().size(); i++){
                        if(stack.getUsuarios().get(i).getNombre().equals(autorVoto)){
                            stack.getUsuarios().get(i).setPuntos(-1);
                            stack.getUsuarioActivo().get(0).setPuntos(-1);
                        }
                    }
                    stack.getPreguntas().get(posPreg).setVotosNegativos();
                    return 1;
                }
                return -1;
            }
        }

        return 0;
    }
    
    /**
     * Obtener la lista de preguntas del Stack.
     * @return ArrayList con las preguntas del Stack.
     */
    public ArrayList<Pregunta> getPreguntas(){
    	return stack.getPreguntas();
    }
    
    /**
     * Obtener al usario activo del Stack.
     * @return ArrayList con los datos del usuario activo.
     */
    public ArrayList<UsuarioActivo> getActivo(){
    	return stack.getUsuarioActivo();
    }

}
