package bearmaps;

public class Node {

    private Node left;
    private Node right;
    private Point val;
    private Integer depth;

    public Node (Point point) {
        val = point;
        left = null;
        right = null;
        depth = 0;
    }

    public void addNode(Node n) {
        // Add node comparing the X values to the parent node
        if (depth % 2 == 0) {
            if (n.val.getX() <= val.getX()) {
                if (left == null) {
                    n.setDepth(depth+1);
                    left = n;
                }
                else {
                    n.setDepth(depth+1);
                    left.addNode(n);
                }
            }
            else if (n.val.getX() > val.getX()) {
                if (right == null) {
                    n.setDepth(depth+1);
                    right = n;
                }
                else {
                    n.setDepth(depth+1);
                    right.addNode(n);
                }
            }
        }

        //  Add node comparing the Y values to the parent node
        if (depth % 2 == 1) {
            if (n.val.getY() <= val.getY()) {
                if (left == null) {
                    n.setDepth(depth+1);
                    left = n;
                }
                else {
                    n.setDepth(depth+1);
                    left.addNode(n);
                }
            }
            else if (n.val.getY() > val.getY()) {
                if (right == null) {
                    n.setDepth(depth+1);
                    right = n;
                }
                else {
                    n.setDepth(depth+1);
                    right.addNode(n);
                }
            }
        }
    }

    public Node comparator(Point goal) {
//        if (left == null && right == null) {
//            return null;
//        }
//        else if (left == null) {
//            return right;
//        }
//        else if (right == null){
//            return left;
//        }

        if (depth % 2 == 0) {
            if (val.getX() >= goal.getX()) {
                return left;
            }
            else {
                return right;
            }
        }

        if (depth % 2 == 1) {
            if (val.getY() >= goal.getY()) {
                return left;
            }
            else {
                return right;
            }
        }
        return null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node n) {
        left = n;
    }
    public Node getRight() {
        return right;
    }

    public void setRight(Node n) {
        right = n;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer d) {
        depth = d;
    }

    public Point getVal() {
        return val;
    }
}
