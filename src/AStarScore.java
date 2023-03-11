public class AStarScore {
    private TwoDimVal dualIndexPos;
    private double score;
    private boolean isUsed;
    
    public AStarScore(TwoDimVal dualIndexPos, double score) {
        this.dualIndexPos = dualIndexPos;
        this.score = score;
        this.isUsed = false;
    }

    public void use() {
        this.isUsed = true;
    }

    // Getters
    public TwoDimVal getDualIndexPos() {
        return this.dualIndexPos;
    }

    public double getScore() {
        return this.score;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }
}
