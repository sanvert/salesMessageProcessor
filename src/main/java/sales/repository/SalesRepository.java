package sales.repository;

import sales.entity.PriceAmountPairs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sanver.
 */
public class SalesRepository {
    private final Map<String, PriceAmountPairs> itemCollection = new HashMap<>();

    private static SalesRepository salesRepository;

    public static SalesRepository getInstance() {
        if(salesRepository == null) {
            salesRepository = new SalesRepository();
        }
        return salesRepository;
    }

    public Map<String, PriceAmountPairs> getItemCollection() {
        return itemCollection;
    }
}
