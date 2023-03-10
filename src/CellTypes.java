import Utils.GetBaseDirPath;

public final class CellTypes {
    private static CellType empty = new CellType(
            "empty",
            1,
            1,
            GetBaseDirPath.root() + "/src/media/empty-cell.png"
    );
    private static CellType grass = new CellType(
            "grass",
            2,
            0.2,
            GetBaseDirPath.root() + "/src/media/grass-cell.png"
    );
    private static CellType sand = new CellType(
            "sand",
            4,
            0.05,
            GetBaseDirPath.root() + "/src/media/sand-cell.png"
    );
    private static CellType water = new CellType(
            "water",
            5,
            0.02,
            GetBaseDirPath.root() + "/src/media/water-cell.png"
    );
    private static CellType wall = new CellType(
            "wall",
            1000,
            0.1,
            GetBaseDirPath.root() + "/src/media/wall-cell.png"
    ); /* Wall celltype has very high cost to prevent player from selecting it */
    private static CellType goal = new CellType(
            "goal",
            0,
            0,
            GetBaseDirPath.root() + "/src/media/goal-cell.png"
    ); /* SpawnChance equals zero means manual insertion */
    private static CellType[] cellTypesArr = {
            CellTypes.empty,
            CellTypes.grass,
            CellTypes.sand,
            CellTypes.water,
            CellTypes.wall,
            CellTypes.goal
    };

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
                System.out.println("(!) Unknown type please check inputs (!)");
                selectedType = null;
                break;
        }

        return selectedType;
    }

    public static CellType[] getCellTypesArr() {
        return CellTypes.cellTypesArr;
    }
}
