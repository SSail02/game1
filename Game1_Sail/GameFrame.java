package Game1_Sail;

import javax.swing.JFrame;

/**
 * Main application frame for Mind Gym.
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        super(Constants.GAME_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(new GamePanel());
    }
}
