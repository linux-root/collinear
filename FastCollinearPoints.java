/* *****************************************************************************
 *  Name: Dinh Bao Khanh
 *  Date: 19/05/2020
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;
    private int numberOfLineSeqment;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points list must not be null");
        }
        LineSegment[] temp = new LineSegment[points.length * 10];
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
                        temp[c++] = new LineSegment(head, tail[j - 1]);
                    }
                    current = tail[j];
                    equality = 0;
                }
                else {
                    equality++;
                }
            }
        }
        this.numberOfLineSeqment = c;
        this.segments = new LineSegment[c];
        for (int i = 0; i < c; i++) {
            this.segments[i] = temp[i];
        }
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
        return this.segments;
    }
}
