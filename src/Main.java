import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Double> mapOnServiceOne = new TreeMap<>();
        mapOnServiceOne.put(2, 0.28);
        mapOnServiceOne.put(1, 0.24);
        mapOnServiceOne.put(3, 0.48);
        TreeMap<Integer, Double> mapOnServiceTwo = new TreeMap<>();
        mapOnServiceTwo.put(2, 0.06);
        mapOnServiceTwo.put(8, 0.56);
        mapOnServiceTwo.put(10, 0.38);
        TreeMap<Integer, Double> mapOnServiceThree = new TreeMap<>();
        mapOnServiceThree.put(6, 0.03);
        mapOnServiceThree.put(4, 0.97);
        Service serviceOne = new Service(mapOnServiceOne);
        Service serviceTwo = new Service(mapOnServiceTwo);
        Service serviceThree = new Service(mapOnServiceThree);

        solutionLabThree(new Service[] {serviceOne, serviceTwo, serviceThree});
        //solutionLabFour(new Service[] {serviceOne, serviceTwo, serviceThree});
    }
    public static void printResult(Service service, int valueC) {
        service.print();
        System.out.println("Плотность распределения вероятностей равна: " + service.calculateProbabilityDensity());
        System.out.println("Математическое ожидание: " + service.calculateTheMathematicalExpectation());
        System.out.println("Дисперсия: " + service.calculateVariance());
        System.out.println("Риск срыва временного регламента при С = "+valueC +": " + service.calculateRSVR(valueC));
    }
    public static void solutionLabThree(Service[] serviceArray) {

        int value = 13;
        Service resultService = serviceArray[0];

        for (int i = 1; i < serviceArray.length; i++) {
            resultService = Service.combiningServices(resultService, serviceArray[i], true);
        }

        System.out.println("\nИтоговый сервис:");
        printResult(resultService, value);
    }
    public static void solutionLabFour(Service[] serviceArray) {
        int valueForAND = 5, valueForOr = 4;

        System.out.println("Исследование по булевой функции AND:");
        Service resultService = Service.combineServicesByFunctionAnd(serviceArray);
        printResult(resultService, valueForAND);

        System.out.println();
        System.out.println("Исследование по булевой функции OR:");
        Service resultServiceTwo = Service.combineServicesByFunctionOr(serviceArray);
        printResult(resultServiceTwo, valueForOr);
    }
}