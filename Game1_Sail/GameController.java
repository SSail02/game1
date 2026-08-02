package Game1_Sail;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Controls game state, generated numbers, input checking, and score flow.
 */
public class GameController {
    private final GamePanel gamePanel;
    private final NumberGenerator numberGenerator;
    private final ShuffleManager shuffleManager;
    private final LevelManager levelManager;
    private final ScoreManager scoreManager;
    private final PopupManager popupManager;

    ArrayList<String> org_numbers;
    ArrayList<String> org_sequence;
    ArrayList<String> shuffle;
    int currentQuestion;
    int currentDigit;
    String currentInput;
    int correctAnswers;
    int wrongAnswers;

    public GameController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        numberGenerator = new NumberGenerator();
        shuffleManager = new ShuffleManager();
        levelManager = new LevelManager();
        scoreManager = new ScoreManager();
        popupManager = new PopupManager(gamePanel);
        org_numbers = new ArrayList<String>();
        org_sequence = shuffleManager.getOrgSequence();
        shuffle = new ArrayList<String>();
        currentInput = "";
    }

    public void startGame() {
        if (!gamePanel.hasValidSelections()) {
            popupManager.showValidationError("Please select Number Length and No of Digits before starting.");
            return;
        }
        int numberLength = gamePanel.getSelectedNumberLength();
        int numberCount = gamePanel.getSelectedNumberCount();
        resetGameState();
        gamePanel.setLevelText(levelManager.calculateLevel(numberLength, numberCount));
        gamePanel.setGameControlsEnabled(false);
        gamePanel.setPlayAgainEnabled(false);
        gamePanel.getButtonManager().setKeypadEnabled(false);
        generateNumbers(numberLength, numberCount);
        showNumbersWithDelay();
    }

    private void resetGameState() {
        org_numbers.clear();
        currentQuestion = 0;
        currentDigit = 0;
        currentInput = "";
        correctAnswers = 0;
        wrongAnswers = 0;
        scoreManager.reset();
        gamePanel.clearAnswers();
        gamePanel.setDisplayText("");
    }

    private void generateNumbers(int numberLength, int numberCount) {
        for (int i = 0; i < numberCount; i++) {
            org_numbers.add(numberGenerator.generateNumber(numberLength));
        }
    }

    private void showNumbersWithDelay() {
        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String number : org_numbers) {
                    SwingUtilities.invokeLater(() -> gamePanel.setDisplayText(number));
                    try {
                        Thread.sleep(Constants.DISPLAY_DELAY_MILLIS);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gamePanel.setDisplayText("");
                        gamePanel.getButtonManager().setKeypadEnabled(true);
                        shuffleDigits();
                    }
                });
            }
        });
        displayThread.start();
    }

   public void handleDigit(String digit) {
    if (currentQuestion >= org_numbers.size()) {
        return;
    }
    if (currentInput.length() >= org_numbers.get(currentQuestion).length()) {
        return;
    }
    currentInput += digit;
    currentDigit = currentInput.length();
    gamePanel.setDisplayText(currentInput);
    }

    public void handleSubmit() {
    if (currentQuestion >= org_numbers.size()) {
        return;
    }
    if (currentInput.length() == 0) {
        popupManager.showValidationError("Please enter a number.");
        return;
    }
    if (currentInput.length() != org_numbers.get(currentQuestion).length()) {
        popupManager.showValidationError("Incomplete number.");
        return;
    }
    checkCurrentAnswer();
    }

private void checkCurrentAnswer() {

    String expectedNumber = org_numbers.get(currentQuestion);

    if (expectedNumber.equals(currentInput)) {

        scoreManager.addCorrect();
        correctAnswers = scoreManager.getCorrectAnswers();

        gamePanel.addAnswer(expectedNumber, true);

    } else {

        scoreManager.addWrong();
        wrongAnswers = scoreManager.getWrongAnswers();

        gamePanel.addAnswer(expectedNumber, false);

        popupManager.showWrongGuess(currentInput);

    }

    currentQuestion++;
    currentDigit = 0;
    currentInput = "";

    gamePanel.setDisplayText("");

    if (currentQuestion == org_numbers.size()) {

        finishGame();

    } else {

        shuffleDigits();

    }
}

    private void shuffleDigits() {
        shuffle = shuffleManager.shuffleSequence();
        org_sequence = new ArrayList<String>(shuffle);
        gamePanel.getButtonManager().updateDigitLabels(org_sequence);
    }

    private void finishGame() {
        gamePanel.getButtonManager().setKeypadEnabled(false);
        gamePanel.setPlayAgainEnabled(true);
        popupManager.showResult(correctAnswers, org_numbers.size());
    }

    public void playAgain() {
        resetGameState();
        gamePanel.resetForNewGame();
    }
}
