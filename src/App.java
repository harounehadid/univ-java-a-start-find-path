public class App {

    public static void main(String[] args) {
        int minColRowNum = 2;
        int colNum = 6;
        int rowNum = 4;

        new GameManager(new TwoDimVal(colNum, rowNum));
    }
}