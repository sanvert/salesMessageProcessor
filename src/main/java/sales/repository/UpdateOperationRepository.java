package sales.repository;

import sales.entity.ItemUpdateOperations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sanver.
 */
public class UpdateOperationRepository {
    private final Map<String, ItemUpdateOperations> itemCollection = new HashMap<>();

    private static UpdateOperationRepository updateOperationRepository;

    public static UpdateOperationRepository getInstance() {
        if(updateOperationRepository == null) {
            updateOperationRepository = new UpdateOperationRepository();
        }
        return updateOperationRepository;
    }

    public Map<String, ItemUpdateOperations> getItemCollection() {
        return itemCollection;
    }
}
