import java.util.*;

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
    public static Service combineServicesByFunctionAnd (Service[] serviceArray) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        ArrayList<Integer> minElements = new ArrayList<>();
        ArrayList<Integer> maxElements = new ArrayList<>();
        for (Service s : serviceArray) {
            minElements.add(s.profile.firstKey());
            maxElements.add(s.profile.lastKey());
        }

        int maxFromMinArray = Collections.max(minElements);
        int maxFromMaxArray = Collections.max(maxElements);
        while(maxFromMinArray <= maxFromMaxArray) {

            double probability = 0.0;
            double multiplyingprobabilityToN = 1.0;
            double multiplyingprobabilityToNsubtractOne = 1.0;

            for (Service s : serviceArray) {
                double sumProbabilityToN = 0;
                double sumProbabilityToNsubtractOne = 0;
                for (Map.Entry<Integer, Double> entry : s.getProfile().entrySet()) {
                    if (entry.getKey() <= maxFromMinArray)
                        sumProbabilityToN+=entry.getValue();
                    if (entry.getKey() <= maxFromMinArray - 1)
                        sumProbabilityToNsubtractOne+=entry.getValue();
                }
                multiplyingprobabilityToN*=sumProbabilityToN;
                multiplyingprobabilityToNsubtractOne*=sumProbabilityToNsubtractOne;
            }
            probability = multiplyingprobabilityToN - multiplyingprobabilityToNsubtractOne;
            resultMap.put(maxFromMinArray, probability);
            maxFromMinArray++;
        }
        return new Service(resultMap);
    }
    public static Service combineServicesByFunctionOr (Service[] serviceArray) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        ArrayList<Integer> minElements = new ArrayList<>();
        ArrayList<Integer> maxElements = new ArrayList<>();
        for (Service s : serviceArray) {
            minElements.add(s.profile.firstKey());
            maxElements.add(s.profile.lastKey());
        }

        int minFromMinArray = Collections.min(minElements);
        int minFromMaxArray = Collections.min(maxElements);
        while(minFromMinArray <= minFromMaxArray) {

            double probability = 0.0;
            double multiplyingprobabilityToN = 1.0;
            double multiplyingprobabilityToNsubtractOne = 1.0;

            for (Service s : serviceArray) {
                double differenceProbabilityToN = 1;
                double differenceProbabilityToNsubtractOne = 1;
                for (Map.Entry<Integer, Double> entry : s.getProfile().entrySet()) {
                    if (entry.getKey() <= minFromMinArray)
                        differenceProbabilityToN-=entry.getValue();
                    if (entry.getKey() <= minFromMinArray - 1)
                        differenceProbabilityToNsubtractOne-=entry.getValue();
                }
                multiplyingprobabilityToN *= differenceProbabilityToN;
                multiplyingprobabilityToNsubtractOne *= differenceProbabilityToNsubtractOne;
            }
            probability = multiplyingprobabilityToNsubtractOne - multiplyingprobabilityToN;
            resultMap.put(minFromMinArray, probability);
            minFromMinArray++;
        }

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
            System.out.printf("%-5d \t|\t %,.5f\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}


