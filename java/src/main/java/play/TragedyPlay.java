package play;

import static util.PlayConstants.*;

public class TragedyPlay extends Play {

    public TragedyPlay(String name) {
        super(name);
    }

    @Override
    public int getProfit(int audienceSize) {
        if (audienceSize > TRAGEDY_AUDIENCE_LIMIT) {
            return TRAGEDY_BASE_AMOUNT + TRAGEDY_BONUS_AMOUNT * (audienceSize - TRAGEDY_AUDIENCE_LIMIT);
        }
        return TRAGEDY_BASE_AMOUNT;
    }
}
