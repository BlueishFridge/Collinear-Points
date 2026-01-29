import java.util.Arrays;
import java.util.Arrays.*;

public class BruteCollinearPoints {

    private final Point[] points;
    private LineSegment[] segs;
    private int count;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        this.points = points;
        this.count = 0;
        int AddBuffer = 4;
        int segLength = points.length;


        Arrays.sort(points);
        System.out.println(Arrays.toString(points));

        //array of all possible segements
        this.segs = new LineSegment[points.length];

        for (int i = 0; i< points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                for (int k = j+1; k < points.length;k++) {
                    for (int l = k+1; l < points.length; l++) {
                        double slope1,slope2,slope3 = 0;
                        slope1 = points[i].slopeTo(points[j]);
                        slope2 = points[i].slopeTo(points[k]);
                        slope3 = points[i].slopeTo(points[l]);

                        if (slope1 == slope2 && slope1 == slope3) {
                            if (segs.length - count <= AddBuffer ){
                                // Use Arrays.copyOf() to create a new array and copy all elements
                                segs = Arrays.copyOf(segs, segs.length+segLength);
                                System.out.println("lol");
                            }
                            segs[count++] = new LineSegment(points[i],points[j]);
                            segs[count++] = new LineSegment(points[j],points[k]);
                            segs[count++] = new LineSegment(points[k],points[l]);
                            segs[count++] = new LineSegment(points[i],points[l]);

                        }
                        else System.out.println("false");
                    }
                }

            }
        }


    }

    // the number of line segments
    public int numberOfSegments() {
        return this.count;

    }

    // the line segments
    public LineSegment[] segments() {
        return this.segs;
    }
}
