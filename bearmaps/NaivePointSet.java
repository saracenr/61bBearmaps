package bearmaps;

import java.util.List;

import static bearmaps.Point.distance;
import static java.lang.Math.sqrt;

public class NaivePointSet {

    private List<Point> points;

    public NaivePointSet(List<Point> listPoints) {
        points = listPoints;
    }

    public Point nearest(double x, double y) {
        double bestDistance = Double.MAX_VALUE;
        Point bestPoint = null;
        Point compare = new Point(x,y);
        for (Point p: points) {
            double currentDistance = sqrt(distance(compare, p));
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                bestPoint = p;
            }
        }
        return bestPoint;
    }
}
