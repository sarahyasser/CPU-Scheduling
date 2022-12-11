package schedulers;

import java.util.ArrayList;

//import trial.Process;

public class ShortestJobFirst 
{
	double avgT;
	double avgW;
	int contextTime;
	ArrayList<String> gantt=new ArrayList<String>();
	
	public ShortestJobFirst(int contextTime) {
		// TODO Auto-generated constructor stub
		this.contextTime=contextTime;
	}

	void findWaitingTime(Process process[])
	{
		for(int i=0;i<process.length;i++)
		{
			process[i].remainingBurstTime=process[i].burstTime;
		}
		int completedProcesses = 0;
		int timer = 0;
		int min = Integer.MAX_VALUE;
		int shortest = 0;
		int finish_time;
		int counter=0;
		Process temp;
		boolean found = false;
		
		while(completedProcesses<process.length)
		{
			//find the process with the minimum remaining time
			for (int i = 0; i < process.length; i++)
			{
				if ((process[i].arrivalTime <= timer) &&(process[i].remainingBurstTime < min) && process[i].remainingBurstTime > 0) {
					min = process[i].remainingBurstTime;
					shortest = i;
					found=true;
					counter++;
					
				}
			
			}
			gantt.add(process[shortest].getProcessID());//adding this process to gantt chart

			process[shortest].remainingBurstTime--;//decrementing the remaining burst time of the current process
			min = process[shortest].remainingBurstTime;
	
			if (process[shortest].remainingBurstTime == 0)//if the current process is executed
			
			{
				min = Integer.MAX_VALUE;
				completedProcesses++;//incrementing the completed processes
				finish_time = timer + 1;
				process[shortest].completionTime=finish_time;
					
			}
			if(counter==1)
				{timer++;}
			else{
				timer++;
				if(found)//if context switch happened 
					timer+=contextTime;
				
			}
			found=false;
		}
		calculations(process);
		}
	
	public void calculations(Process process[])
	{
		
		for(int i=0;i<process.length;i++)
		{   process[i].waitingTime=process[i].completionTime-process[i].burstTime-process[i].arrivalTime;
		    process[i].turnAroundTime=process[i].burstTime+process[i].waitingTime;
		    avgT+=process[i].getTurnAroundTime();
			avgW+=process[i].getWaitingTime();
		}
	
		print(process);
	}
	
		public void print(Process process[])
		{
			System.out.println(gantt);
			System.out.println("\npid  arrival  burst  complete turn  waiting");
			for(int i=0;i<process.length;i++)
			{
				System.out.println(process[i].getProcessID()+" \t "+process[i].getArrivalTime()+" \t"+process[i].getBurstTime()+" \t"+process[i].getCompletionTime()+
						"\t"+process[i].getTurnAroundTime()+" \t"+process[i].getWaitingTime());
			}
			
			System.out.println("\nAverage waiting time: "+ (avgW/process.length));     // printing average waiting time.
			System.out.println("Average turnaround time:"+(avgT/process.length)); 
		}
}



