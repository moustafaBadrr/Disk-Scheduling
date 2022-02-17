package disk_scheduling_algorithms;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;
import java.util.ArrayList;
import java.util.List;
import output_handeller.OutputConsole;

public class SSTF extends Algorithm {

    public SSTF() {
        name = "SSTF.";
    }

    @Override
    public ServiceResult runAlgorithm(HardDisk hardDisk, List<Request> requests) {
        ServiceResult serviceResult = new ServiceResult();
        int numberOfRequests = requests.size();
        List<Request> sortedRequests = sortIntoCopy(requests);
        for (int i = 0; i < numberOfRequests; i++) {
            Request request = findNearest(sortedRequests, hardDisk.getCurrentHeadPosition());
            sortedRequests.remove(request);
            serviceResult.addRequest(request);
            int distanceMoved = Math.abs(request.getCylinderNumber() - hardDisk.getCurrentHeadPosition());
            serviceResult.addToTotalHeadMovements(distanceMoved);
            hardDisk.setCurrentHeadPosition(request.getCylinderNumber());
        }
        return serviceResult;
    }

    private List<Request> sortIntoCopy(List<Request> requests) {
        List<Request> sortedRequests = copyList(requests);
        for (int i = 0; i < sortedRequests.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedRequests.size(); j++) {
                if (sortedRequests.get(j).getCylinderNumber() < sortedRequests.get(minIndex).getCylinderNumber()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Request temp = new Request(sortedRequests.get(i));
                sortedRequests.set(i, sortedRequests.get(minIndex));
                sortedRequests.set(minIndex, temp);
            }
        }
        return sortedRequests;
    }

    private Request findNearest(List<Request> sortedRequests, int currentHeadPosition) {
        Request nearest = sortedRequests.get(0);
        int minimumLinearDistance = Math.abs(sortedRequests.get(0).getCylinderNumber() - currentHeadPosition);
        for (int i = 1; i < sortedRequests.size(); i++) {
            Request currentRequest = sortedRequests.get(i);
            int linearDistance = Math.abs(currentRequest.getCylinderNumber() - currentHeadPosition);
            if (linearDistance < minimumLinearDistance) {
                minimumLinearDistance = linearDistance;
                nearest = currentRequest;
            } else {
                //Since the array is sorted, once the linearDistance started to increase relation to the last linearDistance.
                //Then we already got the minimum possible distance.
                break;
            }
        }
        return nearest;
    }

    private List<Request> copyList(List<Request> requests) {
        List<Request> copy = new ArrayList<>();
        for (Request request : requests) {
            copy.add(new Request(request));
        }
        return copy;
    }

}
