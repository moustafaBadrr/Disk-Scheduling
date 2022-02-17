package disk_scheduling_algorithms;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Scan extends Algorithm {

	public Scan() {
		name = "Scan.";
	}

//	public static List<Request> sortTheArray(List<Request> requests) {
//
//		for (int i = 1, j; i < requests.size(); i++) {
//			int tempNumber = requests.get(i).getCylinderNumber();
//			int tempID = requests.get(i).getRequestID();
//			for (j = i; j > 0 && tempNumber < requests.get(j - 1).getCylinderNumber(); j--) {
//				requests.get(j).setCylinderNumber(requests.get(j - 1).getCylinderNumber());
//				requests.get(j).setRequestID(requests.get(j - 1).getRequestID());
//			}
//			requests.get(j).setCylinderNumber(tempNumber);
//			requests.get(j).setRequestID(tempID);
//
//		}
//
//		return requests;
//	}

	public static int getIndex(List<Request> arr, int integer) {
		int x = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (integer == arr.get(i).getCylinderNumber()) {
				x = i;
				break;
			}
		}
		return x;
	}

	@Override
	public ServiceResult runAlgorithm(HardDisk hardDisk, List<Request> requests) {

		ServiceResult sequence = new ServiceResult();
		List<Request> copyRequests = new ArrayList<Request>();
		copyRequests.addAll(requests);
		
//		Request req = new Request();
//		req.setCylinderNumber(0);
//		req.setRequestID(-1);
//		copyRequests.add(req);

		Request req1 = new Request();
		req1.setCylinderNumber(hardDisk.getCurrentHeadPosition());
		req1.setRequestID(-2);
		copyRequests.add(req1);

		
		
		//copyRequests=sortTheArray(copyRequests);

		
		
        Collections.sort(copyRequests, new Comparator<Request>() {
            public int compare(Request r1, Request r2) {
                return Integer.valueOf(r1.getCylinderNumber()).compareTo(r2.getCylinderNumber());
            }
        });
        
        
		int y = getIndex(copyRequests, hardDisk.getCurrentHeadPosition()) - 1;
		
		while (y > -1) {

			sequence.addRequest(copyRequests.get(y));
			y--;

		}

		y = getIndex(copyRequests, hardDisk.getCurrentHeadPosition()) + 1;
		while (y < copyRequests.size()) {
			sequence.addRequest(copyRequests.get(y));
			y++;

		}
		
		for (int i = 0; i < sequence.getServiedRequests().size() - 1;i++) {
			sequence.addToTotalHeadMovements(Math.abs(sequence.getServiedRequests().get(i+1).getCylinderNumber() - 
					sequence.getServiedRequests().get(i).getCylinderNumber()));
		}
		return sequence;
	}

}
