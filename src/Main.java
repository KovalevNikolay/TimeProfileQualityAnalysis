import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Double> mapOnServiceOne = new TreeMap<>();
        mapOnServiceOne.put(1, 0.81);
        mapOnServiceOne.put(9, 0.09);
        mapOnServiceOne.put(5, 0.1);
        TreeMap<Integer, Double> mapOnServiceTwo = new TreeMap<>();
        mapOnServiceTwo.put(3, 0.56);
        mapOnServiceTwo.put(2, 0.33);
        mapOnServiceTwo.put(4, 0.11);
        TreeMap<Integer, Double> mapOnServiceThree = new TreeMap<>();
        mapOnServiceThree.put(6, 0.13);
        mapOnServiceThree.put(7, 0.61);
        mapOnServiceThree.put(8, 0.26);
        Service serviceOne = new Service(mapOnServiceOne);
        Service serviceTwo = new Service(mapOnServiceTwo);
        Service serviceThree = new Service(mapOnServiceThree);

        //solutionLabThree(new Service[] {serviceOne, serviceTwo, serviceThree});
        solutionLabFour(new Service[] {serviceOne, serviceTwo, serviceThree});
    }
    public static void printResult(Service service, int valueC) {
        service.print();
        System.out.println("Плотность распределения вероятностей равна: " + service.calculateProbabilityDensity());
        service.calculateTheMathematicalExpectation(true);
        service.calculateVariance(true);
        service.calculateRSVR(valueC, true);
    }
    public static void solutionLabThree(Service[] serviceArray) {
        int value = 22;
        Service resultService = serviceArray[0];

        for (int i = 1; i < serviceArray.length; i++) {
            resultService = Service.combiningServices(resultService, serviceArray[i]);
        }
        System.out.println("\nИтоговый сервис:");
        printResult(resultService, value);
    }
    public static void solutionLabFour(Service[] serviceArray) {
        int valueForAND = 7, valueForOr = 2;

        System.out.println("Исследование по булевой функции AND:");
        Service resultService = Service.combineServicesByFunctionAnd(serviceArray);
        printResult(resultService, valueForAND);

        System.out.println();
        System.out.println("Исследование по булевой функции OR:");
        Service resultServiceTwo = Service.combineServicesByFunctionOr(serviceArray);
        printResult(resultServiceTwo, valueForOr);
    }
}