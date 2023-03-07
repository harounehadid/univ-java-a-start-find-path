import javax.swing.*;
import java.awt.*;

public class Cell {
    private TwoDimVal dualIndex;
    private TwoDimVal pos;
    private String type;
    private int cost;
    private double spawnChance;
    private String backgroundLoc;
    private int borderLineThickness;
    private JLabel cellGUI;
    private static int cellImageSize = 64;


    public Cell(TwoDimVal dualIndex, String type) {
        this.dualIndex = new TwoDimVal(dualIndex.getX(), dualIndex.getY());
//        this.pos = new TwoDimVal(pos.getX(), pos.getY());

        // Call cell type to get specifications
        CellType cellData = CellTypes.getTypeData(type);

        this.type = cellData.getName();
        this.cost = cellData.getCost();
        this.spawnChance = cellData.getSpawnChance();
        this.backgroundLoc = cellData.getRepresentation();

        this.handleGUI();
    }

    private void handleGUI() {
        this.cellGUI = new JLabel();

        this.cellGUI.setHorizontalTextPosition(JLabel.CENTER);
        this.cellGUI.setVerticalTextPosition(JLabel.CENTER);

        this.borderLineThickness = 7;

        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(0x414141), this.borderLineThickness));

        this.treatLabel("image", this.backgroundLoc);
        this.treatLabel("text", this.type);
    }

    private void treatLabel(String itemType, String data) {
        if (itemType == "image") {
            ImageIcon image = new ImageIcon(data);
            this.cellGUI.setSize(Cell.cellImageSize, Cell.cellImageSize);
            this.cellGUI.setIcon(image);
        }
        else if (itemType == "text") {
            this.cellGUI.setText(data);
        }
    }

    public void spawnedOn() {
        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(0x982da6), this.borderLineThickness));
    }

    public void onCellEnter() {
        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(0x3eb700), this.borderLineThickness));
    }

    public void onCellExit() {
        this.cellGUI.setBorder(BorderFactory.createLineBorder(new Color(0xf28729), this.borderLineThickness));
    }

    public boolean identify(int x, int y) {
        return this.dualIndex.getX() == x && this.dualIndex.getY() == y;
    }

    // Getters
    public TwoDimVal getDualIndex() {
        return this.dualIndex;
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
    public JLabel getCellGUI() {
        return this.cellGUI;
    }
    public static int getImageSize() {
        return Cell.cellImageSize;
    }
}
