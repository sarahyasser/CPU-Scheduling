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
		while (!choice.equals("6"))               // Main menu
		{
			System.out.println("* * * * * * * * * * * * * * * * * * ");
		
		System.out.println("Choose the type of scheduler you want to use");
		System.out.println("1-FCFS");
		System.out.println("2-SJF");
		System.out.println("3-RR");
		System.out.println("4-Priority (With Aging)");
		System.out.println("5-AG Scheduling");
		System.out.println("6-EXIT");
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
			System.out.println("Enter the context time:");
			int c=scan.nextInt();
			ShortestJobFirst SJF=new ShortestJobFirst(c);
			SJF.findWaitingTime(process);
			//SJF.sjf(process);
			break;
			
		case "3":
			System.out.println("Enter the quantum time:");
			int quantum=scan.nextInt();
			System.out.println("Enter the context time:");
			int context=scan.nextInt();
			RoundRobin rr=new RoundRobin(quantum,process,context);		
			break;
		
		case "4":
			for (int i = 0; i < process.length; i++) {
				System.out.println("Enter process " + (i+1) + "'s Priority: ");
				int priority = scan.nextInt();
				process[i].setPriority(priority);
			}
			Priority pr= new Priority();
			pr.sortProcessWithAging(process, num);
			break;
			
		case"5":
			for (int i = 0; i < process.length; i++) {
				System.out.println("Enter process " + (i+1) + "'s quantum time: ");
				int quantumAG = scan.nextInt();
				process[i].quantumTime = quantumAG;
				
				System.out.println("Enter process " + (i+1) + "'s Priority: ");
				int priority = scan.nextInt();
				process[i].setPriority(priority);
			}
			
			AG_Scheduling ag = new AG_Scheduling(process);
		
			ag.schedule();
			break;
			
			
			
		}
		
	}

}
}
