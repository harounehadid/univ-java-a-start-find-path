public class GameManager {
    private String gameStatus = "idle";
    private Field gameField;
    private CustomFrame gameFrame;
    private Player player;
    private String failState = "player failed";
    private String successState = "player succeeded";
    private TwoDimVal goalxyi;

    public GameManager(TwoDimVal fieldDims) {
        this.gameStatus = "started";

        // Set the playing field
        this.gameField = new Field(fieldDims);

        // Handle GUI
        this.gameFrame = new CustomFrame("A Start Path Finding");
        this.gameFrame.addItem(this.gameField.getFieldPanel(), "center");
        this.gameFrame.finalizeFrameSetup();
        
        // Launch simulation
        this.launch();
    }

    private void launch() {
        this.player = new Player(this);
        this.gameField.spawnPlayer(this.player);

        for (Cell curCell : this.gameField.getCellsArr()) {
            if (curCell.getType() == "goal") {
                this.goalxyi = new TwoDimVal(curCell.getDualIndex().getX(), curCell.getDualIndex().getY());
            }
        }

        while (!this.isGameOver()) {
            TwoDimVal nextPlayerDestination = this.player.setNextMove();
            TwoDimVal prevIndexPos = this.player.move(nextPlayerDestination);

            // Check if player stayed at the same cell
            if (this.player.isStuck(prevIndexPos) || (nextPlayerDestination.getX() < 0 && nextPlayerDestination.getY() < 0)) {
                setStatusToFail();
                break;
            }

            // Check if player hit the goal
            if (this.player.hitCell(this.goalxyi)) setStatusToSuccess();

            this.gameField.detectPlayerMovement(this.player.getIndexPos(), prevIndexPos);

            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println(this.gameStatus);
    }

    public boolean checkMoveValidity(int x, int y) {
        return this.gameField.inBoundary(x, y) && !this.gameField.cellIsVisited(x, y);
    }

    public int calculateScore(int x, int y) {
        int cost = -1;

        for (Cell curCell : this.gameField.getCellsArr()) {
            if (curCell.identify(x, y) && curCell.getType() != "wall") {
                cost = AStarScore.calculateScore(curCell.getDualIndex(), this.goalxyi, curCell.getCost());
                break;
            }
        }

        return cost;
    }

    private boolean isGameOver() {
        return this.gameStatus == this.failState || this.gameStatus == this.successState;
    }

    private void setStatusToFail() {
        this.gameStatus = this.failState;
    }

    private void setStatusToSuccess() {
        this.gameStatus = this.successState;
    }

    // Getters
    public String getGameStatus() {
        return this.gameStatus;
    }
    // Setters
    public void setGameStatus(String status) {
        this.gameStatus = status;
    }
}
