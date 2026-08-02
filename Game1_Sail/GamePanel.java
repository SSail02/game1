package Game1_Sail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Main game screen matching the requested sketch layout.
 */
public class GamePanel extends JPanel {
    private final JComboBox<String> numberLengthCombo;
    private final JComboBox<String> numberCountCombo;
    private final JLabel levelLabel;
    private final JTextField displayScreen;
    private final JTextArea answerScreen;
    private final JButton startButton;
    private final JButton playAgainButton;
    private final JButton exitButton;
    private final ButtonManager buttonManager;
    private final GameController controller;

    public GamePanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Constants.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));

        numberLengthCombo = new JComboBox<String>(Constants.SELECTION_VALUES);
        numberCountCombo = new JComboBox<String>(Constants.SELECTION_VALUES);
        levelLabel = new JLabel("YOU ARE PLAYING FOR : Select Level");
        displayScreen = new JTextField();
        answerScreen = new JTextArea();
        startButton = new JButton("START");
        playAgainButton = new JButton("PLAY AGAIN");
        exitButton = new JButton("EXIT");
        controller = new GameController(this);
        buttonManager = new ButtonManager(controller);

        buildTopPanel();
        buildCenterPanel();
        wireButtons();
        resetForNewGame();
    }

    private void buildTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(Constants.BACKGROUND);

        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 35, 5));
        selectionPanel.setBackground(Constants.BACKGROUND);
        selectionPanel.add(createLabel("Number Length"));
        selectionPanel.add(numberLengthCombo);
        selectionPanel.add(createLabel("No of Digits"));
        selectionPanel.add(numberCountCombo);

        levelLabel.setFont(Constants.LABEL_FONT);
        levelLabel.setForeground(Constants.LEVEL_FOREGROUND);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(selectionPanel, BorderLayout.NORTH);
        topPanel.add(levelLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
    }

    private void buildCenterPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Constants.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;

        displayScreen.setEditable(false);
        displayScreen.setHorizontalAlignment(SwingConstants.CENTER);
        displayScreen.setFont(Constants.SCREEN_FONT);
        displayScreen.setBackground(Constants.DISPLAY_BACKGROUND);
        displayScreen.setForeground(Constants.DISPLAY_FOREGROUND);
        displayScreen.setBorder(BorderFactory.createTitledBorder("Display Screen"));

        startButton.setFont(Constants.BUTTON_FONT);
        answerScreen.setEditable(false);
        answerScreen.setFont(Constants.ANSWER_FONT);
        answerScreen.setForeground(Constants.CORRECT_GREEN);
        answerScreen.setBorder(BorderFactory.createTitledBorder("Answer Screen"));
        answerScreen.setBackground(Color.WHITE);

        c.gridx = 0; c.gridy = 0; c.weightx = 0.68; c.weighty = 0.18;
        mainPanel.add(displayScreen, c);
        c.gridx = 1; c.weightx = 0.32;
        mainPanel.add(startButton, c);
        c.gridx = 0; c.gridy = 1; c.weightx = 0.68; c.weighty = 0.72;
        mainPanel.add(buttonManager.getKeypadPanel(), c);
        c.gridx = 1; c.weightx = 0.32;
        mainPanel.add(new JScrollPane(answerScreen), c);
        c.gridx = 1; c.gridy = 2; c.weighty = 0.10;
        mainPanel.add(createRightButtons(), c);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createRightButtons() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Constants.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridy = 0;
        playAgainButton.setFont(Constants.LABEL_FONT);
        panel.add(playAgainButton, c);
        c.gridy = 1;
        exitButton.setFont(Constants.LABEL_FONT);
        panel.add(exitButton, c);
        panel.setPreferredSize(new Dimension(260, 95));
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Constants.LABEL_FONT);
        return label;
    }

    private void wireButtons() {
        startButton.addActionListener(e -> controller.startGame());
        playAgainButton.addActionListener(e -> controller.playAgain());
        exitButton.addActionListener(e -> System.exit(0));
    }

    public int getSelectedNumberLength() { return Utils.selectedInteger(numberLengthCombo); }
    public int getSelectedNumberCount() { return Utils.selectedInteger(numberCountCombo); }
    public boolean hasValidSelections() { return Utils.hasValidSelection(numberLengthCombo) && Utils.hasValidSelection(numberCountCombo); }
    public void setLevelText(String level) { levelLabel.setText("YOU ARE PLAYING FOR : " + level); }
    public void setDisplayText(String text) { displayScreen.setText(text); }
    public void clearAnswers() { answerScreen.setText(""); }
    public void addAnswer(String number, boolean correct) {
    if (correct) {
        answerScreen.append("✔ " + number + "\n");
    } else {
        answerScreen.append("✘ " + number + "\n");
           }
    }
    public ButtonManager getButtonManager() { return buttonManager; }

    public void setGameControlsEnabled(boolean enabled) {
        numberLengthCombo.setEnabled(enabled);
        numberCountCombo.setEnabled(enabled);
        startButton.setEnabled(enabled);
    }

    public void setPlayAgainEnabled(boolean enabled) { playAgainButton.setEnabled(enabled); }

    public void resetForNewGame() {
        setDisplayText("");
        clearAnswers();
        setLevelText("Select Level");
        setGameControlsEnabled(true);
        setPlayAgainEnabled(false);
        buttonManager.setKeypadEnabled(false);
    }
}
