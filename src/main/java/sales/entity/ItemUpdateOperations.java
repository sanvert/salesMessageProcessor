package sales.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanver.
 */
public class ItemUpdateOperations {
    private final List<UpdateOperation> list;

    public ItemUpdateOperations() {
        this.list = new ArrayList<UpdateOperation>();
    }

    public List<UpdateOperation> getList() {
        return list;
    }

}
