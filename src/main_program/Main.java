package main_program;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;
import disk_scheduling_algorithms.Algorithm;
import disk_scheduling_algorithms.CLook;
import disk_scheduling_algorithms.CScan;
import disk_scheduling_algorithms.FCFS;
import disk_scheduling_algorithms.OptimizedAlgorithm;
import disk_scheduling_algorithms.SSTF;
import disk_scheduling_algorithms.Scan;
import input_handeller.InputConsole;
import input_handeller.InputFile;
import input_handeller.InputHandeller;
import java.util.ArrayList;
import java.util.List;
import output_handeller.OutputConsole;
import output_handeller.OutputFile;
import output_handeller.OutputHandeller;

public class Main {

    public static void main(String[] args) {
        //testSpecificAlgorithm();

        printMainMenu();
        String choice = inputMainMenu();
        boolean isGuiChoosen = false;
        boolean isFile = false;
        InputHandeller inputHandeller = null;
        OutputHandeller outputHandeller = null;
        if (choice.equals("Console.")) {
            inputHandeller = new InputConsole();
            outputHandeller = new OutputConsole();
        } else if (choice.equals("File.")) {
            inputHandeller = new InputFile();
            outputHandeller = new OutputFile();
            isFile =true;
        } else if (choice.equals("GUI.")) {
            isGuiChoosen = true;
        }

        if (isGuiChoosen) {
        	GUI gui=new GUI();
        	gui.main(args);
        } else {
            int numberOfCylinders = inputHandeller.inputNumberOfCylinders();
            //System.out.println(numberOfCylinders);
            int numberOfRequests = inputHandeller.inputNumberOfRequests();
           // System.out.println(numberOfRequests);
            int headPosition = inputHandeller.inputInitialHeadPosition(numberOfCylinders);
            //System.out.println(headPosition);
            List<Request> requests = inputHandeller.inputRequests(numberOfRequests, numberOfCylinders);
//            for (int i = 0; i < requests.size(); i++) {
//				System.out.println(requests.get(i));
//			}
            

            HardDisk hardDisk = new HardDisk(numberOfCylinders, headPosition);
            List<Algorithm> algorithms = getAvailableAlgorithms();
            for (Algorithm algorithm : algorithms) {
                try {
                    HardDisk copyHardDisk = new HardDisk(hardDisk);
                    ServiceResult sequence = algorithm.runAlgorithm(copyHardDisk, requests);
                    outputHandeller.displayResult(sequence, algorithm.getName());
                } catch (UnsupportedOperationException e) {
                    outputHandeller.displayUnSupportedAlgorithm(algorithm.getName());
                }
            }
            if (isFile){
            	OutputFile file = new OutputFile();
            	file.writeResult();
            }
        }
    }

    private static void printMainMenu() {
        OutputConsole.println("1-Console.");
        OutputConsole.println("2-File.");
        OutputConsole.println("3-GUI.");
    }

    private static String inputMainMenu() {
        InputConsole inputConsole = new InputConsole();
        int choice = inputConsole.inputInteger("Enter a choice from menu :", 1, 3);
        if (choice == 1) {
            return "Console.";
        } else if (choice == 2) {
            return "File.";
        } else if (choice == 3) {
            return "GUI.";
        } else {
            return null;
        }
    }

    private static List<Algorithm> getAvailableAlgorithms() {
        List<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new FCFS());
        algorithms.add(new SSTF());
        algorithms.add(new Scan());
        algorithms.add(new CScan());
        algorithms.add(new CLook());
        algorithms.add(new OptimizedAlgorithm());
        return algorithms;
    }

    private static void testSpecificAlgorithm() {
        OutputHandeller outputHandeller = new OutputConsole();
        List<Request> requests = new ArrayList<>();

        //Test cases.
        int numberOfRequests = 8;
        int numberOfCylinders = 200;
        int currentHeadPosition = 53;
        HardDisk hardDisk = new HardDisk(numberOfCylinders, currentHeadPosition);

        //Requests.
        requests.add(new Request(0, 98));
        requests.add(new Request(1, 183));
        requests.add(new Request(2, 37));
        requests.add(new Request(3, 122));
        requests.add(new Request(4, 14));
        requests.add(new Request(5, 124));
        requests.add(new Request(6, 65));
        requests.add(new Request(7, 67));

        //Algorithm Tested.
        Algorithm algorithm = new SSTF();
        ServiceResult serviceResult = algorithm.runAlgorithm(hardDisk, requests);
        outputHandeller.displayResult(serviceResult, algorithm.getName());
    }
}
