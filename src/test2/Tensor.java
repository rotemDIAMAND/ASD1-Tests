package test2;
import java.util.*;

public class Tensor<T> implements Iterable<T> {
    private final Map<List<Integer>, T> data = new LinkedHashMap<>();

    // שינוי ל-Integer... מאפשר גמישות מקסימלית
    public void put(T value, Integer... indices) {
        data.put(Arrays.asList(indices), value);
    }

    public T get(Integer... indices) {
        return data.get(Arrays.asList(indices));
    }

    @Override
    public Iterator<T> iterator() {
        return data.values().iterator();
    }
}