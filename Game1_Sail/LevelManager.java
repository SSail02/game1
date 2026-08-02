package Game1_Sail;

/**
 * Calculates the level name from number length multiplied by number count.
 */
public class LevelManager {
    public String calculateLevel(int numberLength, int numberCount) {
        int value = numberLength * numberCount;
        if (value >= 16 && value <= 21) {
            return "Silver";
        }
        if (value >= 22 && value <= 27) {
            return "Gold";
        }
        if (value >= 28 && value <= 32) {
            return "Platinum";
        }
        if (value >= 33 && value <= 36) {
            return "Diamond";
        }
        return "Practice";
    }
}
