package Game1_Sail;

import javax.swing.SwingUtilities;

/**
 * Application entry point. Starts the Swing UI on the Event Dispatch Thread.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }
}
