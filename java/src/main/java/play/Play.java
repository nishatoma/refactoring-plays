package play;

import static util.PlayConstants.TRAGEDY_AUDIENCE_LIMIT;

public abstract class Play {

    public String name;

    public Play(String name) {
        this.name = name;
    }

    public abstract int getProfit(int audienceSize);

    public int getVolumeCredits(int audienceSize) {
        int result = 0;
        return Math.max(audienceSize - TRAGEDY_AUDIENCE_LIMIT, result);
    }

}
