package output_handeller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import data_structures.Request;
import data_structures.ServiceResult;
import input_handeller.InputFile;

public class OutputFile implements OutputHandeller {
	static String setTotal = "";
	public OutputFile() {}
	
	@Override
	public void displayResult(ServiceResult sequence, String AlgorithmName) {
		List<Request> serviedRequests = sequence.getServiedRequests();
		int totalHeadMovement = sequence.getTotalHeadMovements();

		String set = " ";
		set += "Algorithm Name :" + AlgorithmName + "\n";
		set += " ID" + "   " + "Cylinder\n";
		for (Request request : serviedRequests) {
			set += " " + request.getRequestID() + "   " + request.getCylinderNumber() + "\n";
		}
		set += "Total Head Movement :" + totalHeadMovement + "\n";
		setTotal += set;
	//	System.out.println(set);
	//	System.out.println(setTotal);
	}

    public void writeResult() {
    	try {
			FileWriter write = new FileWriter(getFileName());
			PrintWriter writeData = new  PrintWriter(write);
			writeData.write(setTotal);
			writeData.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @Override
    public void displayUnSupportedAlgorithm(String algorithmName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getFileName() {
    	System.out.println("Enter the file name to write on it: ");
    	Scanner input = new Scanner (System.in);
    	String fileName = input.next();
    	return fileName;
    }
}