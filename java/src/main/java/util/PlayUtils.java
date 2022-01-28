package util;

import java.text.NumberFormat;
import java.util.Locale;

import static util.PlayConstants.USD_FORMAT;

// Cannot inherit a utility class
public final class PlayUtils {

    // Cannot instantiate a utility class
    private PlayUtils() {
    }

    public static String formatUSD(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / USD_FORMAT);
    }
}
