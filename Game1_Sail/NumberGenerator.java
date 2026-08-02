package Game1_Sail;

import java.util.Random;

/**
 * Generates numbers one digit at a time using java.util.Random.
 */
public class NumberGenerator {
    private final Random random;

    public NumberGenerator() {
        random = new Random();
    }

    public String generateNumber(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        return builder.toString();
    }
}
