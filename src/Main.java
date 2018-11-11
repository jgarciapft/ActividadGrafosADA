import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase principal que contiene el punto de entrada al programa.
 * Encargada de manejar el flujo de ejecuci�n del programa
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class Main {

    /**
     * Calcula la matriz de cierre transitivo del grafo de entrada aplicando el algoritmo de Floyd
     *
     * @param grafoEntrada Grafo de entrada
     * @param P            Matriz de actualizaciones sobre la matriz de cierre transitivo
     * @return Matriz de cierre transitivo
     */
    private static float[][] calcularCierreTransitivo(GrafoNDVIndexCad grafoEntrada, int[][] P) {
        return new float[0][];
    }

    /**
     * Calcula la secuencia de v�rtioes que conforman el cam�no m�nimo entre dos v�rtices
     * aplicando el algoritmo de recuperaci�n de caminos de Floyd
     *
     * @param mCierreT Matriz de cierre transitivo que ocntiene los caminos m�nimos
     * @param P        Matriz de actualizaciones de la matriz de cierre transitivo
     * @param cInicial Nombre de la ciudad de partida
     * @param cFinal   Nombre de la ciudad de destino
     * @return Cadena con el recorrido m�s corto y su longitud
     */
    private static String calcularMinimoCamino(float[][] mCierreT, int[][] P, String cInicial, String cFinal) {
        // TODO - implement Main.calcularMinimoCamino
        return null;
    }

    /**
     * Calcula el �rbol de expansi�n m�nimo del grafo de entrada mediante el algoritmo de Prim
     *
     * @param grafoEntrada Grafo del que calcular su �rbol de expasi�n m�nimo
     * @param grafoRes     �rbol de expansi�n m�nimo del grafo de entrada
     * @return Suma de los valores de las etiquetas del �rbol de expasi�n m�nimo
     */
    private static float calcularExpansionMinima(GrafoNDVIndexCad grafoEntrada,
                                                 GrafoNDVIndexCad grafoRes) {
        float camino;                                           // Etiqueta m�nima entre dos v�rtices
        float sumaEtiquetas = 0;                                // Suma total del valor de las etiquetas
        ArrayList<String> cjtoVertices = new ArrayList<>();     // Cjto de v�rtices del grafo resultado
        String[] verticesEntrada = grafoEntrada.verticesCadena();// Cjo de v�rtices del grafo de entrada
        String[] adyacentes;                                    //Adyacentes a cada v�rtice seleccionado
        String u;                                               // V�rtice actual
        String v;                                               // V�rtice adyacente

        cjtoVertices.add(verticesEntrada[0]);                   // Empieza por el primer v�rtice
        Iterator<String> it = cjtoVertices.iterator();
        while (cjtoVertices.size() < verticesEntrada.length) {
            u = it.next();                                      // Actualiza el v�rtice actual
            adyacentes = grafoEntrada.adyacentes(u);            // Obtiene sus adyacentes

            String minimo = null;                               // V�rtice final que hace el arco m�nimo
            for (String adyacente : adyacentes) {               // Busca el camino m�nimo con el v�rtice actual
                v = adyacente;
                if (!cjtoVertices.contains(v)) {
                    camino = grafoEntrada.obtenerValorArco(u, v);

                    if (camino > grafoEntrada.obtenerValorArco(u, minimo))
                        minimo = v;                             // Actualiza el v�rtice m�nimo
                }
            }
            camino = grafoEntrada.obtenerValorArco(u, minimo);  // Temporal del camino m�nimo

            grafoRes.insertarArco(u, minimo, camino);           // A�ade el camino al grafo resultado
            cjtoVertices.add(minimo);                           // A�ade el v�rtice elegido a los visitados
            sumaEtiquetas += camino;                            // Actualiza el acumulador
        }

        return sumaEtiquetas;
    }

    public static void main(String[] args) {
        Lector lector = new Lector();                           // Lector del flujo de entrada est�ndar
        Escritor escritor = new Escritor();                     // Escritor del flujo de salida est�ndar

        int n;                                                  // N�mero de ciudades del mapa
        int d;                                                  // N�mero de carreteras del mapa
        int p;                                                  // N�mero de preguntas
        int[][] P;                                              // Matriz de actualizaciones sobre la mCierreT
        float[][] mCierreT;                                     // Matriz de cierre transitivo
        String[][] preguntas;                                   // Campos de las preguntas sobre caminos m�nimos
        GrafoNDVIndexCad mapaCarreteras;                        // Grafo del mapa de carreteras (v�rtices -> ciudades)
        GrafoNDVIndexCad minimasCarreteras;                     // Grafo con las carreteras m�nimas para conectar toda ciudad

        // *** LECTURA DE DATOS ***

        n = lector.leerNumero();                                // Lee el n�mero de ciudades del mapa
        mapaCarreteras = new GrafoNDVIndexCad(n);               // Inicializa el mapa con las ciudades iniciales
        for (int i = 0; i < n; i++) {                           // A�ade las ciudades al mapa
            mapaCarreteras.insertarVertice(lector.leerLinea());
        }

        d = lector.leerNumero();                                // Lee el n�mero de carreteras del mapa
        String[] datos;                                         // Campos de la l�nea: 2 ciudades y 1 distancia
        for (int i = 0; i < d; i++) {
            datos = lector.leerLinea().split(" ");        // Divide la l�nea en sus campos
            // Inserta la ciudad en el mapa de carreteras
            mapaCarreteras.insertarArco(datos[0], datos[1], Float.parseFloat(datos[2]));
        }

        // *** PROBLEMA 1 ***

        p = lector.leerNumero();                                // Lee el n�mero de preguntas acerca de caminos m�nimos
        preguntas = new String[p][];                            // Inicializa la matriz de preguntas
        P = new int[mapaCarreteras.getOrden()][];               // Inicializa la matriz P de actualizaciones
        mCierreT = calcularCierreTransitivo(mapaCarreteras, P); // Calcula la matriz de cierre transitivo del mapa
        // Datos de la l�nea: 2 ciudades
        for (int i = 0; i < p; i++) {
            preguntas[i] = lector.leerLinea().split(" "); // Divide la l�nea en sus campos
            // Calcula el cam�no m�nimo y lo escribe
            escritor.escribirLinea(calcularMinimoCamino(mCierreT, P, preguntas[i][0], preguntas[i][1]));
        }
        escritor.escribirLinea("");                             // Nueva l�nea

        // *** PROBLEMA 2 ***

        // Convierte el mapa de carreteras tal que todas las ciudades est�n conectadas y el coste de repararlas sea m�nimo
        minimasCarreteras = new GrafoNDVIndexCad(mapaCarreteras.getOrden());
        // Escribe el coste de reparar las m�nimas carreteras
        escritor.escribirLinea(String.valueOf(calcularExpansionMinima(mapaCarreteras, minimasCarreteras)));

        P = new int[minimasCarreteras.getOrden()][];               // Reinicializa la matriz P para el nuevo mapa
        mCierreT = calcularCierreTransitivo(minimasCarreteras, P); // Calcula la matriz de cierre transitivo del nuevo mapa
        // Calcula los caminos m�nimos a partir del nuevo mapa de carreteras
        for (int i = 0; i < p; i++) {
            // Calcula el cam�no m�nimo y lo escribe
            escritor.escribirLinea(calcularMinimoCamino(mCierreT, P, preguntas[i][0], preguntas[i][1]));
        }
    }

}