package tsp;

import java.util.ArrayList;

public class VerificarMatriz{
    
    public int size;
    public ArrayList<ArrayList<Double>> distances;
    
    public VerificarMatriz(ArrayList<ArrayList<Double>> distances, int size){
        this.distances = distances;
        this.size = size;
    }
    
    boolean matriz(){
        for (int i = 0; i <= size; i++){
			for (int j = 0; j < size; j++){
				if ((i == j)){
					if (!(distances.get(i).get(j) == 0.0)) {
						System.out.print("Un grafo esta dirigido a si mismo, no se puede realizar el programa");
						return false;
					}
				}
			}
		}
		return true;
    }
}