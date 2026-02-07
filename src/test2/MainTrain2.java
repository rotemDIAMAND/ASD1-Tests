package test2;

import java.util.*;

public class MainTrain2 {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        int totalPoints = 24;

        // --- Test 1: Put & Get ---
        try {
            Tensor<Integer> tensor = new Tensor<>();

            
            Integer[] idx1 = randomIndices(), idx2 = randomIndices(), idx3 = randomIndices();
            int v1 = rand.nextInt(100), v2 = rand.nextInt(100), v3 = rand.nextInt(100);

            tensor.put(v1, idx1);
            tensor.put(v2, idx2);
            tensor.put(v3, idx3);

            if (!Objects.equals(tensor.get(idx1), v1)) {
                System.out.println("Error: get() failed for first random index (-6)");
                totalPoints -= 6;
            }
            if (!Objects.equals(tensor.get(idx2), v2)) {
                System.out.println("Error: get() failed for second random index (-6)");
                totalPoints -= 6;
            }
            if (!Objects.equals(tensor.get(idx3), v3)) {
                System.out.println("Error: get() failed for third random index (-6)");
                totalPoints -= 6;
            }
            if (tensor.get(999, 999) != null) { 
                System.out.println("Error: get() should return null for missing index (-6)");
                totalPoints -= 6;
            }

        } catch (Exception e) {
            System.out.println("Exception in Test 1 (-"+(totalPoints)+")");
        }

        // --- Test 2: Iterator Order ---
        try {
            Tensor<String> tensor = new Tensor<>();
            String s1 = randomString(), s2 = randomString(), s3 = randomString();
            Integer[] idx1 = randomIndices(), idx2 = randomIndices(), idx3 = randomIndices();

            tensor.put(s1, idx1);
            tensor.put(s2, idx2);
            tensor.put(s3, idx3);

            Iterator<String> iter = tensor.iterator();
            List<String> values = new ArrayList<>();
            while (iter.hasNext()) {
                values.add(iter.next());
            }

            List<String> expected = Arrays.asList(s1, s2, s3);
            if (!values.equals(expected)) {
                System.out.println("Error: Iterator order is incorrect (-8)");
                totalPoints -= 16;
            }

        } catch (Exception e) {
            System.out.println("Exception in Test 2 (-8)");
            totalPoints -= 16;
        }

        System.out.println("done");
    }

    
    private static Integer[] randomIndices() {
        int length = rand.nextInt(4) + 1;
        Integer[] indices = new Integer[length];
        for (int i = 0; i < length; i++) {
            indices[i] = rand.nextInt(10);
        }
        return indices;
    }

    
    private static String randomString() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}
