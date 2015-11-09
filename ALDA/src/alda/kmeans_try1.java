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
public class kmeans_try1 {
    //dataset and number of clusters comes in as arguments
    //arraylist of the clusters formed is returned 
    public ArrayList<Cluster> kMeans_try(ArrayList<Data> dataset, int number_of_clusters){
        //dataset contains all the data to be applied kmean
        //number_of_clusters we want after applying kmeans
        Centroid c;
        Data d;
        Cluster cl=new Cluster();
        boolean changes=true;
        ArrayList<Data> datacluster=new ArrayList();
        ArrayList<Cluster> clusters=new ArrayList(number_of_clusters);
        ArrayList<Centroid> centroids=new ArrayList();
        //thus add number_of_clusters centroids
        for(int i=0;i<number_of_clusters;i++){
            /*
             * we can take centroid as a random data point 
             * else we can also take the centroid as the data point in the given set
             * Again here as the data provided is not random taking random centroids forms empty clusters
             * so it is better to take the data points as centroids but agian in a random manner
             * 
            double a;
            a=(int)Math.round((Math.random()*100)%dataset.size());
            c = new Centroid(((Data)dataset.get(a)).x,((Data)dataset.get(a)).y);
            centroid.add(c);
            a=(int)Math.round((Math.random()*100)%dataset.size());
            c = new Centroid(((Data)dataset.get(a)).x,((Data)dataset.get(a)).y);
            centroid.add(c);
             */
            //
            //double x=Math.random()*100;
            //double y=Math.random()*100;
            //c=new Centroid(x,x);
            int a;
            a=(int)Math.round((Math.random()*100)%dataset.size());
            c = new Centroid(((Data)dataset.get(a)).x,((Data)dataset.get(a)).y);
            centroids.add(c);
            cl.c.x=c.x;
            cl.c.y=c.y;
            clusters.add(cl);
        }
        
        //while changes occur
        while(changes){
        //now assign each data element to any one of the number_of_clusters
        for(int i=0;i<dataset.size();i++){
            d=dataset.get(i);
            double min=0;
            double dist=0;
            int nearest_cluster=0;
            for(int j=0;j<number_of_clusters;j++){
                c=centroids.get(j);
                dist=c.euclideandist(d, c);
                if(j==0){
                    min=dist;
                    nearest_cluster=0;
                }
                else if(dist<min){
                    min=dist;
                    nearest_cluster=j;
                    
                }
            }//end inner loop
            d.belongs_to_cluster=nearest_cluster;
            dataset.set(i, d);
            //System.out.println("Data  "+d.x+" "+d.y+"       belongs to cluster: "+d.belongs_to_cluster);
        }//end outer loop
        clusters.removeAll(clusters);
        
        
        //now calculate the new centroid and the reassigning the points to the clusters according to the new centroids calculated
        for(int j=0;j<number_of_clusters;j++){
            cl=new Cluster();
            //cl.items=new ArrayList();
            //cl.c=new Centroid(0,0);
            clusters.add(cl);
            c=centroids.get(j);
            double x=0;
            double y=0;
            double total=0;
            for(int i=0;i<dataset.size();i++){
                d=dataset.get(i);
                if(d.belongs_to_cluster==j){
                    x=x+d.x;
                    y=y+d.y;
                    total++;
                    cl=clusters.get(j);
                    cl.items.add(d);
                    //clusters.set(j, cl);
        
                }//end if
                
            }//end inner loop
            //we got allpoints in a cluster
            //calculate new centroid
            double oldcx=c.x;
            double oldcy=c.y;
            double newcx=x/total;
            double newcy=y/total;
            c.x=newcx;
            c.y=newcy;
            //cl=clusters.get(j);
            cl.c.x=newcx;
            cl.c.y=newcy;
            clusters.set(j,cl);
            cl=null;
            //System.out.println("New Centroid "+j+" is "+newcx+" "+newcy);
            centroids.set(j, c);
            if(Math.abs(oldcx-newcx)<=0.001){
                if(Math.abs(oldcy-newcy)<=0.001){
                    changes=false;
                    //if the old and the new centroids stop changing then we stop the loop
                }
            }
        }//end outer loop
        }//end while
    return clusters;
    }//end kmeans_try
}//end class
