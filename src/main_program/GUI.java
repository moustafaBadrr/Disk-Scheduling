package main_program;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data_structures.HardDisk;
import data_structures.Request;
import data_structures.ServiceResult;
import disk_scheduling_algorithms.Algorithm;
import disk_scheduling_algorithms.CLook;
import disk_scheduling_algorithms.CScan;
import disk_scheduling_algorithms.FCFS;
import disk_scheduling_algorithms.OptimizedAlgorithm;
import disk_scheduling_algorithms.SSTF;
import disk_scheduling_algorithms.Scan;
import input_handeller.InputConsole;
import input_handeller.InputHandeller;
import output_handeller.OutputConsole;
import output_handeller.OutputHandeller;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public static List<Algorithm> getAvailableAlgorithms() {
		List<Algorithm> algorithms = new ArrayList<>();
		algorithms.add(new FCFS());
		algorithms.add(new SSTF());
		algorithms.add(new Scan());
		algorithms.add(new CScan());
		algorithms.add(new CLook());
		algorithms.add(new OptimizedAlgorithm());
		return algorithms;
	}

	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setTitle("OS2_Assignemnt_2");
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(27, 35, 99, 22);
		contentPane.add(textArea);

		JLabel lblNewLabel = new JLabel("Enter the number of cylinders");
		lblNewLabel.setBounds(27, 15, 187, 16);
		contentPane.add(lblNewLabel);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(27, 90, 99, 22);
		contentPane.add(textArea_1);

		JLabel lblEnterTheNumber = new JLabel("Enter the number of requests");
		lblEnterTheNumber.setBounds(27, 70, 187, 16);
		contentPane.add(lblEnterTheNumber);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(27, 154, 99, 37);
		contentPane.add(textArea_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 154, 99, 37);
		contentPane.add(scrollPane);

	  scrollPane.setViewportView(textArea_2);

		JLabel lblEnterTheRequests = new JLabel("Enter the requests by spaces");
		lblEnterTheRequests.setBounds(27, 134, 187, 16);
		contentPane.add(lblEnterTheRequests);

		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(27, 233, 99, 22);
		contentPane.add(textArea_3);

		JLabel lblEnterTheInitial = new JLabel("Enter the initial head position");
		lblEnterTheInitial.setBounds(27, 213, 187, 16);
		contentPane.add(lblEnterTheInitial);

		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(230, 35, 318, 220);
		contentPane.add(textArea_4);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(230, 35, 318, 220);
		contentPane.add(scrollPane1);

		scrollPane1.setViewportView(textArea_4);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numberOfCylinders = Integer.parseInt(textArea.getText());
				int numberOfRequests = Integer.parseInt(textArea_1.getText());
				int initialHeadPosition = Integer.parseInt(textArea_3.getText());
				List<Request> requests = new ArrayList<Request>();
				String reqs, reqss[];
				reqs = textArea_2.getText();
				reqss = reqs.split(" ");
				for (int j = 0; j < reqss.length; j++) {
					Request req = new Request();
					req.setCylinderNumber(Integer.parseInt(reqss[j]));
					req.setRequestID(j);
					requests.add(req);
				}
				HardDisk hardDisk = new HardDisk(numberOfCylinders, initialHeadPosition);
				List<Algorithm> algorithms = getAvailableAlgorithms();
				String setTotal = " ";
				for (Algorithm algorithm : algorithms) {
					try {
						
						HardDisk copyHardDisk = new HardDisk(hardDisk);
						ServiceResult sequence = algorithm.runAlgorithm(copyHardDisk, requests);
						List<Request> serviedRequests = sequence.getServiedRequests();
						int totalHeadMovement = sequence.getTotalHeadMovements();
						String set = " ";
						set += "Algorithm Name :" + algorithm.getName() + "\n";
				        set += " ID" + "   " + "Cylinder\n";
				        for (Request request : serviedRequests) {
				            set += " " + request.getRequestID() + "   " + request.getCylinderNumber() + "\n";
				        }
				        set += "Total Head Movement :" + totalHeadMovement + "\n";
						setTotal += set;
					} catch (UnsupportedOperationException e) {
						System.err.println("Error");
					}

				}
				textArea_4.setText(setTotal);
				
			}
		});
		btnRun.setBounds(195, 288, 97, 32);
		contentPane.add(btnRun);
	}
}