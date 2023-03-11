import java.util.ArrayList;
import java.util.Stack;

public final class PathOptimizer {
    private static Stack<AStarScore> scoresStack = new Stack<AStarScore>();
    private static ArrayList<AStarScore> scoresArchive = new ArrayList<AStarScore>();
    private static int heuristicMultiplier = 3;

    public static double calculateScore(TwoDimVal startPoint, TwoDimVal endPoint, int cost) {
        double score = -1;

        boolean pathIsVisited = false;

        for (AStarScore curScore : scoresArchive) {
            if (curScore.getDualIndexPos().getX() == startPoint.getX() && curScore.getDualIndexPos().getY() == startPoint.getY()) {
                pathIsVisited = true;
                break;
            }
        }

        if (!pathIsVisited) {
            double deltaX = Math.abs(startPoint.getX() - endPoint.getX());
            double deltaY = Math.abs(startPoint.getY() - endPoint.getY());

            double heuristic = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) * heuristicMultiplier;

            score = cost + heuristic;

            storeScore(startPoint, score);
        }
        
        return score;
    }

    private static void storeScore(TwoDimVal dualIndexPos, double score) {
        AStarScore newScore = new AStarScore(dualIndexPos, score);
        scoresStack.add(newScore);
        scoresArchive.add(newScore);
    }

    public static AStarScore getLastestScore() {
        return scoresStack.pop();
    }

    public static boolean otherPathsAvailable() {
        return scoresStack.size() > 0;
    }
}
