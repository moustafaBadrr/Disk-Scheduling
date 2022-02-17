package disk_scheduling_algorithms;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CLook extends Algorithm {

    public CLook() {
        name = "CLook.";
    }

    @Override
    public ServiceResult runAlgorithm(HardDisk hardDisk, List<Request> requests) {
        ServiceResult sequence = new ServiceResult();
        List<Request> copyRequests = new ArrayList<>();
        copyRequests.addAll(requests);
        Collections.sort(copyRequests, new Comparator<Request>() {
            public int compare(Request r1, Request r2) {
                return Integer.valueOf(r1.getCylinderNumber()).compareTo(r2.getCylinderNumber());
            }
        });
//		for (int i = 0; i < copyRequests.size(); i++) {
//			System.out.println(copyRequests.get(i).getRequestID() + " " + (copyRequests.get(i).getCylinderNumber()));
//
//		}
        int i = 0;
        int startIndex = 0;
        for (int j = 0; j < copyRequests.size(); j++) {
            if (copyRequests.get(j).getCylinderNumber() >= hardDisk.getCurrentHeadPosition()) {
                startIndex = j;
                break;
            }

        }
        // System.out.println("s:"+startIndex);
        boolean flag = false;
        if (startIndex == copyRequests.size() - 1) {
            sequence.addRequest(copyRequests.get(startIndex));
            sequence.addToTotalHeadMovements(
                    copyRequests.get(startIndex).getCylinderNumber() - hardDisk.getCurrentHeadPosition());
            startIndex = 0;
            i++;
        } else if (hardDisk.getCurrentHeadPosition() > copyRequests.get(copyRequests.size() - 1).getCylinderNumber()) {
            startIndex = 0;
            flag = true;
        } else {
            sequence.addRequest(copyRequests.get(startIndex));
            sequence.addToTotalHeadMovements(
                    copyRequests.get(startIndex).getCylinderNumber() - hardDisk.getCurrentHeadPosition());

            startIndex++;
            i++;
        }

        while (i != copyRequests.size()) {
            if (startIndex == copyRequests.size()) {
                startIndex = 0;

            } else if (flag) {
                sequence.addRequest(copyRequests.get(startIndex));
                sequence.addToTotalHeadMovements(
                        hardDisk.getCurrentHeadPosition() - copyRequests.get(startIndex).getCylinderNumber());

                startIndex++;
                i++;
                flag = false;

            } else if (startIndex == 0) {

                sequence.addRequest(copyRequests.get(startIndex));
                sequence.addToTotalHeadMovements(copyRequests.get(copyRequests.size() - 1).getCylinderNumber()
                        - copyRequests.get(0).getCylinderNumber());
                startIndex++;
                i++;
            } else {
                sequence.addRequest(copyRequests.get(startIndex));
                sequence.addToTotalHeadMovements(copyRequests.get(startIndex).getCylinderNumber()
                        - copyRequests.get(startIndex - 1).getCylinderNumber());
                startIndex++;
                i++;
            }
        }
        return sequence; // Tools | Templates.
    }

}
