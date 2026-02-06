package test1;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Transformer<T, R> {

    // המתודה המופשטת היחידה - הלב של הממשק
    R transform(T input);

    // 1. chain - מפעיל את הנוכחי ואז שרשרת של טרנספורמרים נוספים
    default Transformer<T, R> chain(List<Transformer<R, R>> successors) {
        return new Transformer<T, R>() {
            @Override
            public R transform(T input) {
                R result = Transformer.this.transform(input);
                for (Transformer<R, R> next : successors) {
                    result = next.transform(result);
                }
                return result;
            }
        };
    }

    // 2. map - הופך טרנספורמר של פריט בודד לטרנספורמר של רשימות
    default Transformer<List<T>, List<R>> map() {
        return new Transformer<List<T>, List<R>>() {
            @Override
            public List<R> transform(List<T> inputList) {
                List<R> resultList = new ArrayList<>();
                for (T item : inputList) {
                    resultList.add(Transformer.this.transform(item));
                }
                return resultList;
            }
        };
    }

    // 3. fallback - מספק טרנספורמר חלופי אם הנוכחי מחזיר null
    default Transformer<T, R> fallback(Transformer<T, R> alternative) {
        return new Transformer<T, R>() {
            @Override
            public R transform(T input) {
                R result = Transformer.this.transform(input);
                if (result == null) {
                    return alternative.transform(input);
                }
                return result;
            }
        };
    }

    // 4. peek - מאפשר לבצע פעולה (כמו הדפסה) על התוצאה בלי לשנות אותה
    default Transformer<T, R> peek(Consumer<R> action) {
        return new Transformer<T, R>() {
            @Override
            public R transform(T input) {
                R result = Transformer.this.transform(input);
                if (result != null) {
                    action.accept(result);
                }
                return result;
            }
        };
    }

    // 5. compose - מקבל טרנספורמר שפועל לפניו (קודם before ואז this)
    default <V> Transformer<V, R> compose(Transformer<V, T> before) {
        return new Transformer<V, R>() {
            @Override
            public R transform(V input) {
                // שלב 1: הפעלת הטרנספורמר שהתקבל כפרמטר (V -> T)
                T intermediate = before.transform(input);
                // שלב 2: הפעלת הטרנספורמר הנוכחי על התוצאה (T -> R)
                return Transformer.this.transform(intermediate);
            }
        };
    }
}