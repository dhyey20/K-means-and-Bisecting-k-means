/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alda;

import java.util.Comparator;

/**
 *
 * @author dhyey
 */
class ClusterComparator implements Comparator<Cluster> {        //this class implements comparator to store the cluster
                                                                //in the priority queue according to the sse values

    public ClusterComparator() {
    }

    @Override
    public int compare(Cluster o1, Cluster o2) {
        double a=o1.SSE;
        double b=o2.SSE;
        double diff= b-a;
        return (diff>0)?1:(diff<0)?-1:0; 
    }
    
    
}
