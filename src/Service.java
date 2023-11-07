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
    public double calculateTheMathematicalExpectation() {
        double mathematicalExpectation = 0.0;
        for (Map.Entry<Integer, Double> entry : this.profile.entrySet()) {
            mathematicalExpectation += entry.getKey() * entry.getValue();
        }
        return mathematicalExpectation;
    }
    public double calculateVariance() {
        double variance = 0;
        for (Map.Entry<Integer, Double> entry : this.profile.entrySet()) {
            variance += Math.pow((entry.getKey() - this.calculateTheMathematicalExpectation()),2) * entry.getValue();
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


