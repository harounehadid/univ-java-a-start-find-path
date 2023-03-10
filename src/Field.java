import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Field {
    private TwoDimVal dims;
    private int cellsNum;
    private ArrayList<Cell> cellsArr;
    private JPanel fieldPanel;
    private int panelWidth;
    private int panelHeight;
    private int panelGap = 0;
    private ArrayList<Integer> cellUnitsArr;
    private int goalUnitsIndex;

    public Field(TwoDimVal dims) {
        this.dims = new TwoDimVal(dims.getX(), dims.getY());
        this.cellsNum = (int)(this.dims.getX() * this.dims.getY());

        cellsArr = new ArrayList<Cell>();
        this.cellUnitsArr = new ArrayList<Integer>();

        this.handleGUI();
        this.setCellUnits();
        this.generateCells();
    }

    private void handleGUI() {
        this.panelWidth = (Cell.getImageSize() + panelGap) * (int)this.dims.getX();
        this.panelHeight = (Cell.getImageSize() + panelGap) * (int)this.dims.getY();

        this.fieldPanel = new JPanel();
        this.fieldPanel.setSize(this.panelWidth, this.panelHeight);
        this.fieldPanel.setLayout(new GridLayout(0, (int)this.dims.getX(), panelGap, panelGap));
    }

    private void generateCells() {
        for (int i = 0; i < this.dims.getY(); i++) {
            for (int j = 0; j < this.dims.getX(); j++) {
                String type = this.selectCellType();
                Cell newCell = new Cell(new TwoDimVal(j, i), type);
                this.cellsArr.add(newCell);
                this.addItem(newCell.getCellGUI());
            }
        }
    }

    private void setCellUnits() {
        CellType[] cellTypesArr = CellTypes.getCellTypesArr();

        for (int i = 0; i < cellTypesArr.length; i++) {

            if (cellTypesArr[i].getSpawnChance() == 1) {
                this.cellUnitsArr.add(this.cellsNum);
            }
            else if (cellTypesArr[i].getSpawnChance() == 0) {
                // The value zero means the number of units is inserted manually
                this.cellUnitsArr.add(1);
            }
            else if (cellTypesArr[i].getSpawnChance() > 0 && cellTypesArr[i].getSpawnChance() < 1) {
                int unitsNum = (int)Math.round(cellTypesArr[i].getSpawnChance() * this.cellsNum);
                this.cellUnitsArr.add(unitsNum);
            }
            else {
                System.out.println("(!) Something wrong with your assigned spawn chances (!)");
            }

            if (cellTypesArr[i].getName() == "goal") {
                this.goalUnitsIndex = i;
            }
        }
    }

    private String selectCellType() {
        String type = "";

        int targetedIndex;
        int nonEmptyUnitsNum = 0;

        for (Integer unitNum: this.cellUnitsArr) {
            nonEmptyUnitsNum += unitNum;
        }

        // This for optimization purposes
        if (nonEmptyUnitsNum == 0) {
            type = CellTypes.getTypeData("empty").getName();
            targetedIndex = 0;
            type = CellTypes.getCellTypesArr()[targetedIndex].getName();
            this.cellUnitsArr.set(targetedIndex, this.cellUnitsArr.get(targetedIndex) - 1);
        }
        else if (nonEmptyUnitsNum == 1 && this.cellUnitsArr.get(this.goalUnitsIndex) != 0) {
            targetedIndex = this.goalUnitsIndex;
            type = CellTypes.getCellTypesArr()[targetedIndex].getName();
            this.cellUnitsArr.set(targetedIndex, this.cellUnitsArr.get(targetedIndex) - 1);
        } /* Insure there all goals spawn in the field */

        // Rest of cell types
        while (type == "") {
            targetedIndex = (int)Math.floor(Math.random() * this.cellUnitsArr.size());

            if (this.cellUnitsArr.get(targetedIndex) == 0) continue;

            type = CellTypes.getCellTypesArr()[targetedIndex].getName();
            this.cellUnitsArr.set(targetedIndex, this.cellUnitsArr.get(targetedIndex) - 1);
        }

        return type;
    }

    private void addItem(JLabel label) {
        this.fieldPanel.add(label);
    }

    public void spawnPlayer(Player player) {
        boolean spawningCellFound = false;
        int randomXPos;
        int randomYPos;

        do {
            randomXPos = (int)Math.floor(Math.random() * this.dims.getX());
            randomYPos = (int)Math.floor(Math.random() * this.dims.getY());

            for (Cell curCell : this.cellsArr) {
                if (curCell.identify(randomXPos, randomYPos)) {
                    if (curCell.getType() == "wall" || curCell.getType() == "goal") continue;
                    curCell.spawnedOn();
                    spawningCellFound = true;
                    break;
                }
            }

        } while (!spawningCellFound);

        player.onSpawn(randomXPos, randomYPos);
    }

    public void detectPlayerMovement(TwoDimVal curPos, TwoDimVal destinedPos) {
        for (Cell curCell : this.cellsArr) {
            if (curCell.identify((int)curPos.getX(), (int)curPos.getY())) {
                curCell.onCellEnter();
            }

            if (curCell.identify((int)destinedPos.getX(), (int)destinedPos.getY())) {
                curCell.onCellExit();
            }
        }
    }

    public boolean inBoundary(int x, int y) {
        return (
            x >= 0 &&
            x < this.dims.getX() &&
            y >= 0 &&
            y < this.dims.getY()
        );
    }

    public boolean cellIsVisited(int x, int y) {
        boolean isVisited = false;

        for (Cell curCell : this.cellsArr) {
            if (curCell.identify(x, y)) {
                isVisited = curCell.isVisited();
            }
        }

        return isVisited;
    }

    // Getters
    public TwoDimVal getDims() {
        return this.dims;
    }
    public int getCellsNum() {
        return this.cellsNum;
    }
    public ArrayList<Cell> getCellsArr() {
        return this.cellsArr;
    }
    public JPanel getFieldPanel() {
        return this.fieldPanel;
    }
}
