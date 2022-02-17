package input_handeller;

import data_structures.Request;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFile implements InputHandeller {
	BufferedReader readFromFile;
	
	public InputFile(){
		try {
			readFromFile = new BufferedReader(new FileReader(getFileName()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int inputNumberOfRequests() {
		int numberOfRequests = 0;
		
		try {
			
			String fileinputres = readFromFile.readLine();
			numberOfRequests = Integer.parseInt(fileinputres);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfRequests;
    }

	@Override
	public int inputNumberOfCylinders() {
		int numberOfCylinder = 0;
		
		try {
			
			String fileinputres = readFromFile.readLine();
			numberOfCylinder = Integer.parseInt(fileinputres);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfCylinder;
	}

    @Override
    public List<Request> inputRequests(int numberOfRequests, int numberOfCylinders) {
    	List<Request> requests = new ArrayList<Request>();
		
		try {
			int i = 0;
			String fileinputres = readFromFile.readLine();
			
			while (fileinputres != null) {
				Request req = new Request();
				Request req1 = new Request();
				req1.setCylinderNumber(Integer.parseInt(fileinputres));
				req1.setRequestID(i);
				requests.add(req1);
				i++;
				fileinputres = readFromFile.readLine();
				
			}
			readFromFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requests;
		
    }

    @Override
    public int inputInitialHeadPosition(int numberOfCylinders) {
    	int initial = 0;
		
		try {
			
			String fileinputres = readFromFile.readLine();
			// System.out.println("We");
			initial = Integer.parseInt(fileinputres);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return initial;
    }
    
     String getFileName() {
    	System.out.println("Enter the file name to read from it: ");
    	Scanner input = new Scanner (System.in);
    	String fileName = input.next();
    	return fileName;
    }
   

}
