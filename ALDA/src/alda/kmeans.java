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
public class kmeans {
 
    public static ArrayList<Cluster> kmeans(Cluster cl){
        ArrayList dataset=cl.items;
        ArrayList<Centroid> centroid=new ArrayList();
        ArrayList<Cluster> cluster=new ArrayList();
        Cluster clust = new Cluster();
        boolean changes = true;
        int counter=0;
        Data d;
        Centroid c;
        int a;
        a=(int)Math.round((Math.random()*100)%dataset.size());
        c = new Centroid(((Data)dataset.get(a)).x,((Data)dataset.get(a)).y);
        centroid.add(c);
        a=(int)Math.round((Math.random()*100)%dataset.size());
        c = new Centroid(((Data)dataset.get(a)).x,((Data)dataset.get(a)).y);
        centroid.add(c);
        
        //c=new Centroid(((Data)dataset.get(0)).x,((Data)dataset.get(0)).y);
        //centroid.add(c);
        //c=new Centroid(((Data)dataset.get(dataset.size()-1)).x,((Data)dataset.get(dataset.size()-1)).y);
        //centroid.add(c);
        
        while(counter<10) {
        
            for (int i = 0; i < dataset.size(); i++) {
                double min = 0, tempdist;
                d = (Data) dataset.get(i);
                for (int j = 0; j < centroid.size(); j++) {
                    c = (Centroid) centroid.get(j);
                    tempdist = c.euclideandist(d, c);
                    if (min == 0) {
                        min = tempdist;
                        d.assigncluster(j);
                    }
                    if (tempdist < min) {
                        min = tempdist;
                        d.assigncluster(j);
                    }
                }
                System.out.println(d.x + " " + d.y + "       " + d.belongs_to_cluster);
            }
            d = null;

            for (int i = 0; i < centroid.size(); i++) {
                double x = 0, y = 0;
                int total = 0;
                for (int j = 0; j < dataset.size(); j++) {
                    d = (Data) dataset.get(j);
                    if (d.belongs_to_cluster == i) {
                        x = x + d.x;
                        y = y + d.y;
                        total++;
                        clust.items.add(d);
                    }


                }
                c = (Centroid) centroid.get(i);
                double oldcx = c.x;
                double oldcy = c.y;
                c.x = x / total;
                c.y = y / total;
                clust.c.x=x/total;
                clust.c.y=y/total;
                if (Math.abs(oldcx - c.x) < 0.1) {
                    if (Math.abs(oldcy - c.y) < 0.1) {
                        changes = false;
                    }
                }
                /*if(oldcx==c.x_coor && oldcy==c.y_coor){
                 changes=false;
                 }*/
                System.out.println("The old centroid" + i + " is:" + oldcx + "  " + oldcy);
                System.out.println("The new centroid" + i + " is:" + c.x + "  " + c.y);
                cluster.add(clust);
                counter++;
            }
        }//end while
        return cluster;
}
}