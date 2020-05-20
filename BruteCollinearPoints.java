import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments; private int numberOfLineSeqment; public BruteCollinearPoints(Point[] points) { if (points == null) { throw new IllegalArgumentException("points list must not be null"); } LineSegment[] temp = new LineSegment[points.length * 10]; int c = 0; Arrays.sort(points); try { for (int i = 0; i < points.length; i++) { for (int j = i + 1; j < points.length; j++) { if (points[i].equals(points[j])) { throw new IllegalArgumentException("duplicated Point: " + points[i].toString() + " and " + points[j].toString()); } for (int k = j + 1; k < points.length; k++) { if (points[k].equals(points[j])) { throw new IllegalArgumentException("duplicated Point: " + points[k].toString() + " and " + points[j].toString()); } if (points[i].slopeOrder().compare(points[j], points[k]) != 0) { continue; } Point endingPointOfLineSegment = null; for (int n = k + 1; n < points.length; n++) { if (points[n].equals(points[k])) { throw new IllegalArgumentException("duplicated Point: " + points[n].toString() + " and " + points[k].toString()); } if (points[j].slopeOrder().compare(points[k], points[n]) == 0) { endingPointOfLineSegment = points[n]; } } if (endingPointOfLineSegment != null) { temp[c++] = new LineSegment(points[i], endingPointOfLineSegment); } } } } } catch (NullPointerException e) {
            throw new IllegalArgumentException("Point must not be Null");
        }
        this.numberOfLineSeqment = c;
        this.segments = new LineSegment[c];
        for (int i = 0; i < c; i++) {
            this.segments[i] = temp[i];
        }
    }

    // finds all line segments containing 4 points
    public int numberOfSegments() {
        // the number of line segments
        return this.numberOfLineSeqment;
    }

    public LineSegment[] segments() {
        // the line segments
        return this.segments;
    }
}
