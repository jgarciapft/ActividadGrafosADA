import java.util.ArrayList;

/**
 * Clase principal que contiene el punto de entrada al programa.
 * Encargada de manejar el flujo de ejecuci�n del programa
 *
 * @author Juan Pablo Garc�a Plaza P�rez - Jos� �ngel Concha Carrasco
 */
public class Main {

    private static final int P_NULO = -1;                           // Valor nulo de la matriz P de actualiaci�n

    /**
     * Calcula la matriz de cierre transitivo del grafo de entrada aplicando el algoritmo de Floyd
     *
     * @param grafoEntrada Grafo de entrada
     * @param P            Matriz de actualizaciones sobre la matriz de cierre transitivo
     * @return Matriz de cierre transitivo
     */
    private static float[][] calcularCierreTransitivo(GrafoNDVIndexCad grafoEntrada, int[][] P) {
        int orden = grafoEntrada.getOrden();                        // Orden de la matriz de adyacencia del grafo
        float[][] mCierreT = new float[orden][orden];               // Matriz de cierre transitivo
        float caminoNuevo;                                          // Longitud del camino formado por [i,k] y [k,j]

        // Copia la matriz de adyacencia del grafo en 'mCierreT'
        for (int i = 0; i < orden; i++) {
            for (int j = 0; j < orden; j++) {
                mCierreT[i][j] = grafoEntrada.getMatrizAdy()[i][j];
            }
        }

        for (int k = 0; k < orden; k++) {
            for (int i = 0; i < orden; i++) {
                for (int j = 0; j < orden; j++) {
                    caminoNuevo = mCierreT[i][k] + mCierreT[k][j];
                    if (caminoNuevo < mCierreT[i][j]) {             // Actualiza el camino si el nuevo es menor
                        mCierreT[i][j] = caminoNuevo;
                        P[i][j] = k;                                // Actualiza la matriz P
                    }
                }
            }
        }

        return mCierreT;
    }

    /**
     * Calcula la secuencia de v�rtioes que conforman el cam�no m�nimo entre dos v�rtices
     * aplicando el algoritmo de recuperaci�n de caminos de Floyd
     *
     * @param mCierreT Matriz de cierre transitivo que ocntiene los caminos m�nimos
     * @param P        Matriz de actualizaciones de la matriz de cierre transitivo
     * @param vInicial V�rtice de partida
     * @param vFinal   V�rtice final
     * @param g        Grafo del que proviene la matriz de cierre transitivo
     */
    private static void calcularMinimoCamino(float[][] mCierreT, int[][] P, String vInicial, String vFinal,
                                             GrafoNDVIndexCad g) {
        int k = P[g.indiceEquivalente(vInicial)][g.indiceEquivalente(vFinal)];

        if (k != P_NULO) {
            calcularMinimoCamino(mCierreT, P, vInicial, g.cadenaEquivalente(k), g);
            System.out.print(g.cadenaEquivalente(k) + " ");
            calcularMinimoCamino(mCierreT, P, g.cadenaEquivalente(k), vFinal, g);
        }
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
        ArrayList<String> cjtoVertices = new ArrayList<>();         // Cjto de v�rtices del grafo resultado
        String[] verticesEntrada = grafoEntrada.verticesCadena();   // Cjto de v�rtices del grafo de entrada
        String[] adyacentes;                                        //Adyacentes a cada v�rtice seleccionado
        String uMinimo = null;                                      // V�rtice inicial que hizo el arco m�nimo
        String vMinimo = null;                                      // V�rtice final que hizo el arco m�nimo
        float caminoMinimo;                                         // Camino m�nimo en cada exploraci�n
        float sumaEtiquetas = 0;                                    // Suma total del valor de las etiquetas

        cjtoVertices.add(verticesEntrada[0]);                       // Empieza por el primer v�rtice
        while (cjtoVertices.size() < verticesEntrada.length) {
            caminoMinimo = MatrizAdyacencia.ELEMENTO_VACIO;         // Reinicializa al valor nulo el camino m�nimo

            for (String vertice : cjtoVertices) {                   // Para cada v�rtice del 'cjtoVertices', explora sus adyacentes
                adyacentes = grafoEntrada.adyacentes(vertice);      // Obtiene los adyacentes de cada v�rtice

                for (String adyacente : adyacentes) {               // Para cada adyacente del v�rtice actual
                    if (!cjtoVertices.contains(adyacente)) {        // Comprueba que el adyacente no est� en 'cjtoVertices'
                        // Actualiza los v�rtices y camino m�nimo si procede
                        if (grafoEntrada.obtenerValorArco(vertice, adyacente) < caminoMinimo) {
                            uMinimo = vertice;
                            vMinimo = adyacente;
                            caminoMinimo = grafoEntrada.obtenerValorArco(uMinimo, vMinimo);
                        }
                    }
                }
            }

            // A�ade el camino al grafo resultado
            grafoRes.insertarArco(uMinimo, vMinimo, caminoMinimo);
            cjtoVertices.add(vMinimo);                              // A�ade el v�rtice elegido a los visitados
            sumaEtiquetas += caminoMinimo;                          // Actualiza el acumulador
        }

        return sumaEtiquetas;
    }

    /**
     * Rellena una matriz con el elemento indicado
     *
     * @param matriz Matriz de enteros a inicializar
     * @param valor  Elemento con el que inicializar las celdas de la matriz
     */
    private static void inicializarMatri(int[][] matriz, int valor) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = valor;
            }
        }
    }

    public static void main(String[] args) {
        Lector lector = new Lector();                               // Lector del flujo de entrada est�ndar
        Escritor escritor = new Escritor();                         // Escritor del flujo de salida est�ndar

        int n;                                                      // N�mero de ciudades del mapa
        int d;                                                      // N�mero de carreteras del mapa
        int p;                                                      // N�mero de preguntas
        int[][] P;                                                  // Matriz de actualizaciones sobre la mCierreT
        float[][] mCierreT;                                         // Matriz de cierre transitivo
        String[][] preguntas;                                       // Campos de las preguntas sobre caminos m�nimos
        GrafoNDVIndexCad mapaCarreteras;                            // Grafo del mapa de carreteras (v�rtices -> ciudades)
        GrafoNDVIndexCad minimasCarreteras;                         // Grafo con las carreteras m�nimas para conectar toda ciudad

        // *** LECTURA DE DATOS ***

        n = lector.leerNumero();                                    // Lee el n�mero de ciudades del mapa
        mapaCarreteras = new GrafoNDVIndexCad(n);                   // Inicializa el mapa con las ciudades iniciales
        for (int i = 0; i < n; i++) {                               // A�ade las ciudades al mapa
            mapaCarreteras.insertarVertice(lector.leerLinea());
        }

        d = lector.leerNumero();                                    // Lee el n�mero de carreteras del mapa
        String[] datos;                                             // Campos de la l�nea: 2 ciudades y 1 distancia
        for (int i = 0; i < d; i++) {
            datos = lector.leerLinea().split(" ");            // Divide la l�nea en sus campos
            // Inserta la ciudad en el mapa de carreteras
            mapaCarreteras.insertarArco(datos[0], datos[1], Float.parseFloat(datos[2]));
        }


        // *** PROBLEMA 1 ***


        p = lector.leerNumero();                                    // Lee el n�mero de preguntas acerca de caminos m�nimos
        preguntas = new String[p][];                                // Inicializa la matriz de preguntas
        // Inicializa la matriz P de actualizaciones con un valor nulo
        P = new int[mapaCarreteras.getOrden()][mapaCarreteras.getOrden()];
        inicializarMatri(P, Main.P_NULO);

        mCierreT = calcularCierreTransitivo(mapaCarreteras, P);     // Calcula la matriz de cierre transitivo del mapa
        // Datos de la l�nea: 2 ciudades
        for (int i = 0; i < p; i++) {
            preguntas[i] = lector.leerLinea().split(" ");     // Divide la l�nea en sus campos
            // Calcula el cam�no m�nimo y lo escribe
            escritor.escribirCadena(preguntas[i][0] + " ");         // Escribe la ciudad de partida
            calcularMinimoCamino(mCierreT, P, preguntas[i][0], preguntas[i][1], mapaCarreteras);
            escritor.escribirCadena(preguntas[i][1] + " ");         // Escribe la ciudad destino
            // Escribe la longitud del camino
            escritor.escribirLinea(String.valueOf(mCierreT[mapaCarreteras.indiceEquivalente(preguntas[i][0])]
                    [mapaCarreteras.indiceEquivalente(preguntas[i][1])]));
        }
        escritor.escribirCadena("\n");                              // Nueva l�nea


        // *** PROBLEMA 2 ***


        // Convierte el mapa de carreteras tal que todas las ciudades est�n conectadas y el coste de repararlas sea m�nimo
        minimasCarreteras = new GrafoNDVIndexCad(mapaCarreteras.getOrden());
        // Copia el diccionario de nombres de ciudades en el nuevo grafo
        minimasCarreteras.setDiccionarioCadenas(mapaCarreteras.getDiccionarioCadenas());

        // Escribe el coste de reparar las m�nimas carreteras
        escritor.escribirLinea(String.valueOf(calcularExpansionMinima(mapaCarreteras, minimasCarreteras)));

        // Reinicializa la matriz P para el nuevo mapa
        inicializarMatri(P, Main.P_NULO);

        mCierreT = calcularCierreTransitivo(minimasCarreteras, P);  // Calcula la matriz de cierre transitivo del nuevo mapa
        // Calcula los caminos m�nimos a partir del nuevo mapa de carreteras
        for (int i = 0; i < p; i++) {
            escritor.escribirCadena(preguntas[i][0] + " ");         // Escribe la ciudad de partida
            // Calcula el cam�no m�nimo y lo escribe
            calcularMinimoCamino(mCierreT, P, preguntas[i][0], preguntas[i][1], minimasCarreteras);
            escritor.escribirCadena(preguntas[i][1] + " ");         // Escribe la ciudad destino
            // Escribe la longitud del camino
            escritor.escribirLinea(String.valueOf(mCierreT[minimasCarreteras.indiceEquivalente(preguntas[i][0])]
                    [minimasCarreteras.indiceEquivalente(preguntas[i][1])]));
        }
    }

}