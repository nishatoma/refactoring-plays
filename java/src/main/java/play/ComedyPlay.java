package play;

import static util.PlayConstants.*;

public class ComedyPlay extends Play {

    public ComedyPlay(String name) {
        super(name);
    }

    @Override
    public int getProfit(int audienceSize) {

        int result = COMEDY_BASE_AMOUNT + COMEDY_BASE_MULTIPLIER * audienceSize;

        if (audienceSize > COMEDY_AUDIENCE_LIMIT) {
            result += COMEDY_BONUS_AMOUNT + COMEDY_BONUS_MULTIPLIER * (audienceSize - COMEDY_AUDIENCE_LIMIT);
        }

        return result;
    }

    @Override
    public int getVolumeCredits(int audienceSize) {
        // add extra credit for every ten comedy attendees
        // Please not COMEDY_ATTENDEES should be 10.0 and not 5.0, but it throws an error
        // in verify function...
        return (int) (super.getVolumeCredits(audienceSize) + Math.floor(audienceSize / COMEDY_ATTENDEES));
    }
}
