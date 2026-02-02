import java.lang.Math;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.Comparator;

public class FastCollinearPoints {

    private final Point[] points;
    private LineSegment[] segs;
    private int count;
    int AddBuffer = 1; // needed to check if theres a spot to add a segment
    int segLength = 1; //points.length/4; // chosen since line is four so assume worst case that each point falls in a 4 segement

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {

        // Check all exceptions
        if (points == null) throw new IllegalArgumentException("Points is null");
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("there is a null point in the Points array");
        }
        if (Arrays.stream(points).distinct().count() != points.length) throw new IllegalArgumentException("Duplicate points");

        this.points = points;
        this.count = 0;
        this.segs = new LineSegment[points.length];

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {

            Comparator<Point> comp = points[i].slopeOrder();
            Point[] tmp = Arrays.copyOfRange(points, i, points.length);
            Arrays.sort(tmp, comp);

            for (int j = 0; j< tmp.length; j++){
                int k = j+1;
                int l = k+1;
                if (Math.abs(tmp[j].slopeTo(points[i])) == Math.abs(tmp[k].slopeTo(points[i])) && Math.abs(tmp[j].slopeTo(points[i])) == Math.abs(tmp[l].slopeTo(points[i]))) {
                    segs = checkArray(segs);
                    Point[] extremes = points[i].findExtremes( tmp[j], tmp[k], tmp[l]);
                    segs[count++] = new LineSegment(extremes[0], extremes[1]);
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.count;

    }

    private LineSegment[] checkArray(LineSegment[] segs){
        int AddBuffer = 1;
        int segLength = points.length;
        if (segs.length - count <= AddBuffer ){
            // Use Arrays.copyOf() to create a new array and copy all elements
            segs = Arrays.copyOf(segs, segs.length+segLength);
        }
        return segs;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segs;
    }
}

