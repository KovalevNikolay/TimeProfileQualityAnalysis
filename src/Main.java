import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Double> mapOnServiceOne = new TreeMap<>();
        mapOnServiceOne.put(6, 0.73);
        mapOnServiceOne.put(5, 0.02);
        mapOnServiceOne.put(2, 0.25);
        TreeMap<Integer, Double> mapOnServiceTwo = new TreeMap<>();
        mapOnServiceTwo.put(8, 0.15);
        mapOnServiceTwo.put(5, 0.4);
        mapOnServiceTwo.put(3, 0.45);
        TreeMap<Integer, Double> mapOnServiceThree = new TreeMap<>();
        mapOnServiceThree.put(6, 0.37);
        mapOnServiceThree.put(7, 0.25);
        mapOnServiceThree.put(3, 0.38);
        Service serviceOne = new Service(mapOnServiceOne);
        Service serviceTwo = new Service(mapOnServiceTwo);
        Service serviceThree = new Service(mapOnServiceThree);

        //solutionLabThree(new Service[] {serviceOne, serviceTwo, serviceThree});
        solutionLabFour(new Service[] {serviceOne, serviceTwo, serviceThree});
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

        Service serviceOneAndTwo = Service.combiningServices(serviceArray[0], serviceArray[1], false);
        System.out.println();
        Service resultService = Service.combiningServices(serviceOneAndTwo, serviceArray[2], false);

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