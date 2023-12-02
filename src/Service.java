import java.util.*;

public class Service {
    final private TreeMap<Integer, Double> profile;
    public Service(TreeMap<Integer, Double> profile) {
        this.profile = profile;
    }
    public TreeMap<Integer, Double> getProfile() {
        return profile;
    }

    public static Service combiningServices(Service s1, Service s2) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        int newKey = 0;
        StringBuilder sb;
        double newProbability = 0.0;
        double oldProbability = 0.0;
        for (Map.Entry<Integer, Double> entry1 : s1.getProfile().entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : s2.getProfile().entrySet()) {
                newKey = entry1.getKey() + entry2.getKey();
                newProbability = entry1.getValue() * entry2.getValue();
                sb = new StringBuilder();
                sb.append("U(K=").append(newKey).append(") = ").append(entry1.getValue()).append(" * ").append(entry2.getValue()).append(" = ").append(newProbability);
                System.out.println(sb.toString());
                if (!resultMap.containsKey(newKey)) {
                    resultMap.put(newKey, newProbability);
                }
                else {
                    oldProbability = resultMap.get(newKey);
                    resultMap.put(newKey, oldProbability + newProbability);
                }
            }
        }
        System.out.println();
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

        System.out.println("Минимальные значения: " + minElements);
        System.out.println("Максимальные значения: " + maxElements);

        int maxFromMinArray = Collections.max(minElements);
        int maxFromMaxArray = Collections.max(maxElements);

        StringBuilder sbToN;
        StringBuilder sbToNsubtractOne;

        System.out.println("Диапазон значений К: от " + maxFromMinArray + " до " + maxFromMaxArray);

        while(maxFromMinArray <= maxFromMaxArray) {

            double probability = 0.0;
            double multiplyingprobabilityToN = 1.0;
            double multiplyingprobabilityToNsubtractOne = 1.0;
            sbToN = new StringBuilder();
            sbToN.append("U(K=").append(maxFromMinArray).append(") = ");
            sbToNsubtractOne = new StringBuilder();

            for (Service s : serviceArray) {
                sbToN.append("(");
                sbToNsubtractOne.append("(");
                double sumProbabilityToN = 0;
                double sumProbabilityToNsubtractOne = 0;
                for (Map.Entry<Integer, Double> entry : s.getProfile().entrySet()) {
                    if (entry.getKey() <= maxFromMinArray) {
                        sbToN.append(entry.getValue()).append("+");
                        sumProbabilityToN += entry.getValue();
                    }
                    else sbToN.append("0+");
                    if (entry.getKey() <= maxFromMinArray - 1) {
                        sbToNsubtractOne.append(entry.getValue());
                        sbToNsubtractOne.append("+");
                        sumProbabilityToNsubtractOne += entry.getValue();
                    }
                    else sbToNsubtractOne.append("0+");
                }
                sbToN.deleteCharAt(sbToN.length()-1);
                sbToNsubtractOne.deleteCharAt(sbToNsubtractOne.length()-1);
                sbToN.append(")*");
                sbToNsubtractOne.append(")*");
                multiplyingprobabilityToN*=sumProbabilityToN;
                multiplyingprobabilityToNsubtractOne*=sumProbabilityToNsubtractOne;
            }
            probability = multiplyingprobabilityToN - multiplyingprobabilityToNsubtractOne;
            sbToN.deleteCharAt(sbToN.length()-1);
            sbToNsubtractOne.deleteCharAt(sbToNsubtractOne.length()-1);
            sbToN.append(" - ").append(sbToNsubtractOne).append(" = ").append(probability);
            System.out.println(sbToN.toString());
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

        System.out.println("Минимальные значения: " + minElements);
        System.out.println("Максимальные значения: " + maxElements);

        int minFromMinArray = Collections.min(minElements);
        int minFromMaxArray = Collections.min(maxElements);

        StringBuilder sbToN;
        StringBuilder sbToNsubtractOne;

        System.out.println("Диапазон значений К: от " + minFromMinArray + " до " + minFromMaxArray);

        while(minFromMinArray <= minFromMaxArray) {

            double probability = 0.0;
            double multiplyingprobabilityToN = 1.0;
            double multiplyingprobabilityToNsubtractOne = 1.0;

            sbToN = new StringBuilder();
            sbToNsubtractOne = new StringBuilder();
            sbToNsubtractOne.append("U(K=").append(minFromMinArray).append(") = ");

            for (Service s : serviceArray) {
                sbToN.append("(1-");
                sbToNsubtractOne.append("(1-");
                double differenceProbabilityToN = 1;
                double differenceProbabilityToNsubtractOne = 1;
                for (Map.Entry<Integer, Double> entry : s.getProfile().entrySet()) {
                    if (entry.getKey() <= minFromMinArray) {
                        differenceProbabilityToN-=entry.getValue();
                        sbToN.append(entry.getValue()).append("-");
                    }
                    else sbToN.append("0-");
                    if (entry.getKey() <= minFromMinArray - 1) {
                        differenceProbabilityToNsubtractOne-=entry.getValue();
                        sbToNsubtractOne.append(entry.getValue()).append("-");
                    }
                    else sbToNsubtractOne.append("0-");
                }
                // U(K=6) = (1-0,25-0,02)*(1-0,45-0,4)*(1-0,38) -  0*(1-0,45-0,4)*0 = 0,06789
                multiplyingprobabilityToN *= differenceProbabilityToN;
                multiplyingprobabilityToNsubtractOne *= differenceProbabilityToNsubtractOne;
                sbToN.deleteCharAt(sbToN.length()-1);
                sbToNsubtractOne.deleteCharAt(sbToNsubtractOne.length()-1);
                sbToN.append(")*");
                sbToNsubtractOne.append(")*");
            }

            probability = multiplyingprobabilityToNsubtractOne - multiplyingprobabilityToN;
            resultMap.put(minFromMinArray, probability);
            sbToN.deleteCharAt(sbToN.length()-1);
            sbToNsubtractOne.deleteCharAt(sbToNsubtractOne.length()-1);
            sbToNsubtractOne.append(" - ").append(sbToN).append(" = ").append(probability);
            System.out.println(sbToNsubtractOne.toString());
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


