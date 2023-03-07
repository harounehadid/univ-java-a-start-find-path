import Utils.GetBaseDirPath;

import javax.swing.*;
import java.util.ArrayList;

public class Player {
    private JLabel playerGUI;
    private int playerSize = 32;
    private TwoDimVal indexPos;
    private GameManager gameManager;

    public Player(GameManager gameManager) {
        this.handleGUI();
        this.gameManager = gameManager;
    }

    private void handleGUI() {
        this.playerGUI = new JLabel();

        String playerURL = GetBaseDirPath.root() + "/src/media/basic-player.png";

        ImageIcon image = new ImageIcon(playerURL);
        this.playerGUI.setSize(playerSize, playerSize);
        this.playerGUI.setIcon(image);

    }

    public void onSpawn(int x, int y) {
        this.indexPos = new TwoDimVal(x, y);
    }

    public TwoDimVal move() {
        ArrayList<TwoDimVal> validDestinations = new ArrayList<TwoDimVal>();

        int possibleMoveNum = 4;

        for (int i = 0; i < possibleMoveNum; i++) {
            int nextXi = -1;
            int nextYi = -1;

            if (i == 0) {
                nextXi = (int)this.indexPos.getX() - 1;
                nextYi = (int)this.indexPos.getY();
            }
            else if (i == 1) {
                nextXi = (int)this.indexPos.getX() + 1;
                nextYi = (int)this.indexPos.getY();
            }
            else if (i == 2) {
                nextXi = (int)this.indexPos.getX();
                nextYi = (int)this.indexPos.getY() - 1;
            }
            else if (i == 3) {
                nextXi = (int)this.indexPos.getX();
                nextYi = (int)this.indexPos.getY() + 1;
            }

            if (gameManager.checkMoveValidity(nextXi, nextYi)) {
                validDestinations.add(new TwoDimVal(nextXi, nextYi));
            }
        }

        TwoDimVal nextXYi = new TwoDimVal(-1, -1);
        int minCost = -1;

        for (TwoDimVal xyiDes : validDestinations) {
            int curCost = this.gameManager.checkCellCost((int)xyiDes.getX(), (int)xyiDes.getY());

            if (curCost >= 0) {
                if (minCost >= 0) {
                    if (curCost <= minCost) {
                        minCost = curCost;
                        nextXYi.setX(xyiDes.getX());
                        nextXYi.setY(xyiDes.getY());
                    }
                }
                else {
                    minCost = curCost;
                    nextXYi.setX(xyiDes.getX());
                    nextXYi.setY(xyiDes.getY());
                }
            }
        }

        if (nextXYi.getX() < 0 || nextXYi.getY() < 0) {
            nextXYi.setX(this.indexPos.getX());
            nextXYi.setY(this.indexPos.getY());
        }

        return nextXYi;
    }

    public boolean hitCell(TwoDimVal xyi) {
        return this.indexPos.getX() == xyi.getX() && this.indexPos.getY() == xyi.getY();
    }

    // Getters
    public JLabel getPlayerGUI() {
        return this.playerGUI;
    }
    public TwoDimVal getIndexPos() {
        return this.indexPos;
    }
}