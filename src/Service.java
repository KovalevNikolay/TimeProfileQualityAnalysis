import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Service {
    final private TreeMap<Integer, Double> profile;
    public Service(TreeMap<Integer, Double> profile) {
        this.profile = profile;
    }
    public TreeMap<Integer, Double> getProfile() {
        return profile;
    }

    public static Service combiningServices(Service s1, Service s2, boolean withSolution) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        int newKey = 0;
        double newProbability = 0.0;
        double oldProbability = 0.0;
        for (Map.Entry<Integer, Double> entry1 : s1.getProfile().entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : s2.getProfile().entrySet()) {
                newKey = entry1.getKey() + entry2.getKey();
                newProbability = entry1.getValue() * entry2.getValue();
                if (withSolution) {
                    //U(K=17) = 0.73 * 0.04 = 0,0292
                    System.out.println("U(K=" + newKey + ") = " + entry1.getValue() + " * " + entry2.getValue() + " = " + newProbability);
                }
                if (!resultMap.containsKey(newKey)) {
                    resultMap.put(newKey, newProbability);
                }
                else {
                    oldProbability = resultMap.get(newKey);
                    resultMap.put(newKey, oldProbability + newProbability);
                }
            }
        }
        return new Service(resultMap);
    }
    public Service combineServicesByFunctionAnd (Service s1, Service s2, Service s3) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        int[] minTimeArray = new int[] {s1.profile.firstKey(), s2.profile.firstKey(), s3.profile.firstKey()};
        int[] maxTimeArray = new int[] {s1.profile.lastKey(), s2.profile.lastKey(), s3.profile.lastKey()};
        int maxFromMinArray = maxElement(minTimeArray);
        int maxFromMaxArray = maxElement(maxTimeArray);
        double probability = 0.0;
        while(maxFromMinArray <= maxFromMaxArray) {

            // TODO: 24.11.2023 Изменить сигнатуру метода на Service[] serviceArray

            maxFromMaxArray++;
        }
        return new Service(resultMap);
    }

    public int minElement(int[] array) {
        int min = array[0];
        for (int i = 0; i<array.length; i++) {
            if(array[i] < min)
                min = array[i];
        }
        return min;
    }
    public int maxElement(int[] array) {
        int max = array[0];
        for (int i = 0; i<array.length; i++) {
            if(array[i] > max)
                max = array[i];
        }
        return max;
    }
    public Service combineServicesByFunctionOr (Service[] serviceArray) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        // TODO: 24.11.2023  

        return new Service(resultMap);
    }
    public double calculateTheMathematicalExpectation() {
        double mathematicalExpectation = 0.0;
        for (Map.Entry<Integer, Double> entry : this.profile.entrySet()) {
            mathematicalExpectation += entry.getKey() * entry.getValue();
        }
        return mathematicalExpectation;
    }
    public double calculateVariance() {
        double variance = 0;
        double mathematicalExpectation = this.calculateTheMathematicalExpectation();
        for (Map.Entry<Integer, Double> entry : this.profile.entrySet()) {
            variance += Math.pow((entry.getKey() - mathematicalExpectation),2) * entry.getValue();
        }
        return variance;
    }
    public double calculateRSVR(int c) {
        double rsvr = 0.0;
        for (Map.Entry<Integer, Double> entry : this.profile.entrySet()) {
            if (entry.getKey() <= c) {
                rsvr+= entry.getValue();
            }
            else {
                break;
            }
        }
        return 1 - rsvr;
    }
    public double calculateProbabilityDensity() {
        double probabilityDensity = 0;
        for (Map.Entry<Integer,Double> entry : this.profile.entrySet()) {
            probabilityDensity += entry.getValue();
        }
        return probabilityDensity;
    }
    public void print() {
        System.out.printf("%-5s \t|\t %-15s \n", "Время", "Вероятность");
        for (Map.Entry<Integer, Double> entry : this.profile.entrySet()) {
            System.out.printf("%-5d \t|\t %,.4f\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}


