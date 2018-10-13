import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {


	/**
	 * A Job for this lab activity. 
	 * @author pedroirivera-vega
	 *
	 */
	private static class Job {
		private int pid;            // id of this job
		private int arrivalTime;    // arrival time of this job
		private int remainingTime;  // remaining service time for this job
		private int departureTime;  // time when the service for this job is completed

		public Job(int id, int at, int rt) { 
			pid = id; 
			arrivalTime = at; 
			remainingTime = rt; 
		}

		public int getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(int departureTime) {
			this.departureTime = departureTime;
		}

		public int getPid() {
			return pid;
		}

		public int getArrivalTime() {
			return arrivalTime;
		}

		public int getRemainingTime() {
			return remainingTime;
		}

		/**
		 * Registers an update of serviced received by this job. 
		 * @param q the time of the service being registered. 
		 */
		public void isServed(int q) { 
			if (q < 0) return;   // only register positive value of q
			remainingTime -= q; 
		}

		/**
		 * Generates a string that describes this job; useful for printing
		 * information about the job.
		 */
		public String toString() { 
			return "PID = " + pid + 
					"  Arrival Time = " + arrivalTime +
					"  Remaining Time = " + remainingTime +
					"  Departure Time = " + departureTime;                 
		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {

		System.out.println(" ");
		//Scanner in = new Scanner((System.in));
		Queue<Job> inputQueue = readJobs("input.txt");
		Queue<Job> processingQueue = new LinkedList<>();
		ArrayList<Job> terminatedJobs = new ArrayList<>();

		int t = 0;

		while((inputQueue.size()+processingQueue.size())>0){
			if(!(processingQueue.isEmpty())){
				Job current = processingQueue.peek();
				current.isServed(1);

				if(current.getRemainingTime() == 0){
					current.setDepartureTime(t);
					processingQueue.poll();
					terminatedJobs.add(current);
				}
				else{
					processingQueue.add(processingQueue.poll());
				}

			}
			if(!(inputQueue.isEmpty()) && 
					(inputQueue.peek().getArrivalTime() == t)){
				processingQueue.add(inputQueue.poll());
			}

			t++;
		}

		System.out.print("Average Time in System is: ");
		System.out.printf("%.2f",calculateTime(terminatedJobs));
		System.out.println("");
	}

	private static Queue<Job> readJobs(String fileName) throws NumberFormatException, IOException{

		Queue<Job> jobs = new LinkedList<>();
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int id = 1;

		String first;
		while((!(first = input.readLine()).equals(""))){			
			int at, st;

			String[] line = first.split(", ");
				at = Integer.parseInt(line[0]);
				st = Integer.parseInt(line[1]);

			//System.out.println(""+at);
			jobs.add(new Job(id, at, st));
			id++;
			//System.out.println("loop#: " +id);

		}
		input.close();
		//System.out.println("in closed");//for debugging

		return jobs;
	}

	private static double calculateTime(ArrayList<Job> jobList){
		double sum = 0;
		double amount = jobList.size();

		//total = dep-arriv
		for(Job job : jobList){
			int dep = job.getDepartureTime();
			int arv = job.getArrivalTime();

			sum += dep-arv;
		}

		return (sum/amount);
	}
}
