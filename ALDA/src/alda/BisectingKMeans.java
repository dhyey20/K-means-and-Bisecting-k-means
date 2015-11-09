/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alda;
import java.util.*;
/**
 *
 * @author dhyey
 */

public class BisectingKMeans extends kmeans{
    
    public static void main(String args[]){
    
        
    int TOTAL_DATA_SIZE=100;
    double inputdata[][]=new double[TOTAL_DATA_SIZE][TOTAL_DATA_SIZE];
    
    for(int i=0;i<TOTAL_DATA_SIZE;i++){
        inputdata[i][0]=Math.random()*100;
        inputdata[i][1]=Math.random()*100;
    }
    int num_of_iter=1;
    ArrayList<Data> dataset = new ArrayList();
    Data d;
    Centroid c;
    Cluster cl=new Cluster();
    //Comparator<Cluster> comparator=new ClusterComparator();
    //PriorityQueue<Cluster> clusters=new PriorityQueue(3,comparator);
    ArrayList<Cluster> clusters=new ArrayList();
    ArrayList<Cluster> clustertemp=new ArrayList();
    int k = 0;
    double x=0,y=0;
    
    //prepare one cluster and calculateits centroid
    while (k < TOTAL_DATA_SIZE) {
            d = new Data(inputdata[k][0],inputdata[k][1]);
            x+=inputdata[k][0];
            y+=inputdata[k][1];
            dataset.add(d);
            cl.items.add(d);
            k++;
    }
    
    cl.c.x=x/TOTAL_DATA_SIZE;
    cl.c.y=y/TOTAL_DATA_SIZE;
    cl.calSSE();
    clusters.add(cl);
    
    while(clusters.size()<2){
    cl=clusters.remove(0);
    double sse[]=new double[2*num_of_iter];
    double min=0;
    int minpos=0;
    
    for(int i=0;i<num_of_iter;i++){
        sse[i]=0;
        ArrayList<Cluster> clust=kmeans(cl);
        for(int j=0;j<2;j++){
            cl=(Cluster) clust.get(j);
            cl.print();
            cl.SSE=cl.calSSE();
            clustertemp.add(cl);
        }
        clust.removeAll(clust);
    }   
    for(int i=0;i<clustertemp.size();i++){
        cl=clustertemp.get(i);
        sse[i]=cl.SSE; 
    }
    for(int i=0;i<clustertemp.size();i=i+2){
    if(min==0){
                min=sse[i]+sse[i+1];
                minpos=i;
            }
            if(sse[i]+sse[i+1]<min)
            {
                min=sse[i]+sse[i+1];
                minpos=i;
            }
    }
    cl=clustertemp.get(minpos);
    clusters.add(cl);
    cl=clustertemp.get(minpos+1);
    clusters.add(cl);
    clustertemp.removeAll(clustertemp);
    
    }
    System.out.println("Size:"+clusters.size());
    ArrayList<Cluster> finalclusters=new ArrayList();
    for(int j=0;j<clusters.size();j++){
        cl=clusters.get(j);
        cl.print();
     }
    
  
    
    
    }
}
