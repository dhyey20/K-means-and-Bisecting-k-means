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
public class Appkmeans {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        int TOTAL_DATA_SIZE=400;
        int NUMBER_OF_CLUSTERS=8;
        //taking random data
        double inputdata[][]=new double[TOTAL_DATA_SIZE][2];
    
        /*for(int i=0;i<TOTAL_DATA_SIZE;i++){
            inputdata[i][0]=Math.random()*100;
            inputdata[i][1]=Math.random()*100;
        }
        */
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
         
        ArrayList<Data> dataset = new ArrayList();
        Data d;
        int k=0;
        while(k < TOTAL_DATA_SIZE) {
            d = new Data(inputdata[k][0],inputdata[k][1]);
            dataset.add(d);
            k++;
        }
        //call kmeans with arguments 
        kmeans_try1 km=new kmeans_try1();
        ArrayList<Cluster> clusters=new ArrayList();
        clusters = km.kMeans_try(dataset, NUMBER_OF_CLUSTERS);
        Cluster cl=new Cluster();
        System.out.println("Number of clusters:"+clusters.size());
        /*
        for(i=0;i<clusters.size();i++){
            cl=clusters.get(i);
            System.out.println("Centroids returned are  "+cl.c.x+"      "+cl.c.y+"  has "+cl.items.size()+" points.");
        }*/
        System.out.println("length width cl");
        for(i=0;i<clusters.size();i++){
            cl=clusters.get(i);
            for(int j=0;j<cl.items.size();j++){
                d=cl.items.get(j);
                System.out.println(d.x+" "+d.y+" "+(i+1));
            }
        }
        
    }   
}
