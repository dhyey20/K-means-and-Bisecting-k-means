/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alda;

import java.util.ArrayList;

/**
 *
 * @author dhyey
 */
public class Cluster {
    ArrayList<Data> items=new ArrayList();      //contains all the data points in the cluster
    Centroid c=new Centroid(0,0);               //stores the centroid of the cluster
    double SSE=0;                               //stores the cluster sse
    
    public void print(){
        //for(int i=0;i<items.size();i++){
          //  Data d=items.get(i);
            System.out.println("Cluster:"+c.x+" "+c.y);
        //}
        
    }
    
    public double calSSE(){         //this function calculates the cluster sse when called
        double dist;
        double sse=0;
        for(int i=0;i<items.size();i++){
            Data d=items.get(i);
            dist=c.euclideandist(d, c);
            sse+=dist;
            
        }
        return sse;
    }
}
