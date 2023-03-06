import java.util.ArrayList;

public class Field {
    private TwoDimVal dims;
    private int cellsNum;
    private ArrayList<Cell> cellsArr;
    public Field(TwoDimVal dims) {
        this.dims = new TwoDimVal(dims.getX(), dims.getY());
        this.cellsNum = (int)(this.dims.getX() * this.dims.getY());

        cellsArr = new ArrayList<Cell>();
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
}
