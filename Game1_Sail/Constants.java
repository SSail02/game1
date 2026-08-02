package Game1_Sail;

import java.awt.Color;
import java.awt.Font;

/**
 * Shared constants for the Mind Gym Swing application.
 */
public final class Constants {
    public static final String GAME_NAME = "Mind Gym";
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 700;
    public static final int DISPLAY_DELAY_MILLIS = 3000;

    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 28);
    public static final Font LABEL_FONT = new Font("SansSerif", Font.BOLD, 18);
    public static final Font SCREEN_FONT = new Font("Monospaced", Font.BOLD, 42);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 24);
    public static final Font ANSWER_FONT = new Font("SansSerif", Font.BOLD, 20);

    public static final Color BACKGROUND = new Color(238, 244, 252);
    public static final Color PANEL_BACKGROUND = new Color(222, 232, 246);
    public static final Color DISPLAY_BACKGROUND = Color.WHITE;
    public static final Color DISPLAY_FOREGROUND = new Color(20, 40, 70);
    public static final Color LEVEL_FOREGROUND = new Color(90, 60, 150);
    public static final Color CORRECT_GREEN = new Color(0, 135, 70);

    public static final String[] SELECTION_VALUES = {"Select", "4", "5", "6"};

    private Constants() {
    }
}
