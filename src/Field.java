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
    private int panelGap = 5;

    public Field(TwoDimVal dims) {
        this.dims = new TwoDimVal(dims.getX(), dims.getY());
        this.cellsNum = (int)(this.dims.getX() * this.dims.getY());

        cellsArr = new ArrayList<Cell>();

        this.handleGUI();

        this.generateCells();
    }

    private void handleGUI() {
        this.panelWidth = (Cell.getImageSize() + panelGap) * (int)this.dims.getX();
        this.panelHeight = (Cell.getImageSize() + panelGap) * (int)this.dims.getY();

        this.fieldPanel = new JPanel();
        this.fieldPanel.setSize(this.panelWidth, this.panelHeight);
        this.fieldPanel.setLayout(new GridLayout(0, (int)this.dims.getX(), 5, 5));
    }

    private void generateCells() {
        for (int i = 0; i < this.dims.getY(); i++) {
            for (int j = 0; j < this.dims.getX(); j++) {
                Cell newCell = new Cell(new TwoDimVal(j, i), "grass");
                this.cellsArr.add(newCell);
                this.addItem(newCell.getCellGUI());
            }
        }
    }

    private void addItem(JLabel label) {
        this.fieldPanel.add(label);
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
