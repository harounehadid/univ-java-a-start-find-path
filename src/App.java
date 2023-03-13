public class App {

    public static void main(String[] args) {
        int minColRowNum = 6;
        int colNum = (int)Math.floor(Math.random() * 10) + minColRowNum;
        int rowNum = (int)Math.floor(Math.random() * 10) + minColRowNum;

        new GameManager(new TwoDimVal(colNum, rowNum));
    }
}