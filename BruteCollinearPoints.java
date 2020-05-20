import java.util.Arrays;

public class BruteCollinearPoints {

    private final int numberOfLineSeqment;
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(final Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points list must not be null");
        }
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("Point is null ");
        }
        final int pad = 10;
        lineSegments = new LineSegment[points.length * pad];
        int c = 0;
        final Point[] sortedPoints = Arrays.stream(points).sorted().toArray(Point[]::new);
        for (int i = 0; i < sortedPoints.length; i++) {
            for (int j = i + 1; j < sortedPoints.length; j++) {
                if (sortedPoints[i].equals(sortedPoints[j])) {
                    throw new IllegalArgumentException(
                            "duplicated Point: " + sortedPoints[i].toString() + " and " + sortedPoints[j]
                                    .toString());
                }
                for (int k = j + 1; k < sortedPoints.length; k++) {
                    if (sortedPoints[k].equals(sortedPoints[j])) {
                        throw new IllegalArgumentException(
                                "duplicated Point: " + sortedPoints[k].toString() + " and "
                                        + sortedPoints[j].toString());
                    }
                    if (sortedPoints[i].slopeOrder().compare(sortedPoints[j], sortedPoints[k]) != 0) {
                        continue;
                    }
                    Point endingPointOfLineSegment = null;
                    for (int n = k + 1; n < sortedPoints.length; n++) {
                        if (sortedPoints[n].equals(sortedPoints[k])) {
                            throw new IllegalArgumentException(
                                    "duplicated Point: " + sortedPoints[n].toString() + " and "
                                            + sortedPoints[k].toString());
                        }
                        if (sortedPoints[j].slopeOrder().compare(sortedPoints[k], sortedPoints[n]) == 0) {
                            endingPointOfLineSegment = sortedPoints[n];
                        }
                    }
                    if (endingPointOfLineSegment != null) {
                        lineSegments[c++] = new LineSegment(sortedPoints[i], endingPointOfLineSegment);
                    }
                }
            }
        }
        this.numberOfLineSeqment = c;
    }

    // finds all line segments containing 4 points
    public int numberOfSegments() {
        // the number of line segments
        return this.numberOfLineSeqment;
    }

    public LineSegment[] segments() {
        // the line segments
        final LineSegment[] segments = new LineSegment[numberOfLineSeqment];
        for (int i = 0; i < numberOfLineSeqment; i++) {
            segments[i] = this.lineSegments[i];
        }
        return segments;
    }
}
