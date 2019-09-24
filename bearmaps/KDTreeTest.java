package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    public static List<Point> createRandomList(Integer n, Integer range) {
        List<Point> list = new ArrayList<>();
        Random random = new Random();
        Point point;
        for (int x = 0; x < n; x++) {
            point = new Point(random.nextInt(range), random.nextInt(range));
            list.add(point);
        }
        return list;
    }

    public static void testManyPoints(int size, int range, int numberOfTests) {
        List<Point> list = createRandomList(size, range);
        Random random = new Random();
        NaivePointSet nn = new NaivePointSet(list);
        KDTree kd = new KDTree(list);
        int correctAnswers = 0;
        int wrongAnswers = 0;
        int slower = 0;
        for (int x = 0; x < numberOfTests; x++) {
            int xCoord = random.nextInt(range);
            int yCoord = random.nextInt(range);
            Stopwatch sw = new Stopwatch();
            Point ret = nn.nearest(xCoord, yCoord); // returns p2
//            System.out.println("The correct answer is:");
//            System.out.println(ret.getX()); // evaluates to 3.3
//            System.out.println(ret.getY()); // evaluates to 4.4
            double naiveTime = sw.elapsedTime();
            System.out.println("Total time elapsed Naive: " + naiveTime + " seconds.");

            Stopwatch nw = new Stopwatch();
            Node KDret = kd.nearest(xCoord, yCoord);
//            System.out.println("The KD tree says its:");
//            System.out.println(KDret.getVal().getX()); // evaluates to 3.3
//            System.out.println(KDret.getVal().getY()); // evaluates to 4.4
            double kdTreeTime = nw.elapsedTime();
            System.out.println("Total time elapsed KDTree: " + kdTreeTime + " seconds.");

            if (naiveTime > kdTreeTime) {
                slower+=1;
            }

            if (ret.getX() == KDret.getVal().getX() && ret.getY() == KDret.getVal().getY()) {
                correctAnswers+=1;
            }
            else {
                wrongAnswers+=1;
            }
        }
        System.out.println("Total wrong answers: " + wrongAnswers);
        System.out.println("Total correct answers: " + correctAnswers);
        System.out.println("KDTree was slower than Naive: " + slower + " times out of 1000");
    }

    public static void testManyTrees(int size, int range, int numberOfTrees) {
        Random random = new Random();
        for (int z = 0; z < numberOfTrees; z++) {
            List<Point> list = createRandomList(size, range);
            NaivePointSet nn = new NaivePointSet(list);
            KDTree kd = new KDTree(list);
            int correctAnswers = 0;
            int wrongAnswers = 0;
            int slower = 0;
            for (int x = 0; x < 50; x++) {
                int xCoord = random.nextInt(range);
                int yCoord = random.nextInt(range);
                Stopwatch sw = new Stopwatch();
                Point ret = nn.nearest(xCoord, yCoord); // returns p2
//            System.out.println("The correct answer is:");
//            System.out.println(ret.getX()); // evaluates to 3.3
//            System.out.println(ret.getY()); // evaluates to 4.4
                double naiveTime = sw.elapsedTime();
//                System.out.println("Total time elapsed Naive: " + naiveTime + " seconds.");

                Stopwatch nw = new Stopwatch();
                Node KDret = kd.nearest(xCoord, yCoord);
//            System.out.println("The KD tree says its:");
//            System.out.println(KDret.getVal().getX()); // evaluates to 3.3
//            System.out.println(KDret.getVal().getY()); // evaluates to 4.4
                double kdTreeTime = nw.elapsedTime();
//                System.out.println("Total time elapsed KDTree: " + kdTreeTime + " seconds.");

                if (naiveTime > kdTreeTime) {
                    slower += 1;
                }

                if (ret.getX() == KDret.getVal().getX() && ret.getY() == KDret.getVal().getY()) {
                    correctAnswers += 1;
                } else {
                    wrongAnswers += 1;
                }
            }
            System.out.println("Test " + z + " out of " + numberOfTrees);
            System.out.println("Total wrong answers: " + wrongAnswers);
            System.out.println("Total correct answers: " + correctAnswers);
            System.out.println("KDTree was slower than Naive: " + slower + " times out of 1000");
        }
    }
    public static void main(String args[]) {
        testManyTrees(100000,100000, 100);
    }
}
