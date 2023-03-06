public abstract class CellTypes {
    private static CellType empty = new CellType(
            "empty",
            1,
            1,
            "./media/empty-cell.svg"
    );
    private static CellType grass = new CellType(
            "grass",
            2,
            0.2,
            "./media/grass-cell.svg"
    );
    private static CellType sand = new CellType(
            "sand",
            4,
            0.05,
            "./media/sand-cell.svg"
    );
    private static CellType water = new CellType(
            "water",
            4,
            0.02,
            "./media/water-cell.svg"
    );
    private static CellType wall = new CellType(
            "wall",
            1000,
            0.1,
            "./media/wall-cell.svg"
    ); /* Wall celltype has very high cost to prevent player from selecting it */
    private static CellType goal = new CellType(
            "goal",
            0,
            0,
            "./media/goal-cell.svg"
    ); /* SpawnChance equals zero means manual insertion */

    public static CellType getTypeData(String name) {
        CellType selectedType;

        switch (name) {
            case "empty":
                selectedType = CellTypes.empty;
                break;
            case "grass":
                selectedType = CellTypes.grass;
                break;
            case "sand":
                selectedType = CellTypes.sand;
                break;
            case "water":
                selectedType = CellTypes.water;
                break;
            case "wall":
                selectedType = CellTypes.wall;
                break;
            case "goal":
                selectedType = CellTypes.goal;
                break;
            default:
                selectedType = null;
                break;
        }

        return selectedType;
    }
}
