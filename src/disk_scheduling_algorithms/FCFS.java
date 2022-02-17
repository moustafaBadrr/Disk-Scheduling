package disk_scheduling_algorithms;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;
import java.util.ArrayList;
import java.util.List;

public class FCFS extends Algorithm {

    public FCFS() {
        name = "FCFS.";
    }

    @Override
    public ServiceResult runAlgorithm(HardDisk hardDisk, List<Request> requests) {
        ServiceResult service = new ServiceResult();
        List<Integer> done = new ArrayList<>();
        for (int i = 0; i < requests.size(); i++) {
            Request tmp = requests.get(i);
            service.addToTotalHeadMovements(Math.abs(tmp.getCylinderNumber() - hardDisk.getCurrentHeadPosition()));
            hardDisk.setCurrentHeadPosition(tmp.getCylinderNumber());
            service.addRequest(tmp);
        }
        return service;
    }

}
