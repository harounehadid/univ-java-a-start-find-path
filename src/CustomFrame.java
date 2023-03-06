import Utils.GetBaseDirPath;

import javax.swing.*;
import java.awt.*;

public class CustomFrame extends JFrame {
    public CustomFrame() {
        this.instantiateWindow();

        this.addLabel("image", CellTypes.getTypeData("grass").getRepresentation());
        this.addLabel("text", CellTypes.getTypeData("grass").getRepresentation());

        this.setVisible(true);
    }

    private void instantiateWindow() {
        this.setTitle("A* Find The Path");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280, 720);

        ImageIcon image = new ImageIcon(GetBaseDirPath.root() +
                "/src/media/find-path-algorithm-logo-GeeksforGeeks-image.png");

        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(0xf8f9f9));
    }

    private void addLabel(String type, String data) {
        JLabel newLabel = new JLabel();

        if (type == "image") {
            newLabel.setIcon(new ImageIcon(data));
            this.add(newLabel);
        }
        else if (type == "text") {
            newLabel.setText(data);
            this.add(newLabel);
        }
    }
}
