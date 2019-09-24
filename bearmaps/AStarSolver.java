package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private SolverOutcome result;
    private List<Vertex> solution;
    private double solutionWeight;
    private int statesExplored;
    private double time;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        solutionWeight = 0;
        pq.add(start,0);
        List<Vertex> visited = new ArrayList<>();
        Vertex source = null;
        int explored = 0;
        Stopwatch sw = new Stopwatch();

        while (pq.size() > 0) {

            if (pq.getSmallest().equals(end)) {
                solutionWeight = pq.findPriority(pq.getSmallest());
                visited.add(pq.removeSmallest());
                result = SolverOutcome.SOLVED;
                solution = visited;
                statesExplored = explored;
                time = sw.elapsedTime();
                return;
            }
            else if (sw.elapsedTime() > timeout) {
                result = SolverOutcome.TIMEOUT;
                return;
            }

            explored+=1;
            source = pq.getSmallest();
            double sourcePriority = pq.findPriority((source));
            visited.add(source);
            pq.removeSmallest();

            for (WeightedEdge<Vertex> e: input.neighbors(source)) {
                Vertex p = e.from();
                Vertex q = e.to();
                double w = e.weight();
                System.out.println("This is edge (" + p + "," + q + ")");
                double priority = sourcePriority+w+input.estimatedDistanceToGoal(q,end);
                if (pq.contains(q)) {
                    System.out.println("PQ Contains " + q);
                    if (sourcePriority + w <  pq.findPriority(q)) {
                        pq.changePriority(q, priority);
                    }

                }
                else {
                    System.out.println("Adding " + q + " with priority " + priority);
                    pq.add(q, priority);
//                    pq.printFancyHeapDrawing();
                }
            }

        }
    }

    public SolverOutcome outcome() {
        return result;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return statesExplored;
    }
    public double explorationTime() {
        return time;
    }
}
