package carcassonne.control.state;

import carcassonne.control.MainController;
import carcassonne.model.grid.GridDirection;
import carcassonne.model.tile.Tile;
import carcassonne.view.main.MainGUI;
import carcassonne.view.main.menubar.Scoreboard;
import carcassonne.view.secondary.GameMessage;
import carcassonne.view.secondary.PlacementGUI;
import carcassonne.view.secondary.RotationGUI;

/**
 * The specific state when a Tile can be placed.
 * @author Timur Saglam
 */
public class StatePlacing extends AbstractControllerState {

    /**
     * Constructor of the state.
     * @param controller sets the controller.
     * @param mainGUI sets the MainGUI
     * @param rotationGUI sets the RotationGUI
     * @param placementGUI sets the PlacementGUI
     * @param scoreboard sets the Scoreboard
     */
    public StatePlacing(MainController controller, MainGUI mainGUI, RotationGUI rotationGUI, PlacementGUI placementGUI, Scoreboard scoreboard) {
        super(controller, mainGUI, rotationGUI, placementGUI, scoreboard);
    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#abortGame()
     */
    @Override
    public void abortGame() {
        changeState(StateGameOver.class);
    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#newGame()
     */
    @Override
    public void newGame(int playerCount) {
        GameMessage.showWarning("Abort the current game before starting a new one.");

    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#placeMeeple()
     */
    @Override
    public void placeMeeple(GridDirection position) {
        throw new IllegalStateException("Placing meeples in StatePlacing is not allowed.");
    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#placeTile()
     */
    @Override
    public void placeTile(int x, int y) {
        Tile tile = rotationGUI.getTile();
        if (grid.place(x, y, tile)) {
            round.updateCurrentTile(tile);
            mainGUI.set(tile, x, y);
            changeState(StateManning.class);
        }
    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#skip()
     */
    @Override
    public void skip() {
        round.nextTurn();
        entry();
    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#entry()
     */
    @Override
    protected void entry() {
        if (round.isOver()) {
            changeState(StateGameOver.class);
        } else {
            rotationGUI.setTile(round.getCurrentTile(), round.getActivePlayer().getNumber());
            updateStackSize();
        }
    }

    /**
     * @see carcassonne.control.state.AbstractControllerState#exit()
     */
    @Override
    protected void exit() {
        rotationGUI.disableFrame();
    }

}
