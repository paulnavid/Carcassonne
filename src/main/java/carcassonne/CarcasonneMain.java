package carcassonne;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import carcassonne.control.GameOptions;
import carcassonne.control.MainController;
import carcassonne.view.secondary.GameMessage;

/**
 * Carcassonne main class for starting the game itself.
 * @author Timur Saglam
 */
public final class CarcasonneMain {
    /**
     * Main method for the Carcassonne game.
     * @param args are not used.
     */
    public static void main(String[] args) {
        if (!GameOptions.getInstance().operatingSystemName.startsWith("Mac")) {
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception exception) {
                GameMessage.showError("Could not use Nimbus LookAndFeel. Using default look and feel instead (" + exception.getMessage() + ").");
            }
        }
        new MainController();
    }

    private CarcasonneMain() {
    }
}
