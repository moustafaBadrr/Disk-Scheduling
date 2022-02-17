package data_structures;

import java.util.ArrayList;
import java.util.List;

public class ServiceResult {

    private List<Request> serviedRequests;
    private int totalHeadMovements;

    public ServiceResult() {
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
