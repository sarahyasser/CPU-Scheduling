package schedulers;

import java.util.Scanner;

//import Process.Process;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the number of procceses");
		int num=scan.nextInt();
		Process[] process=new Process[num];
		for(int i=0;i<num;i++)
		{
			System.out.println("Enter process " + (i+1) + "'s arrival time: ");
			int arrivalTime=scan.nextInt();	
			System.out.println("Enter process " + (i+1) + "'s burst time: ");
			int burstTime=scan.nextInt();
			process[i] = new Process(i+1,arrivalTime, burstTime);
			
		
		}
		String choice="";
		while (!choice.equals("4"))               // Main menu
		{
			System.out.println("* * * * * * * * * * * * * * * * * * ");
		
		System.out.println("Choose the type of scheduler you want to use");
		System.out.println("1-FCFS");
		System.out.println("2-SJF");
		System.out.println("3-RR");
		System.out.println("4-EXIT");
		System.out.println("* * * * * * * * * * * * * * * * * * ");
		
		System.out.println("Enter your choice: ");
		choice = scan.next();
		switch(choice)

		{
		case"1":
			FirstComeFirstServe fcfs= new FirstComeFirstServe();
			fcfs.sortProcesses(process);
			fcfs.calculations(process);
			/*for(int i=0;i<num;i++)
			{
			     System.out.println("\nprocess"+(i+1)+"waiting time is"+process[i].getWaitingTime()+"burst time is"+process[i].getBurstTime()+"competion time is"+process[i].getCompletionTime()+"arrival  time is"+process[i].getArrivalTime());
			}*/
			break;
		case "2":
			//ShortestJobFirst SJF=new ShortestJobFirst();
			//SJF.sjf(process);
			break;
			
		case "3":
			System.out.println("Enter the quantum time:");
			int quantum=scan.nextInt();
			System.out.println("Enter the context time:");
			int context=scan.nextInt();
			RoundRobin rr=new RoundRobin(quantum,process,context);		
			
			
			
		}
		
	}

}
}
