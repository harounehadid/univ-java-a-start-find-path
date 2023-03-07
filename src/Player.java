import Utils.GetBaseDirPath;

import javax.swing.*;

public class Player {
    private JLabel playerGUI;
    private int playerSize = 32;

    public Player() {
        this.handleGUI();
    }

    private void handleGUI() {
        this.playerGUI = new JLabel();

        String playerURL = GetBaseDirPath.root() + "/src/media/basic-player.png";

        ImageIcon image = new ImageIcon(playerURL);
        this.playerGUI.setSize(playerSize, playerSize);
        this.playerGUI.setIcon(image);

    }

    // Getters
    public JLabel getPlayerGUI() {
        return this.playerGUI;
    }
}