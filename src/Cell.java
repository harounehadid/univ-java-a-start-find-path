import javax.swing.*;
import java.awt.*;

import Utils.GetBaseDirPath;

public class Cell {
    private TwoDimVal dualIndex;
    private String type;
    private int cost;
    private String backgroundLoc;
    private int borderLineThickness;
    private JLabel cellGUI;
    private static int cellImageSize = 64;
    private boolean spawnedOn = false;
    private boolean isVisited = false;
    private int defaultBorderColor = 0x414141;
    private int spawnedOnBorderColor = 0x982da6;
    private int onEnterBorderColor = 0x3eb700;
    private int onExitBorderColor = 0xf28729;

    public Cell(TwoDimVal dualIndex, String type) {
        this.dualIndex = new TwoDimVal(dualIndex.getX(), dualIndex.getY());

        // Call cell type to get specifications
        CellType cellData = CellTypes.getTypeData(type);

        this.type = cellData.getName();
        this.cost = cellData.getCost();
        this.backgroundLoc = cellData.getRepresentation();

        this.handleGUI();
    }

    private void handleGUI() {
        this.cellGUI = new JLabel();

        this.cellGUI.setHorizontalTextPosition(JLabel.CENTER);
        this.cellGUI.setVerticalTextPosition(JLabel.CENTER);

        this.borderLineThickness = 4;

        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(defaultBorderColor), this.borderLineThickness));

        this.treatLabel("image", this.backgroundLoc);
    }

    private void treatLabel(String itemType, String data) {
        if (itemType == "image") {
            this.cellGUI.setIcon(null);
            ImageIcon image = new ImageIcon(data);
            this.cellGUI.setSize(Cell.cellImageSize, Cell.cellImageSize);
            this.cellGUI.setIcon(image);
        }
        else if (itemType == "text") {
            this.cellGUI.setText(data);
        }
    }

    public void spawnedOn() {
        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(spawnedOnBorderColor), this.borderLineThickness));
        this.isVisited = true;
        this.spawnedOn = true;
    }

    public void onCellEnter() {
        this.isVisited = true;

        this.treatLabel("image", GetBaseDirPath.root() + "/src/media/" + this.type + "-cell-visited.png");

        if (this.spawnedOn) return;
        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(onEnterBorderColor), this.borderLineThickness));
    }

    public void onCellExit() {
        this.treatLabel("image", this.backgroundLoc);
        
        if (this.spawnedOn) return;
        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(onExitBorderColor), this.borderLineThickness));
    }

    public boolean identify(int x, int y) {
        return this.dualIndex.getX() == x && this.dualIndex.getY() == y;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    // Getters
    public TwoDimVal getDualIndex() {
        return this.dualIndex;
    }
    public String getType() {
        return this.type;
    }
    public int getCost() {
        return this.cost;
    }
    public JLabel getCellGUI() {
        return this.cellGUI;
    }
    public static int getImageSize() {
        return Cell.cellImageSize;
    }
}
