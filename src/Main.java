import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

    }

    public static void solutionLab3() {
        TreeMap<Integer, Double> mapOnServiceOne = new TreeMap<>();
        mapOnServiceOne.put(8, 0.87);
        mapOnServiceOne.put(9, 0.12);
        mapOnServiceOne.put(3, 0.01);
        TreeMap<Integer, Double> mapOnServiceTwo = new TreeMap<>();
        mapOnServiceTwo.put(6, 0.15);
        mapOnServiceTwo.put(5, 0.81);
        mapOnServiceTwo.put(3, 0.04);
        TreeMap<Integer, Double> mapOnServiceThree = new TreeMap<>();
        mapOnServiceThree.put(4, 0.03);
        mapOnServiceThree.put(1, 0.97);
        int value = 13;

        Service serviceOne = new Service(mapOnServiceOne);
        Service serviceTwo = new Service(mapOnServiceTwo);
        Service serviceThree = new Service(mapOnServiceThree);

        Service serviceOneAndTwo = Service.combiningServices(serviceOne, serviceTwo, false);
        System.out.println();
        Service resultService = Service.combiningServices(serviceOneAndTwo, serviceThree, false);

        System.out.println("\nИтоговый сервис:");
        resultService.print();
        System.out.println("Математическое ожидание: " + resultService.calculateTheMathematicalExpectation());
        System.out.println("Дисперсия: " + resultService.calculateVariance());
        System.out.println("Риск срыва временного регламента при С="+value +": " + resultService.calculateRSVR(value));
    }
    public static void solutionLab4() {

    }
}