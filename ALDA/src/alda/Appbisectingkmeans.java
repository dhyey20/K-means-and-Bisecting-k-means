/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dhyey
 */
public class Appbisectingkmeans {
     public static void main(String args[]) throws FileNotFoundException, IOException{
        kmeans_try1 km=new kmeans_try1();
        int TOTAL_DATA_SIZE=400;
        int TOTAL_REQUIRED_CLUSTERS=8;
        int clustercounter=0;
        //taking randomly generated data
        //instead we can read data from a .txt or .dat file
        double inputdata[][]=new double[TOTAL_DATA_SIZE][2];
        File fil= new File("d-c4hw2.csv");
        BufferedReader data_in = new BufferedReader(new FileReader(fil));
        int i=0;
        int flag=0;
        while(data_in.ready())
                {
                    String line = data_in.readLine();
                    //System.out.println(line);
                    
                    if(flag==0){
                        flag=1;
                        continue;
                    }
                    String[] s=line.split(",");
                    for(int j=0;j<s.length;j++)
                    {
                        inputdata[i][j]=Double.valueOf(s[j]);
                    }
                     i++;
                 }
         
        /*
        double inputdata[][]=new double[TOTAL_DATA_SIZE][TOTAL_DATA_SIZE];
    
        for(int i=0;i<TOTAL_DATA_SIZE;i++){
            inputdata[i][0]=Math.random()*100;
            inputdata[i][1]=Math.random()*100;
        }
    */
          
        ArrayList<Data> dataset = new ArrayList();
        Data d;
        int k=0;
        while(k < TOTAL_DATA_SIZE) {
            d = new Data(inputdata[k][0],inputdata[k][1]);
            dataset.add(d);
            k++;
        }
        k=0;
        
        //final list containing k clusters
        ArrayList<Cluster> finalclusters=new ArrayList();
        //temp clusters which stores return from kmeans 
        ArrayList<Cluster> tempclusters=new ArrayList();
        Centroid c;
        Cluster cl;
        
        //make a big cluster
        //ad all x and y and find the centroid
        cl=new Cluster();
        cl.items=dataset;
        double x=0,y=0;
        for(i=0;i<cl.items.size();i++){
            d=cl.items.get(i);
            x=x+d.x;
            y=y+d.y;
            d.belongs_to_cluster=clustercounter;
            cl.items.set(i,d);
        }
        double cx=x/cl.items.size();
        double cy=y/cl.items.size();
        cl.c.x=cx;
        cl.c.y=cy;
        cl.SSE=cl.calSSE();
        //System.out.println("SSE:    "+cl.SSE);
        //add the main big cluster to finalclusters
        finalclusters.add(cl);
        clustercounter++;
        k=finalclusters.size();
        //till we have k clusters
        while(k<TOTAL_REQUIRED_CLUSTERS){
        double max=0;
        int maxpos=0;
        //this loop finds the cluster with the maxsse
        for(i=0;i<finalclusters.size();i++){
            cl=finalclusters.get(i);
            if(i==0){
                max=cl.calSSE();
                maxpos=i;
            }
            else if(cl.calSSE()>max){
                max=cl.calSSE();
                maxpos=i;
            }
        }//end for
        cl=new Cluster();
        //System.out.println("MAx:    "+maxpos+"  maxvalue:   "+max);
        cl=finalclusters.remove(maxpos);
        //clustercounter--;
        //remove element with highestSSE from the finalclusters to the bisect
        ArrayList<Data> dataset1=cl.items;
        ArrayList<Cluster> kmeanret;
        kmeanret=km.kMeans_try(dataset1, 2);
        //System.out.println("Number of clusters:"+kmeanret.size());
        for(i=0;i<kmeanret.size();i++){
            cl=kmeanret.get(i);
            cl.SSE=cl.calSSE();
            for(int j=0;j<cl.items.size();j++){
                d=cl.items.get(j);
                d.belongs_to_cluster=clustercounter;
                cl.items.set(j, d);
            }//end inner for
            finalclusters.add(cl);
            clustercounter++;
            //System.out.println("Centroids returned are  "+cl.c.x+"      "+cl.c.y+"  has "+cl.items.size()+" points.");
            //System.out.println("Size of final:  "+finalclusters.size());
        }
        k=finalclusters.size();
        }//end while
        for(i=0;i<finalclusters.size();i++){
            cl=finalclusters.get(i);
            //System.out.println("Centroids returned are  "+cl.c.x+"      "+cl.c.y+"  has "+cl.items.size()+" points and sse is   "+cl.SSE);
        }//these prints the final clusters
        k=finalclusters.size();
        
        ArrayList<Data> nodeprint=new ArrayList<Data>();
        for(i=0;i<finalclusters.size();i++){
            cl=finalclusters.get(i);
            nodeprint=cl.items;
            for(int j=0;j<nodeprint.size();j++){
                d=nodeprint.get(j);
                System.out.println(d.x+" "+d.y+" "+d.belongs_to_cluster);
            }
        }
        double totalsse=0;
        for(i=0;i<finalclusters.size();i++){
            cl=finalclusters.get(i);
            //System.out.println(cl.SSE);
            totalsse+=cl.SSE;
        }
        System.out.println(totalsse);
     }//end main   
}
