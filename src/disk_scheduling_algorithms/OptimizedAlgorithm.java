package disk_scheduling_algorithms;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;
import java.util.Collections;
import java.util.List;

public class OptimizedAlgorithm extends Algorithm {

    public OptimizedAlgorithm() {
        name = "New Optimized Algorithm.";
    }

    @Override
    public ServiceResult runAlgorithm(HardDisk hardDisk, List<Request> requests) {
        hardDisk.setCurrentHeadPosition(0);
        ServiceResult service = new ServiceResult();
        sort(requests);
        for (int i = 0; i < requests.size(); i++) {
            service.addRequest(requests.get(i));
            Request tmp = requests.get(i);
            service.addToTotalHeadMovements(Math.abs(tmp.getCylinderNumber() - hardDisk.getCurrentHeadPosition()));
            hardDisk.setCurrentHeadPosition(tmp.getCylinderNumber());
        }
        return service;
    }
    
    private void sort(List<Request> requests) 
    { 
        int n = requests.size(); 
        for (int i = 0; i < n-1; i++) 
        { 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (requests.get(j).getCylinderNumber() < requests.get(min_idx).getCylinderNumber()) 
                    min_idx = j; 
            Collections.swap(requests, min_idx, i);
        } 
    } 

}
