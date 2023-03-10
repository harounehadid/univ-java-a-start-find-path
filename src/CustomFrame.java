import Utils.GetBaseDirPath;

import javax.swing.*;
import java.awt.*;

public class CustomFrame extends JFrame {
    public CustomFrame(String frameTitle) {
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon(GetBaseDirPath.root() +
                "/src/media/find-path-algorithm-logo-GeeksforGeeks-image.png");

        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(0xf8f9f9));
    }

    public CustomFrame(String frameTitle, int width, int height) {
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);

        this.setSize(width, height);

        ImageIcon image = new ImageIcon(GetBaseDirPath.root() +
                "/src/media/find-path-algorithm-logo-GeeksforGeeks-image.png");

        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(0xf8f9f9));
    }

    public void addItem(JPanel panel, String section) {
        if (section == "center") {
            this.add(panel, BorderLayout.CENTER);
        }
        else if (section == "north") {
            this.add(panel, BorderLayout.NORTH);
        }
        else if (section == "east") {
            this.add(panel, BorderLayout.EAST);
        }
        else if (section == "south") {
            this.add(panel, BorderLayout.SOUTH);
        }
        else if (section == "west") {
            this.add(panel, BorderLayout.WEST);
        }
        else {
            System.out.println("(!) Unknown frame section check item added to CustomFrame (!)");
        }
    }

    public void addItem(JLabel label, String section) {
        if (section == "center") {
            this.add(label, BorderLayout.CENTER);
        }
        else if (section == "north") {
            this.add(label, BorderLayout.NORTH);
        }
        else if (section == "east") {
            this.add(label, BorderLayout.EAST);
        }
        else if (section == "south") {
            this.add(label, BorderLayout.SOUTH);
        }
        else if (section == "west") {
            this.add(label, BorderLayout.WEST);
        }
        else {
            System.out.println("(!) Unknown frame section check item added to CustomFrame (!)");
        }
    }

    public JLabel createLabel(String itemType, String data) {
        JLabel newLabel = new JLabel();

        newLabel.setHorizontalTextPosition(JLabel.CENTER);
        newLabel.setVerticalTextPosition(JLabel.CENTER);

        if (itemType == "image") {
            ImageIcon image = new ImageIcon(data);
            newLabel.setSize(image.getIconWidth(), image.getIconHeight());
            newLabel.setIcon(image);
        }
        else if (itemType == "text") {
            newLabel.setText(data);
        }

        return newLabel;
    }

    public void updateLabel(JLabel label, String itemType, String data) {
        if (label == null) {
            System.out.println("(!) Label DO NOT exist (!)");
            return;
        }

        if (itemType == "image") {
            ImageIcon image = new ImageIcon(data);
            label.setSize(image.getIconWidth(), image.getIconHeight());
            label.setIcon(image);
        }
        else if (itemType == "text") {
            label.setText(data);
        }
    }

    public void finalizeFrameSetup() {
        // These two lines of code should be left at the very bottom
        this.pack();
        this.setVisible(true);
    } /* This function is created to avoid any problems that can occure with GUI */
}
