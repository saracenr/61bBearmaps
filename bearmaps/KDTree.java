package bearmaps;

import java.util.List;
import java.lang.Math;

import static bearmaps.Point.distance;

public class KDTree {

    private Node root;

    // Points has at least size 1
    public KDTree(List<Point> listPoints) {
        root = null;
        for (Point p: listPoints) {
            if (root == null) {
                root = new Node(p);
            }
            else {
                root.addNode(new Node(p));
            }
        }
    }

    public Node nearestHelper(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }

        double bestDistance = distance(best.getVal(), goal);
        if (distance(n.getVal(), goal) < bestDistance) {
            best = n;
        }
        Node goodSide = n.comparator(goal);
        Node badSide = null;
        if (goodSide == n.getLeft()) {
            badSide = n.getRight();
        }
        else if (goodSide == n.getRight()) {
            badSide = n.getLeft();
        }

        best = nearestHelper(goodSide, goal, best);

        if (badSide != null && badSide.getDepth() % 2 == 0) {
            if (Math.abs(badSide.getVal().getY() - goal.getY()) < bestDistance) {
                best = nearestHelper(badSide, goal, best);
            }
        }
        else if (badSide != null) {
            if (Math.abs(badSide.getVal().getX() - goal.getX()) < bestDistance) {
                best = nearestHelper(badSide, goal, best);
            }
        }
        return best;
    }

    public Node nearest(double x, double y) {
        Point coords = new Point(x,y);
        return nearestHelper(root,coords,root);

    }
}
