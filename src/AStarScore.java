public final class AStarScore {
    public static int calculateScore(TwoDimVal startPoint, TwoDimVal endPoint, int cost) {
        int score = 0;

        double deltaX = Math.abs(startPoint.getX() - endPoint.getX());
        double deltaY = Math.abs(startPoint.getY() - endPoint.getY());

        double heuristic = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        score = (int)Math.round(cost + heuristic);
        
        return score;
    }
}
