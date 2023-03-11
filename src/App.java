public class App {

    public static void main(String[] args) {
        int minColRowNum = 2;
        int colNum = 12;
        int rowNum = 8;

        new GameManager(new TwoDimVal(colNum, rowNum));
    }
}