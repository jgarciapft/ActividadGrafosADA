import java.io.IOException;
import java.io.InputStream;

/**
 * Clase que implementa una serie de operaciones de lectura del flujo est�ndar
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class Lector {

    /**
     * Flujo de entrada est�ndar
     */
    private InputStream entradaEstandar;

    /**
     * Constructor por defecto de la clase. Inicializa el flujo de entrada est�ndar
     */
    public Lector() {
        entradaEstandar = System.in;
    }

    /**
     * @return N�mero le�do del flujo de entrada est�ndar
     */
    public int leerNumero() {
        return Integer.parseInt(leerLinea());
    }

    /**
     * @return L�nea le�da del flujo de entrada est�ndar
     */
    public String leerLinea() {
        int sigByte = -1;                                   // Cada car�cter de una l�nea del flujo
        StringBuilder sb = new StringBuilder();             // Para generar la cadena de l�nea

        do {                                                // Lee una l�nea (Hasta que encuentra el caracter '\n')
            try {
                sigByte = entradaEstandar.read();
            } catch (IOException e) {                       // Manejo de excepci�n de I/O
                e.printStackTrace();
            }
            sb.append((char) sigByte);
        } while (sigByte != '\n');

        return sb.toString().trim();
    }

}