import java.util.ArrayList;

/**
 * Clase que modela un grafo no dirigido valuado (con etiquetas positivas y que toman valores reales no negativos)
 * con indexaci�n de cadenas en lugar de indexacion de enteros.
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class GrafoNDVIndexCad extends GrafoNDV {

    private ArrayList<String> diccionarioCadenas;       // Colecci�n de cadenas. Permite asociar un �ndice num�rico a cada cadena.

    /**
     * Constructor parametrizado de la clase. Inicializa un grafo no dirigido y valuado con idexado por cadenas
     * de un tama�o inicial dado
     *
     * @param nVerticesInic: N�mero de v�rtices iniciales
     */
    public GrafoNDVIndexCad(int nVerticesInic) {
        super(nVerticesInic);
    }

    /**
     * A�ade el nombre de una ciudad a la lista de ciudades que se convertir�n en v�rtices del grafo
     *
     * @param cadena: Cadena con la que indexar un v�rtice
     */
    public void insertarVertice(String cadena) {
        diccionarioCadenas.add(cadena);
    }

    /**
     * Inserta un arco etiquetado
     *
     * @param vInicio: V�rtice inicial
     * @param vFin:    Vertice final
     * @param valor:   Etiqueta del arco
     */
    public void insertarArco(String vInicio, String vFin, float valor) {
        // Obtenemos el �ndice que ocupa cada String en el diccionario, y lo insertamos en el grafo,
        // (clase padre).
        insertarArco(diccionarioCadenas.indexOf(vInicio), diccionarioCadenas.indexOf(vFin), valor);
    }

    /**
     * Devuelve el valor del arco formado entre dos v�rtices pasados por par�metro (Strings).
     *
     * @param vInicio: V�rtice inicial
     * @param vFin:    V�rtice final
     * @return Valor de la etiqueta del arco que une dos v�rtices
     */
    public float obtenerValorArco(String vInicio, String vFin) {
        // Obtenemos el �ndice que ocupa cada String en el diccionario y lo buscamos en el grafo,
        // (clase padre).
        return obtenerArco(diccionarioCadenas.indexOf(vInicio), diccionarioCadenas.indexOf(vFin));
    }

    /**
     * Devuelve el conjunto de adyacentes al v�rtice dado por par�metro.
     *
     * @param vertice: V�rtice del que obtener sus adyacentes
     * @return V�rtices adyacentes al v�rtice indicado
     */
    public String[] adyacentes(String vertice) {

        int[] adyacentesNumericos = adyacentes(diccionarioCadenas.indexOf(vertice));    // Obtenemos los v�rtices.
        String[] verticesString = new String[adyacentesNumericos.length];               // Creamos un vector de igual
        // tamano de Strings

        for (int i = 0; i < adyacentesNumericos.length; i++) {                         // Convertimos cada �ndice a
            verticesString[i] = diccionarioCadenas.get(adyacentesNumericos[i]);         // String.
        }

        return verticesString;
    }

    /**
     * Devuelve el conjunto de v�rtices (Strings) que forman el grafo.
     *
     * @return Devuelve el conjunto de v�rtices del grafo
     */
    public String[] verticesCadena() {

        int[] verticesNumericos = vertices();                                // Obtenemos los v�rtices (n�mericos).
        String[] verticesString = new String[verticesNumericos.length];      // Creamos un vector de igual tamano
        // de Strings.

        for (int i = 0; i < verticesNumericos.length; i++) {                   // Convertimos cada �ndice a String.
            verticesString[i] = diccionarioCadenas.get(verticesNumericos[i]);
        }

        return verticesString;
    }

    /**
     * Devuelve la cadena asociada al �ndice pasado por par�metro.
     *
     * @param indice �ndice asociado a un v�rtice del grafo y una ciudad
     * @return Cadena equivalente al �ndice indicado
     */
    private String cadenaEquivalente(int indice) {
        return diccionarioCadenas.get(indice);
    }

    /**
     * Devuelve el �ndice asociado a la cadena pasada por par�metro.
     *
     * @param cadena Cadena mapeada a un v�rtice del grafo
     * @return El �ndice num�rico mapeado a una cadena
     */
    private int indiceEquivalente(String cadena) {
        return diccionarioCadenas.indexOf(cadena);
    }

}