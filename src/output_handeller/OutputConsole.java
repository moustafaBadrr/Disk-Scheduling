package output_handeller;

import data_structures.Request;
import data_structures.ServiceResult;
import java.util.List;

public class OutputConsole implements OutputHandeller {

    @Override
    public void displayResult(ServiceResult sequence, String alogrithmName) {
        List<Request> serviedRequests = sequence.getServiedRequests();
        int totalHeadMovement = sequence.getTotalHeadMovements();

        println("Algorithm Name :" + alogrithmName);
        println("ID" + " " + "Cylinder");
        for (Request request : serviedRequests) {
            println(request.getRequestID() + "  " + request.getCylinderNumber());
        }
        println("Total Head Movement :" + totalHeadMovement);
    }

    @Override
    public void displayUnSupportedAlgorithm(String alogrithmName) {
        println("The following algorithm is not implemented :" + alogrithmName);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static <T> void printList(List<T> list) {
        for (T item : list) {
            println(item.toString());
        }
    }

}
