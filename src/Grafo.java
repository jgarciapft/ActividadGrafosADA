/**
 * Interfaz que define las operaciones fundamentales de cualquier grafo implementado mediante matriz de adyacencia.
 * <p>
 * El tipo gen�rico T representa el tipo de las celdas de la matriz de adyacencia asociada al grafo
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jose �ngel Concha Carrasco
 */
public interface Grafo<T> {

    /**
     * Inicializa la estructura soporte del grafo
     */
    void crear();

    /**
     * Inserta un v�rtice nuevo en el grafo
     *
     * @param vertice V�rtice a insertar
     */
    void insertarVertice(int vertice);

    /**
     * Inserta un arco en el grafo
     *
     * @param vInicio V�rtice inicial del arco
     * @param vFin    V�rtice final del arco
     * @param valor   Valor de la etiqueta del arco, si debe existir
     */
    void insertarArco(int vInicio, int vFin, T valor);

    /**
     * Borra un v�rtice del grafo. Puede provocar el borrado de un arco si el v�rtice a borrar tiene un grado mayor que 0
     *
     * @param vertice V�rtice a borrar
     */
    void borrarVertice(int vertice);

    /**
     * Borra el arco del grafo representado por los v�rtices de inicio y fin
     *
     * @param vInicio V�rtice inicial del camino a borrar
     * @param vFin    V�rtice final del camino a borrar
     */
    void borrarArco(int vInicio, int vFin);

    /**
     * @return Si el grafo contiene alg�n v�rtice
     */
    boolean vacio();

    /**
     * @return El conjunto de v�rtices del grafo
     */
    int[] vertices();

    /**
     * @return Matriz de adyacencia del grafo
     */
    T[][] arcos();

    /**
     * @param vertice V�rtice del que devolver sus adyacentes
     * @return Conjunto de v�rtices adyacentes al dado. Devuelve nulo si su grado es 0
     */
    int[] adyacentes(int vertice);

}