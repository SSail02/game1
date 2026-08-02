package Game1_Sail;

/**
 * Stores correct and wrong answer counts.
 */
public class ScoreManager {
    private int correctAnswers;
    private int wrongAnswers;

    public void reset() {
        correctAnswers = 0;
        wrongAnswers = 0;
    }

    public void addCorrect() {
        correctAnswers++;
    }

    public void addWrong() {
        wrongAnswers++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }
}
