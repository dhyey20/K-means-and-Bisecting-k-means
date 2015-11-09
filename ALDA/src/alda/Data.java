/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alda;

/**
 *
 * @author dhyey
 */
public class Data {     //stores the two dimensions x and y of the data point
    double x;
    double y;
    int belongs_to_cluster;
    
    public Data(double x,double y){
        this.x=x;
        this.y=y;
        belongs_to_cluster=0;
    }
    
    public void assigncluster(int num){     //assign a cluster number to the data point
        this.belongs_to_cluster=num;
    }
}
