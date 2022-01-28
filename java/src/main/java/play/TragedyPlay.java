package play;

import static util.PlayConstants.*;

public class TragedyPlay extends Play {

    public TragedyPlay(String name) {
        super(name);
    }

    @Override
    public int getProfit(int audienceSize) {
        var result = 0;

        result = TRAGEDY_BASE_AMOUNT;

        if (audienceSize > TRAGEDY_AUDIENCE_LIMIT) {
            result += TRAGEDY_BONUS_AMOUNT * (audienceSize - TRAGEDY_AUDIENCE_LIMIT);
        }

        return result;
    }
}
