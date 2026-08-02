package Game1_Sail;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Builds and updates the shuffled digit keypad.
 */
public class ButtonManager {
    private final ArrayList<JButton> digitButtons;
    private final JButton submitButton;
    private final JPanel keypadPanel;

    public ButtonManager(GameController controller) {
        digitButtons = new ArrayList<JButton>();
        submitButton = new JButton("SUBMIT");
        keypadPanel = new JPanel(new GridBagLayout());
        keypadPanel.setBackground(Constants.PANEL_BACKGROUND);
        createButtons(controller);
    }

    private void createButtons(GameController controller) {
        for (int i = 0; i <= 9; i++) {
            final JButton button = new JButton(String.valueOf(i));
            button.setFont(Constants.BUTTON_FONT);
            button.addActionListener(e -> controller.handleDigit(button.getText()));
            digitButtons.add(button);
        }
        submitButton.setFont(Constants.BUTTON_FONT);
        submitButton.addActionListener(e -> controller.handleSubmit());
        layoutButtons();
        setKeypadEnabled(false);
    }

    private void layoutButtons() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(9, 9, 9, 9);
        c.weightx = 1.0;
        c.weighty = 1.0;
        for (int i = 0; i < 9; i++) {
            c.gridx = i % 3;
            c.gridy = i / 3;
            keypadPanel.add(digitButtons.get(i + 1), c);
        }
        c.gridx = 0;
        c.gridy = 3;
        keypadPanel.add(digitButtons.get(0), c);
        c.gridx = 1;
        c.gridwidth = 2;
        keypadPanel.add(submitButton, c);
    }

    public JPanel getKeypadPanel() {
        return keypadPanel;
    }

    public void updateDigitLabels(ArrayList<String> sequence) {
        for (int i = 0; i < digitButtons.size(); i++) {
            digitButtons.get(i).setText(sequence.get(i));
        }
    }

    public void setKeypadEnabled(boolean enabled) {
        for (JButton button : digitButtons) {
            button.setEnabled(enabled);
        }
        submitButton.setEnabled(enabled);
    }
}
