/* Author: Dale Salcedo
 * Date: 4/16/13
 * Dependencies: stdlib.jar
 * 
 * Purpose: Faster than the brute algorithm. Given a point p, the following 
 * method determines whether p participates in a set of 4 or more collinear 
 * points.
 * 
 * - Think of p as the origin.
 * - For each other point q, determine the slope it makes with p.
 * - Sort the points according to the slopes they makes with p.
 * - Check if any 3 (or more) adjacent points in the sorted order have equal 
 *   slopes with respect to p. If so, these points, together with p, are 
 *   collinear.
 * 
 * Applying this method for each of the N points in turn yields an efficient 
 * algorithm to the problem. The algorithm solves the problem because points 
 * that have equal slopes with respect to p are collinear, and sorting brings 
 * such points together. The algorithm is fast because the bottleneck operation 
 * is sorting.
 */

import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        StdDraw.clear();
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] ogArr = new Point[N];
        Point[] compArr = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            ogArr[i] = p;
            compArr[i] = p;
        }        
        Arrays.sort(ogArr);
        
        for (int a = 0; a < N; a++) {
            int count = 3;
            int start = 0, stop = 0;
            
            //COMPARE POINT
            Point compare = ogArr[a];
            
            //SORT sortArr ACCORDING TO SLOPE WITH COMPARE POINT
            Arrays.sort(compArr, compare.SLOPE_ORDER);
            
            boolean firstTime = true;
            
            //FIND 2 POINTS THAT MAKE SAME SLOPE WITH COMPARE POINT
            for (int b = 1; b < N; b++) {
                boolean leftMostPoint = false;
                
                double slope1 = compare.slopeTo(compArr[b-1]);
                double slope2 = compare.slopeTo(compArr[b]);
                
                //IF 2 SAME SLOPES FOUND, CHECK IF LEFT MOST
                if (slope1 == slope2) {
                    for (int c = (b-1); c < N; c++) {
                        if ((compare.slopeTo(compArr[c]) == slope1) && (compare.compareTo(compArr[c]) == -1)) {
                            leftMostPoint = true;
                        }
                        else if ((compare.slopeTo(compArr[c]) == slope1) && (compare.compareTo(compArr[c]) != -1)) {
                            leftMostPoint = false;
                            break;
                        }
                    }
                    
                    if (leftMostPoint) {
                        count++;
                        if (firstTime) {
                            start = b-1;
                            compare.drawTo(compArr[b-1]);
                            compare.drawTo(compArr[b]);
                            firstTime = false;
                        }
                        else {
                            stop = b;
                            compare.drawTo(compArr[b]);
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            
            if (count >= 4) {
                Arrays.sort(compArr, start, stop+1);
                StdOut.print(compare);
                for (int f = start; f < stop+1; f++) {
                    StdOut.print(" -> " + compArr[f]);
                }
                System.out.println();
            }
        }
        StdDraw.show(0);
        StdDraw.clear();
    }//end Main
}//end Fast