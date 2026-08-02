package Game1_Sail;

import java.util.ArrayList;
import java.util.Random;

/**
 * Maintains and shuffles keypad digit order with ArrayList-based logic.
 */
public class ShuffleManager {
    private final Random random;
    private final ArrayList<String> org_sequence;
    private ArrayList<String> shuffle;

    public ShuffleManager() {
        random = new Random();
        org_sequence = new ArrayList<String>();
        shuffle = new ArrayList<String>();
        for (int i = 0; i <= 9; i++) {
            org_sequence.add(String.valueOf(i));
        }
    }

    public ArrayList<String> getOrgSequence() {
        return new ArrayList<String>(org_sequence);
    }

    public ArrayList<String> shuffleSequence() {
        shuffle = new ArrayList<String>();
        while (shuffle.size() != 10) {
            int randomIndex = random.nextInt(10);
            String value = org_sequence.get(randomIndex);
            if (!shuffle.contains(value)) {
                shuffle.add(value);
            }
        }
        org_sequence.clear();
        org_sequence.addAll(shuffle);
        return getOrgSequence();
    }
}
