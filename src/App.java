import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class App {

    public static void main(String[] args) {
        // Customize JFrame style
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.gray));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
        JFrame.setDefaultLookAndFeelDecorated(true);

        // The actual app
        int minColRowNum = 6;
        int colNum = (int)Math.floor(Math.random() * 10) + minColRowNum;
        int rowNum = (int)Math.floor(Math.random() * 10) + minColRowNum;

        new GameManager(new TwoDimVal(colNum, rowNum));
    }
}