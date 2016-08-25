package carcassonne.control.state;

import carcassonne.control.GameOptions;
import carcassonne.control.MainController;
import carcassonne.model.Round;
import carcassonne.model.grid.Grid;
import carcassonne.model.grid.GridDirection;
import carcassonne.view.main.MainGUI;
import carcassonne.view.main.menubar.Scoreboard;
import carcassonne.view.secondary.GameMessage;
import carcassonne.view.secondary.PlacementGUI;
import carcassonne.view.secondary.RotationGUI;

/**
 * Is the abstract state of the state machine.
 * @author Timur Saglam
 */
public abstract class AbstractControllerState {

    protected MainController controller;
    protected MainGUI mainGUI;
    protected RotationGUI rotationGUI;
    protected PlacementGUI placementGUI;
    protected Round round;
    protected Grid grid;
    protected Scoreboard scoreboard;
    protected GameOptions options;

    /**
     * Constructor of the abstract state, sets the controller from the parameter, registers the
     * state at the controller and calls the <code>entry()</code> method.
     * @param controller sets the Controller
     * @param mainGUI sets the MainGUI
     * @param rotationGUI sets the RotationGUI
     * @param placementGUI sets the PlacementGUI
     * @param scoreboard sets the Scoreboard
     */
    public AbstractControllerState(MainController controller, MainGUI mainGUI, RotationGUI rotationGUI, PlacementGUI placementGUI, Scoreboard scoreboard) {
        this.controller = controller;
        this.mainGUI = mainGUI;
        this.rotationGUI = rotationGUI;
        this.placementGUI = placementGUI;
        this.scoreboard = scoreboard;
        options = GameOptions.getInstance();
        controller.registerState(this);
    }

    /**
     * Starts new round with a specific amount of players.
     */
    public void abortGame() {
        GameMessage.showWarning("You can't abort a game right now.");
    }

    /**
     * Starts new round with a specific amount of players.
     * @param playerCount sets the amount of players.
     */
    public void newGame(int playerCount) {
        GameMessage.showWarning("You can't start a new game right now.");
    }

    /**
     * Method for the view to call if a user mans a tile with a Meeple.
     * @param position is the placement position.
     */
    public void placeMeeple(GridDirection position) {
        GameMessage.showWarning("You can't place meeple tile right now.");
    }

    /**
     * Method for the view to call if a user places a tile.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     */
    public void placeTile(int x, int y) {
        // GameMessage.showWarning("You can't place a tile right now.");
    }

    /**
     * Method for the view to call if the user wants to skip a round.
     */
    public void skip() {
        GameMessage.showWarning("You can't place a tile right now.");
    }

    /**
     * Updates the round and the grid object after a new round was started.
     * @param round sets the new round.
     * @param grid sets the new grid.
     */
    public void updateState(Round round, Grid grid) {
        this.round = round;
        this.grid = grid;
    }

    /**
     * Changes the state to a new state.
     * @param stateType is the type of the new state.
     */
    protected void changeState(Class<? extends AbstractControllerState> stateType) {
        exit();
        AbstractControllerState newState = controller.changeState(stateType);
        newState.entry();
    }

    /**
     * Entry method of the state.
     */
    protected abstract void entry();

    /**
     * Exit method of the state.
     */
    protected abstract void exit();

    /**
     * Updates the round and the grid of every state after a new round has been started.
     */
    protected void updateScores() {
        for (int player = 0; player < round.getPlayerCount(); player++) {
            scoreboard.update(player, round.getScore(player));
        }
    }
}
