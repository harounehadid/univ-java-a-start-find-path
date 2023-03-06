public class CellType {
    private String name;
    private int cost;
    private double spawnChance;
    private String representation;

    public CellType(String name, int cost, double spawnChance, String representation) {
        this.name = name;
        this.cost = cost;
        this.spawnChance = spawnChance;
        this.representation = representation;
    }

    // Getters >>>
    public String getName() {
        return this.name;
    }
    public int getCost() {
        return this.cost;
    }
    public double getSpawnChance() {
        return this.spawnChance;
    }
    public String getRepresentation() {
        return this.representation;
    }
}
