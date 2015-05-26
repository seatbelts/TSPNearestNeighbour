package tsp;

import java.util.Collections;
import java.util.ArrayList;

public class VMC extends Main implements Runnable{
    
    int count;
    
    public VMC(int count){
        this.count = count;
    }
    
    //La inciaciond de los hilos, la clase recibe el valor del iterador en el for donde se ejecutan/ generan los hilos
    //para poder llevar el control de los recorridos, por ejemplo, recibe 0, el recorrido comienza en la ciudad 0, y asi.   
    public void run(){
        
        int n = count;
        int dummy = 0;
        ArrayList<ArrayList<Boolean>> dummyList = new ArrayList<ArrayList<Boolean>>();
        ArrayList booleanList = new ArrayList();
        ArrayList rows = new ArrayList();
        
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j < 10; j++) {
                booleanList.add(true);
            }
            dummyList.add(booleanList);
        }
        
        rows.add(n);
        double newDummy = Collections.max(distances.get(n));
        
        for (int i = 0; i < size; i++) {
            System.out.println(i);
            double Dummy = distances.get(n).get(i);
            if (((Dummy <= newDummy) && !(Dummy == 0))) {
                newDummy = Dummy;
                dummy = i;
            }
        }
        
        for (int i = 0; i < size; i++) {
            dummyList.get(i).set(dummy, false);
            dummyList.get(i).set(n, false);
        }
        
        totalDistances[count] = totalDistances[count] + newDummy;
        rows.add(dummy);
        
        n = dummy;
        
        for (int i = 0; i < size; i++) {
            
            newDummy = Collections.max(distances.get(n));
            
            for (int j = 0; j <= size; j++) {
                double Dummy = distances.get(n).get(i);
                if ((Dummy <= newDummy) && !(Dummy == 0) &&(dummyList.get(n).get(j))) {
                    newDummy = Dummy;
                    dummy = j;
                }
            }
            
            for (int k = 0; k <= size; k++) {
                dummyList.get(k).set(dummy, false);
            }
            
            totalDistances[count] = totalDistances[count] + newDummy;
            rows.add(dummy);            
        }
        
        visitedCities.set(count, rows);
    }
    
}