package carcassonne.view.secondary;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import carcassonne.control.MainController;
import carcassonne.model.Round;

/**
 * A class for the game statistics GUI.
 * @author Timur Saglam
 */
public class GameStatisticsGUI {

    protected MainController controller;
    private JFrame frame;
    private JButton buttonClose;
    private final JTable table;

    /**
     * Creates the GUI and extracts the data from the current round.
     * @param controller is the game controller.
     * @param round is the current round.
     */
    public GameStatisticsGUI(MainController controller, Round round) {
        this.controller = controller;
        table = new JTable(new GameStatisticsModel(round));
        buildButtonClose();
        buildFrame();
    }

    /**
     * Hides and disposes the GUI.
     */
    public void closeGUI() {
        frame.setVisible(false);
        frame.dispose();
    }

    private void buildButtonClose() {
        buttonClose = new JButton("Close");
        buttonClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.requestSkip();
            }
        });
    }

    private void buildFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
        frame.add(table, BorderLayout.CENTER);
        frame.add(buttonClose, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}
