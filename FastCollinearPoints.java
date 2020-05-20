/* *****************************************************************************
 *  Name: Dinh Bao Khanh
 *  Date: 19/05/2020
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;
    private final int numberOfLineSeqment;

    public FastCollinearPoints(final Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points list must not be null");
        }
        final int m = 10;
        this.lineSegments = new LineSegment[points.length * m];
        int c = 0;
        for (int i = 0; i < points.length; i++) {
            Point head = points[i];
            if (head == null) {
                throw new IllegalArgumentException("Point is Null");
            }
            Point[] tail = tail(points); // get tail of this array
            Arrays.sort(tail); // sort by position in flat
            Arrays.sort(tail, head.slopeOrder()); // sort by slope

            Point current = tail[0];
            int equality = 0;
            for (int j = 1; j < tail.length; j++) {
                if (head.slopeOrder().compare(current, tail[j]) != 0) {
                    if (equality >= 2) {
                        this.lineSegments[c++] = new LineSegment(head, tail[j - 1]);
                    }
                    current = tail[j];
                    equality = 0;
                }
                else {
                    equality++;
                }
            }
            if (equality >= 2) {
                this.lineSegments[c++] = new LineSegment(head, tail[tail.length - 1]);
            }
        }
        this.numberOfLineSeqment = c;
    }

    private Point[] tail(Point[] source) {
        return Arrays.stream(source, 1, source.length)
                     .toArray(Point[]::new);
    }


    // finds all line segments containing 4 points
    public int numberOfSegments() {
        // the number of line segments
        return this.numberOfLineSeqment;
    }

    public LineSegment[] segments() {
        // the line segments
        final LineSegment[] segments = new LineSegment[this.numberOfLineSeqment];
        for (int i = 0; i < this.numberOfLineSeqment; i++) {
            segments[i] = this.lineSegments[i];
        }

        return segments;
    }
}
