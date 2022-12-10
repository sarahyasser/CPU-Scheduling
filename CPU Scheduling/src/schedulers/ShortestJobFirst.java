package schedulers;


//import trial.Process;

public class ShortestJobFirst 
{
	
	
	double avgT;
	double avgW;
	int contextTime;
	
	public ShortestJobFirst(int contextTime) {
		// TODO Auto-generated constructor stub
		this.contextTime=contextTime;
	}

	void findWaitingTime(Process process[])
{
		for(int i=0;i<process.length;i++)
		{
			process[i].tempBurstTime=process[i].burstTime;
		}
		int completedProcesses = 0, timer = 0, min = Integer.MAX_VALUE;
		int shortest = 0, finish_time;
		
		//boolean check = false;
		while(completedProcesses<process.length)
		{
			// Find process with minimum
			// remaining time among the
			// processes that arrives till the
			// current time`
			for (int i = 0; i < process.length; i++)
			{
				if ((process[i].arrivalTime <= timer) &&(process[i].tempBurstTime < min) && process[i].tempBurstTime > 0) {
					min = process[i].tempBurstTime;
					shortest = i;
					
				}
			}
			System.out.print((shortest+1)+" ");
			
			
	
		
			// Reduce remaining time by one
			//rt[shortest]--;
			process[shortest].tempBurstTime--;
	
			// Update minimum
			min = process[shortest].tempBurstTime;
		//	if (min == 0)
			//	min = Integer.MAX_VALUE;
			// If a process gets completely
			// executed
			if (process[shortest].tempBurstTime == 0)
			
			{
				min = Integer.MAX_VALUE;
				// Increment complete
				completedProcesses++;
				//check = false;
	
				// Find finish time of current
				// process
				finish_time = timer + 1;
				process[shortest].completionTime=finish_time;
	
				// Calculate waiting time
		
					
			}
			// Increment time
			timer++;
			timer+=contextTime;
		}
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
//yarab mykhodsh balo
	
