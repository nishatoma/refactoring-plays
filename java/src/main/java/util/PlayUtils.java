package util;

import com.theatricalplayers.Invoice;
import com.theatricalplayers.Performance;
import play.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static util.PlayConstants.USD_FORMAT;

// Cannot inherit a utility class
public final class PlayUtils {

    // Cannot instantiate a utility class
    private PlayUtils() {
    }

    public static String formatUSD(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / USD_FORMAT);
    }

    public static Play getPlay(Map<String, ? extends Play> plays, Performance perf) {
        return plays.get(perf.getPlayID());
    }

    public static int getTotalAmount(Map<String, ? extends Play> plays, Invoice invoice) {
        int result = 0;

        for (Performance perf : invoice.getPerformances()) {
            result += getPlay(plays, perf).getProfit(perf.getAudience());
        }

        return result;
    }

    public static int getTotalVolume(Map<String, ? extends Play> plays, Invoice invoice) {
        int result = 0;

        for (Performance perf : invoice.getPerformances()) {
            // add volume credits
            result += getPlay(plays, perf).getVolumeCredits(perf.getAudience());
        }

        return result;
    }
}
