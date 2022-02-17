package input_handeller;

import data_structures.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputConsole implements InputHandeller {

    @Override
    public int inputNumberOfRequests() {
        return inputInteger("Enter the number of requests :");
    }

    @Override
    public int inputNumberOfCylinders() {
        return inputInteger("Enter the number of cylinders :", 1);
    }

    @Override
    public List<Request> inputRequests(int numberOfRequests, int numberOfCylinders) {
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            int cylinderNumber = inputInteger("Enter the cylinder number for request " + (i + 1) + " :", 0, numberOfCylinders - 1);
            requests.add(new Request(i, cylinderNumber));
        }
        return requests;
    }

    @Override
    public int inputInitialHeadPosition(int numberOfCylinders) {
        return inputInteger("Enter the initial head position :", 0, numberOfCylinders - 1);
    }

    public int inputInteger(String messageToUser) {
        return inputInteger(messageToUser, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int inputInteger(String messageToUser, int lowerBound) {
        return inputInteger(messageToUser, lowerBound, Integer.MAX_VALUE);
    }

    //Lower and Upper are inclusive.
    public int inputInteger(String messageToUser, int lowerBound, int upperBound) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(messageToUser);
            input = scanner.nextLine();
            int integer = Integer.parseInt(input);
            if (integer >= lowerBound && integer <= upperBound) {
                return integer;
            } else {
                System.err.println("Invalid Range.");
            }
        }
    }

}
