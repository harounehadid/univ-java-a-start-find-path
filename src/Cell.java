public class Cell {
    private TwoDimVal dualIndex;
    private String id;
    private TwoDimVal pos;
    private String type;
    private int cost;
    private String backgroundLoc;

    public Cell(TwoDimVal dualIndex, TwoDimVal pos, String type, int cost, String backgroundLoc) {
        this.dualIndex = new TwoDimVal(dualIndex.getX(), dualIndex.getY());
        this.id = "x" + this.dualIndex.getX() + "y" + this.dualIndex.getY();
        this.pos = new TwoDimVal(pos.getX(), pos.getY());
        this.type = type;
        this.cost = cost;
        this.backgroundLoc = backgroundLoc;

        this.handleGUI();
    }

    private void handleGUI() {

    }

    // Getters
    public TwoDimVal getDualIndex() {
        return this.dualIndex;
    }
    public String getId() {
        return this.id;
    }
    public TwoDimVal getPos() {
        return this.pos;
    }
    public String getType() {
        return this.type;
    }
    public int getCost() {
        return this.cost;
    }
}
