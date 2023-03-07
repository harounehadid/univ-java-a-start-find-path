import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Cell {
    private TwoDimVal dualIndex;
    private String id;
    private TwoDimVal pos;
    private String type;
    private int cost;
    private double spawnChance;
    private String backgroundLoc;
    private Border borderLine;
    private JLabel cellGUI;
    private static int cellImageSize = 64;


    public Cell(TwoDimVal dualIndex, String type) {
        this.dualIndex = new TwoDimVal(dualIndex.getX(), dualIndex.getY());
        this.id = "x" + this.dualIndex.getX() + "y" + this.dualIndex.getY();
//        this.pos = new TwoDimVal(pos.getX(), pos.getY());

        // Call cell type to get specifications
        CellType cellData = CellTypes.getTypeData(type);

        this.type = cellData.getName();
        this.cost = cellData.getCost();
        this.spawnChance = cellData.getSpawnChance();
        this.backgroundLoc = cellData.getRepresentation();

        this.borderLine = BorderFactory.createLineBorder(Color.black, 3);

        this.handleGUI("image", this.backgroundLoc);
    }

    private void handleGUI(String itemType, String data) {
        this.cellGUI = new JLabel();

        this.cellGUI.setHorizontalTextPosition(JLabel.CENTER);
        this.cellGUI.setVerticalTextPosition(JLabel.CENTER);

        this.cellGUI.setBorder(this.borderLine);

        if (itemType == "image") {
            ImageIcon image = new ImageIcon(data);
            this.cellGUI.setSize(Cell.cellImageSize, Cell.cellImageSize);
            this.cellGUI.setIcon(image);
        }
        else if (itemType == "text") {
            this.cellGUI.setText(data);
        }
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
    public JLabel getCellGUI() {
        return this.cellGUI;
    }
    public static int getImageSize() {
        return Cell.cellImageSize;
    }
}
