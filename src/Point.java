/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    // YOUR DEFINITION HERE
    public final Comparator<Point> SLOPE_ORDER = new BySlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // comparator to sort by name
    
    /* SLOPE_ORDER comparator should compare points by the slopes they make with 
     * the invoking point (x0, y0). Formally, the point (x1, y1) is less than 
     * the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0) is less 
     * than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and 
     * degenerate line segments as in the slopeTo() method.
     */
    private class BySlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            //Point p = new Point(x, y);
            
            //double temp = p.slopeTo(o1) - p.slopeTo(o2);
            double temp = slopeTo(o1) - slopeTo(o2);
            if (temp > 0) {
                return 1;
            }
            else if (temp < 0) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    
    /* Return the slope between the invoking point (x0, y0) and the argument 
     * point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0). 
     * 
     * Treat the slope of a horizontal line segment as positive zero 
     * [added 7/29]; 
     * 
     * Treat the slope of a vertical line segment as positive 
     * infinity;
     * 
     * Treat the slope of a degenerate line segment (between a point 
     * and itself) as negative infinity.
     */
    public double slopeTo(Point that) {
        
        
        //degenerate line segment
        if ((that.y == this.y) && (that.x == this.x)) {
            return Double.NEGATIVE_INFINITY;
        }
        //horizontal line segment
        else if (that.y == this.y) {
            return 0.0;
        }
        //vertical line segment
        else if (that.x == this.x) {
            return Double.POSITIVE_INFINITY;
        }
        //divide by 0 error
        else if ((that.x - this.x) == 0) {
            if ((that.y - this.y) > 0) {
                return 0.0;
            }
            if ((that.y - this.y) == 0) {
                return 0.0;
            }
            else {
                return -0.0;
            }
        }
        else {
            return ((double)(that.y - this.y) / (double)(that.x - this.x));
        }
        
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    
    /* The compareTo() method should compare points by their y-coordinates, 
     * breaking ties by their x-coordinates. 
     * Formally, the invoking point (x0, y0) is less than the argument point 
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     */
    public int compareTo(Point that) {
        if ((this.y == that.y) && (this.x == that.x)) {
            return 0;
        }
        else if ((this.y == that.y) && (this.x < that.x)) {
            return -1;
        }
        else if (this.y < that.y) {
            return -1;
        }
        else {
            return 1;
        }
    }

    // return string representation of this point
    @Override
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
