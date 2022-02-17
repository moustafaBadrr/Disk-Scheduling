package input_handeller;

import data_structures.Request;
import java.util.List;

public interface InputHandeller {

    public int inputNumberOfRequests();

    public int inputNumberOfCylinders();

    public List<Request> inputRequests(int numberOfRequests, int numberOfCylinders);

    public int inputInitialHeadPosition(int numberOfCylinders);
}
