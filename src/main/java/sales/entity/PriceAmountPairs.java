package sales.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sanver.
 */
public class PriceAmountPairs {
    private final Map<Double, Integer> map;

    public PriceAmountPairs() {
        map = new HashMap<>();
    }

    public void addPriceAmountPair(Double price, int amount) {
        map.computeIfPresent(price, (key, value) -> value + amount);
        map.computeIfAbsent(price, key -> amount);
    }

    public Map<Double, Integer> getMap() {
        return map;
    }
}
