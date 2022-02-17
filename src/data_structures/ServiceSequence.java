package data_structures;

import java.util.ArrayList;
import java.util.List;

public class ServiceSequence {

    private List<Request> serviedRequests;
    private int totalHeadMovements;

    public ServiceSequence() {
        serviedRequests = new ArrayList<>();
        totalHeadMovements = 0;
    }

    public List<Request> getServiedRequests() {
        return serviedRequests;
    }

    public void addRequest(Request request) {
        serviedRequests.add(request);
    }

    public int getTotalHeadMovements() {
        return totalHeadMovements;
    }

    public void addToTotalHeadMovements(int haedMovements) {
        totalHeadMovements += haedMovements;
    }

}
