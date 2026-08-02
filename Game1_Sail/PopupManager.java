package Game1_Sail;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Centralizes user popup messages.
 */
public class PopupManager {
    private final Component parent;

    public PopupManager(Component parent) {
        this.parent = parent;
    }

    public void showValidationError(String message) {
        JOptionPane.showMessageDialog(parent, message, "Validation", JOptionPane.WARNING_MESSAGE);
    }

    public void showWrongGuess() {
        JOptionPane.showMessageDialog(parent, "Wrong number! Try the next one.", "Wrong Guess", JOptionPane.ERROR_MESSAGE);
    }

    public void showResult(int correctAnswers, int totalAnswers) {
        JOptionPane.showMessageDialog(parent, "Correct Guess\n\n" + correctAnswers + " / " + totalAnswers, "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
