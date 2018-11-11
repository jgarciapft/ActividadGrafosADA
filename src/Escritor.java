import java.io.PrintStream;

/**
 * Clase que implementa una serie de operaciones de escritura en el flujo est�ndar
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class Escritor {

    /**
     * Flujo de salida est�ndar
     */
    private PrintStream salidaEstandar;

    /**
     * Constructor por defecto de la clase. Inicializa la salida est�ndar
     */
    public Escritor() {
        salidaEstandar = System.out;
    }

    /**
     * Escribe una l�nea al flujo de salida est�ndar
     *
     * @param linea L�nea a escribir
     */
    public void escribirLinea(String linea) {
        salidaEstandar.println(linea);
    }

}