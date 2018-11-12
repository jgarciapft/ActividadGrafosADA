import java.util.ArrayList;

/**
 * Clase que modela un Grafo No Dirigido Valuado con indexado de v�rtices por enteros.
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class GrafoNDV extends MatrizAdyacencia implements Grafo {

    /**
     * Constructor parametrizado de la clase. Instancia un grafo no dirigido valuado
     *
     * @param nVerticesInic N�mero de v�rtices iniciales del grafo
     */
    public GrafoNDV(int nVerticesInic) {
        super(nVerticesInic);
        crear();
    }

    @Override
    public void crear() {
    }

    @Override
    public void insertarVertice(int vertice) {
        insertarIndice(vertice);
    }

    /**
     * M�todo que inserta un arco entre dos v�rtices.
     *
     * @param vInicio V�rtice inicial del arco
     * @param vFin    V�rtice final del arco
     * @param valor   Valor de la etiqueta del arco, si debe existir
     */
    @Override
    public void insertarArco(int vInicio, int vFin, float valor) {
        modificarCelda(vInicio, vFin, valor);
        modificarCelda(vFin, vInicio, valor);                           // Se insertan de forma sim�trica
    }

    /**
     * Devuelve el valor del arco formado por ambos v�rtices.
     *
     * @param vInicio V�rtice inicial del arco
     * @param vFin    V�rtice final del arco
     * @return el valor del arco
     */
    @Override
    public float obtenerArco(int vInicio, int vFin) {
        return obtenerValorCelda(vInicio, vFin);
    }

    /**
     * M�todo que elimina un v�rtice indicado por par�metro
     *
     * @param vertice V�rtice a borrar
     */
    @Override
    public void borrarVertice(int vertice) {
        borrarIndice(vertice);
    }

    @Override
    public void borrarArco(int vInicio, int vFin) {
        modificarCelda(vInicio, vFin, MatrizAdyacencia.ELEMENTO_VACIO);
        modificarCelda(vFin, vInicio, MatrizAdyacencia.ELEMENTO_VACIO); // Se elimina de forma sim�trica
    }

    /**
     * M�todo que indica si el grafo es un grafo vac�o.
     *
     * @return true si el grafo es un grafo vac�o.
     */
    @Override
    public boolean vacio() {
        return getOrden() == 0;
    }

    /**
     * M�todo que devuelve los v�rtices que componen el grafo.
     *
     * @return un vector de enteros con los �ndices de cada v�rtice.
     */
    @Override
    public int[] vertices() {
        int[] acumulados = new int[getOrden()];                         // Vector de vertices (orden) de la matriz de adyacencia
        for (int i = 0; i < getOrden(); i++) {                          // Inicializaci�n del los v�rtices con su valor num�rico
            acumulados[i] = i;
        }

        return acumulados;
    }

    /**
     * M�todo que devuelve los arcos que componen el grafo.
     *
     * @return matriz de float con los valores de cada arco.
     */
    @Override
    public float[][] arcos() {                                          // Los arcos son el conjunto de valores de la matriz de adyacencia
        return getMatrizAdy();
    }

    /**
     * M�todo que devuelve los v�rtices adyacentes del v�rtice pasado por par�metro.
     *
     * @param vertice V�rtice del que devolver sus adyacentes
     * @return vector de enteros con los �ndices de cada v�rtice adyacente al dado.
     */
    @Override
    public int[] adyacentes(int vertice) {
        ArrayList<Integer> vAdyacentes = new ArrayList<>();             // Colecci�n auxiliar para calcular el n� de ady

        for (int i = 0; i < getOrden(); i++) {                          // Comprueba si [i] es adyacente
            if (obtenerValorCelda(vertice, i) > 0 &&                    // Vertices adyacentes
                    obtenerValorCelda(vertice, i) < MatrizAdyacencia.ELEMENTO_VACIO) {
                vAdyacentes.add(i);
            }
        }

        int[] vertices = new int[vAdyacentes.size()];                   // Vector de vertices adyacentes final

        for (int i = 0; i < vAdyacentes.size(); i++) {                  // Paso de la estructura auxiliar a la final
            vertices[i] = vAdyacentes.get(i);
        }

        return vertices;
    }

}