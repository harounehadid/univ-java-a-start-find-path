public class GameManager {
    private String gameStatus = "idle";

    public GameManager(TwoDimVal fieldDims) {
        this.gameStatus = "started";

        // Set the playing field
        Field field = new Field(fieldDims);

        CustomFrame mainFrame = new CustomFrame("A Start Path Finding");
        mainFrame.addItem(field.getFieldPanel(), "center");

        Player player = new Player();

        field.spawnPlayer(player);

        mainFrame.finalizeFrameSetup();
    }

    public boolean isGameOver() {
        return this.gameStatus == "player failed" || this.gameStatus == "player succeeded";
    }

    // Getters
    public String getGameStatus() {
        return this.gameStatus;
    }
}
