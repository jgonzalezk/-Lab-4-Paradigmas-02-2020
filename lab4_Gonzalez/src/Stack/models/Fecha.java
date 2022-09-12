package Stack.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Una clase para representar la fecha
 * en formato DD/MM/YYYY.
 */
public class Fecha {
    private String fecha;

    /**
     * Crea la fecha actual con el formato DD/MM/YYYY.
     */
    public Fecha(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();
        this.fecha = df.format(fecha);
    }

    /**
     * Obtener la fecha.
     * @return Se retorna la fecha como tipo String
     */
    public String getFecha() {
        return fecha;
    }
}
