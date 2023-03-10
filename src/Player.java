import Utils.GetBaseDirPath;

import javax.swing.*;
import java.util.ArrayList;

public class Player {
    private JLabel playerGUI;
    private int playerSize = 32;
    private TwoDimVal indexPos;
    private GameManager gameManager;
    private boolean isBackTracking = false;

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

    public TwoDimVal setNextMove() {
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
        int minScore = -1;

        for (TwoDimVal xyiDes : validDestinations) {
            int curScore = this.gameManager.calculateScore((int)xyiDes.getX(), (int)xyiDes.getY());

            if (curScore >= 0) {
                if (minScore >= 0) {
                    if (curScore <= minScore) {
                        minScore = curScore;
                        nextXYi = xyiDes;
                    }
                }
                else {
                    minScore = curScore;
                    nextXYi = xyiDes;
                }
            }
        }

        if (nextXYi.getX() < 0 || nextXYi.getY() < 0) {
            nextXYi = this.indexPos;
        }

        return nextXYi;
    }

    public TwoDimVal move(TwoDimVal nextDestination) {
        TwoDimVal prevIndexPos = this.indexPos;

        this.indexPos = nextDestination;

        return prevIndexPos;
    }

    public boolean hitCell(TwoDimVal xyi) {
        return this.indexPos.getX() == xyi.getX() && this.indexPos.getY() == xyi.getY();
    }

    public boolean isStuck(TwoDimVal xyi) {
        if (this.isBackTracking) return false;
        return this.hitCell(xyi);
    }

    // Getters
    public JLabel getPlayerGUI() {
        return this.playerGUI;
    }
    public TwoDimVal getIndexPos() {
        return this.indexPos;
    }
}