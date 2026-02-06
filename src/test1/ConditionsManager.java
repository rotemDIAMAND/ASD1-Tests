package test1;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ConditionsManager<T> {

    private Map<String, Predicate<T>> conditions;

    public ConditionsManager() {
        this.conditions = new HashMap<>();
    }

    public void update(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }

    /**
     * בדיקה האם ערך מסוים עומד בתנאי שהוגדר.
     */
    public boolean test(String name, T value) {
        // בדיקה האם הקריטריון בכלל קיים במפה
        if (!conditions.containsKey(name)) {
            return false; // אם לא קיים, מחזירים false לפי דרישת התרגיל
        }

        // אם הגענו לכאן, הקריטריון קיים - נשלוף ונבדוק אותו
        return conditions.get(name).test(value);
    }
}
