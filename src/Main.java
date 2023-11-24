import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        solutionLab3();
    }

    public static void solutionLab3() {
        TreeMap<Integer, Double> mapOnService1 = new TreeMap<>();
        mapOnService1.put(8, 0.87);
        mapOnService1.put(9, 0.12);
        mapOnService1.put(3, 0.01);
        TreeMap<Integer, Double> mapOnService2 = new TreeMap<>();
        mapOnService2.put(6, 0.15);
        mapOnService2.put(5, 0.81);
        mapOnService2.put(3, 0.04);
        TreeMap<Integer, Double> mapOnService3 = new TreeMap<>();
        mapOnService3.put(4, 0.03);
        mapOnService3.put(1, 0.97);
        int value = 13;

        Service service1 = new Service(mapOnService1);
        Service service2 = new Service(mapOnService2);
        Service service3 = new Service(mapOnService3);

        Service service12 = Service.combiningServices(service1, service2, false);
        System.out.println();
        Service resultService = Service.combiningServices(service12, service3, false);

        System.out.println("\nИтоговый сервис:");
        resultService.print();
        System.out.println("Математическое ожидание: " + resultService.calculateTheMathematicalExpectation());
        System.out.println("Дисперсия: " + resultService.calculateVariance());
        System.out.println("Риск срыва временного регламента при С="+value +": " + resultService.calculateRSVR(value));
    }
}