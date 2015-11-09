/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alda;

/**
 *
 * @author dhyey
 */
public class Centroid {     //Stores the x and y i.e two dimensions of the centroid
    double x;
    double y;
    
    public Centroid(double x,double y){
        this.x=x;
        this.y=y;
    }
    //considering 2d data
    public double euclideandist(Data d,Centroid c){         //calculates the 2d euclidean distance between d and c
        double dx=d.x;
        double dy=d.y;
        double cx=c.x;
        double cy=c.y;
        double temp;
        temp = Math.pow((Math.abs(dx-cx)),2) + Math.pow((Math.abs(dy-cy)),2);
        temp = Math.sqrt(temp);
        return temp;
    }
}
