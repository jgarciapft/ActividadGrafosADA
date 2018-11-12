import java.util.ArrayList;

/**
 * Clase que modela un grafo no dirigido valuado (con etiquetas positivas y que toman valores reales no negativos)
 * con indexaci�n de cadenas en lugar de indexacion de enteros
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class GrafoNDVIndexCad extends GrafoNDV {

    // Colecci�n de cadenas. Permite asociar un �ndice num�rico a cada cadena
    private ArrayList<String> diccionarioCadenas;

    /**
     * Constructor parametrizado de la clase. Inicializa un grafo no dirigido y valuado con idexado por cadenas
     * de un tama�o inicial dado
     *
     * @param nVerticesInic N�mero de v�rtices iniciales
     */
    public GrafoNDVIndexCad(int nVerticesInic) {
        super(nVerticesInic);
        diccionarioCadenas = new ArrayList<>();
    }

    /**
     * A�ade el nombre de una ciudad a la lista de ciudades que se convertir�n en v�rtices del grafo
     *
     * @param cadena Cadena con la que indexar un v�rtice
     */
    public void insertarVertice(String cadena) {
        diccionarioCadenas.add(cadena);
    }

    /**
     * Inserta un arco etiquetado
     *
     * @param vInicio V�rtice inicial
     * @param vFin    Vertice final
     * @param valor   Etiqueta del arco
     */
    public void insertarArco(String vInicio, String vFin, float valor) {
        // Obtiene el �ndice que ocupa cada String en el diccionario, y lo inserta en el grafo
        insertarArco(indiceEquivalente(vInicio), indiceEquivalente(vFin), valor);
    }

    /**
     * Devuelve el valor del arco formado entre dos v�rtices pasados por par�metro (Strings).
     *
     * @param vInicio V�rtice inicial
     * @param vFin    V�rtice final
     * @return Valor de la etiqueta del arco que une dos v�rtices
     */
    public float obtenerValorArco(String vInicio, String vFin) {
        // Obtiene el �ndice que ocupa cada String en el diccionario y lo busca en el grafo
        return obtenerArco(indiceEquivalente(vInicio), indiceEquivalente(vFin));
    }

    /**
     * Devuelve el conjunto de adyacentes al v�rtice dado por par�metro.
     *
     * @param vertice V�rtice del que obtener sus adyacentes
     * @return V�rtices adyacentes al v�rtice indicado
     */
    public String[] adyacentes(String vertice) {
        int[] adyacentesNumericos = adyacentes(indiceEquivalente(vertice));             // Obtiene los v�rtices
        String[] verticesString = new String[adyacentesNumericos.length];               // Crea un vector de igual

        for (int i = 0; i < adyacentesNumericos.length; i++)                            // Convierte cada �ndice a cadena
            verticesString[i] = cadenaEquivalente(adyacentesNumericos[i]);

        return verticesString;
    }

    /**
     * Devuelve el conjunto de v�rtices (Strings) que forman el grafo.
     *
     * @return Devuelve el conjunto de v�rtices del grafo
     */
    public String[] verticesCadena() {
        int[] verticesNumericos = vertices();                                           // Obtiene los v�rtices (n�mericos)
        String[] verticesString = new String[verticesNumericos.length];                 // Crea un vector de igual tamano

        for (int i = 0; i < verticesNumericos.length; i++)                              // Convierte cada �ndice a String
            verticesString[i] = cadenaEquivalente(verticesNumericos[i]);

        return verticesString;
    }

    /**
     * Devuelve la cadena asociada al �ndice pasado por par�metro.
     *
     * @param indice �ndice asociado a un v�rtice del grafo y una ciudad
     * @return Cadena equivalente al �ndice indicado
     */
    public String cadenaEquivalente(int indice) {
        return diccionarioCadenas.get(indice);
    }

    /**
     * Devuelve el �ndice asociado a la cadena pasada por par�metro.
     *
     * @param cadena Cadena mapeada a un v�rtice del grafo
     * @return El �ndice num�rico mapeado a una cadena
     */
    public int indiceEquivalente(String cadena) {
        return diccionarioCadenas.indexOf(cadena);
    }

    /**
     * @return Diccionario de cadenas mapeadas
     */
    public ArrayList<String> getDiccionarioCadenas() {
        return diccionarioCadenas;
    }

    /**
     * Actualiza el diccionario de cadenas con un nuevo diccionario
     *
     * @param dic Nuevo diccionario de cadenas
     */
    public void setDiccionarioCadenas(ArrayList<String> dic) {
        diccionarioCadenas = dic;
    }

}