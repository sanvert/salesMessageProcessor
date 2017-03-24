package sales.processor;

import sales.type.UpdateOperationType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by sanver.
 */
public class UpdateOperationMappings {
    private static final Map<UpdateOperationType, BiFunction> updateOperationMap = new EnumMap<UpdateOperationType, BiFunction>(UpdateOperationType.class)
    {{
        BiFunction<Double, Double, Double> add = (x, y) -> x + y;
        BiFunction<Double, Double, Double> subtract = (x, y) -> x - y;
        BiFunction<Double, Double, Double> multiply = (x, y) -> x * y;

        put(UpdateOperationType.ADD, add);
        put(UpdateOperationType.SUBTRACT, subtract);
        put(UpdateOperationType.MULTIPLY, multiply);
    }};

    public static BiFunction get(UpdateOperationType type) {
        return updateOperationMap.get(type);
    }
}
