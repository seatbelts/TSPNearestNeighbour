
package tsp;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static int size;
    public static long time;
    public static double[] totalDistances;//Un arreglo para almacenar las distancias totales de cada recorrido
    public static String path;
    public static ArrayList<ArrayList<Double>> distances, visitedCities;//Alamacena los recorridos
    
    public Main(){
    }
    
    //Funcion basica para leer los datos de un archivo de texo.
    public void leerTexto() throws IOException{
        Scanner reader = new Scanner(new File(path));
        
        while (reader.hasNextLine()){
            
            Scanner rowReader = new Scanner(reader.nextLine());
            ArrayList rows = new ArrayList();
            
            while (rowReader.hasNextDouble()){
                //Anade cada linea del documento a una Lista...
                rows.add(rowReader.nextDouble());
            }
            //... y anade esta a otra lista (un Lista de listas).
            distances.add(rows);
        }
        size = distances.size() - 1;
    }
    //Busca la solucion del problema, los recorridos y el costo.
    public void solucion(){
        double dummy = totalDistances[0];
        int flag = 0;
        
        //Creo que me hubiera ahorrado mas tiempo usando Listas en vez de arreglos, pero tenia que llenarlas
        //para luego reemplazarlos con los valores adecuados y eso era un poco molesto.
        for (int i = 0; i < size; i++) {
            if (dummy < totalDistances[i]) {
                dummy = totalDistances[i];
                flag = i;
            }
        }
        
        System.out.println("Recorrido mas barato: " + visitedCities.get(flag));
        for (int i = 0; i <= size; i++) {
            //System.out.print(visitedCities[flag][i] + " ");
        }
        
        System.out.println("\nCon un costo de:\n" + totalDistances[flag]);
        System.out.println("Tiempo: " + time + " ms");
    }
    
    public static void main(String[] args) throws IOException, InterruptedException{
        path = "C:/Users/Ismael/ciudades.txt";
        distances = new ArrayList<ArrayList<Double>>();
        ArrayList dummy = new ArrayList();
        
        Main tsp = new Main();
        tsp.leerTexto();
        
        //visitedCities = new int [size + 4][size + 4];
        visitedCities = new ArrayList<ArrayList<Double>>();
        totalDistances = new double[size + 1];
        
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                dummy.add(i);
            }
            visitedCities.add(dummy);
        }
        
        for(ArrayList<Double> m: distances){
            System.out.println(m);
        }
        
        VerificarMatriz mts = new VerificarMatriz(distances, size);
        
        //Simplemente para checar que no se dirijan a si mismas las ciudades
        if (mts.matriz()) {
            long start = System.currentTimeMillis();
            
            //Threadpools !1!!11!!
            for (int j = 0; j <= size; j++) {
                ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                Runnable vmc = new VMC(j);
                executor.execute(vmc);
                executor.shutdown();
            }
            
            long end = System.currentTimeMillis();
            
            time = end - start;
        }
        
        //meh
        Thread.sleep(1000);
        
        tsp.solucion();
    }
    
}
