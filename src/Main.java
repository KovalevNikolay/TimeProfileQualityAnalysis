import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Double> mapOnService1 = new TreeMap<>();
        mapOnService1.put(2, 0.28);
        mapOnService1.put(1, 0.24);
        mapOnService1.put(3, 0.48);
        TreeMap<Integer, Double> mapOnService2 = new TreeMap<>();
        mapOnService2.put(2, 0.06);
        mapOnService2.put(8, 0.56);
        mapOnService2.put(10, 0.38);
        TreeMap<Integer, Double> mapOnService3 = new TreeMap<>();
        mapOnService3.put(6, 0.03);
        mapOnService3.put(4, 0.97);
        int value = 17;

        Service service1 = new Service(mapOnService1);
        Service service2 = new Service(mapOnService2);
        Service service3 = new Service(mapOnService3);
        
        Service resultService = combiningServices(combiningServices(service1, service2), service3);

        System.out.println("\nИтоговый сервис:");
        resultService.print();
        System.out.println("Математическое ожидание: " + resultService.calculateTheMathematicalExpectation());
        System.out.println("Дисперсия: " + resultService.calculateVariance());
        System.out.println("Риск срыва временного регламента при С="+value +": " + resultService.calculateRSVR(value));
    }
    public static Service combiningServices(Service s1, Service s2) {
        TreeMap<Integer, Double> resultMap = new TreeMap<>();
        int newKey = 0;
        double newProbability = 0.0;
        double oldProbability = 0.0;
        for (Map.Entry<Integer, Double> entry1 : s1.getProfile().entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : s2.getProfile().entrySet()) {
                newKey = entry1.getKey() + entry2.getKey();
                newProbability = entry1.getValue() * entry2.getValue();
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
}