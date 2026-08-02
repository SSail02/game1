package Game1_Sail;

import javax.swing.JComboBox;

/**
 * Small utility helpers used by the UI and controller.
 */
public final class Utils {
    private Utils() {
    }

    public static boolean hasValidSelection(JComboBox<String> comboBox) {
        return comboBox.getSelectedIndex() > 0;
    }

    public static int selectedInteger(JComboBox<String> comboBox) {
        return Integer.parseInt((String) comboBox.getSelectedItem());
    }
}
