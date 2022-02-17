package disk_scheduling_algorithms;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;
import java.util.List;

public abstract class Algorithm {

    protected String name;

    public abstract ServiceResult runAlgorithm(HardDisk hardDisk, List<Request> requests);

    public String getName() {
        return name;
    }
}
