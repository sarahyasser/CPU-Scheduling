package schedulers;


public class ShortestJobFirst 
{


	double avgT;
	double avgW;
	int contextTime;

	public ShortestJobFirst(int contextTime) {
		// TODO Auto-generated constructor stub
		this.contextTime=contextTime;
	}

	void completionTime(Process process[])
{
		for(int i=0;i<process.length;i++)
		{
			process[i].tempBurstTime=process[i].burstTime;
		}
		int completedProcesses = 0;
		int timer = 0;
		int min = Integer.MAX_VALUE;
		int shortest = 0;
		int finish_time;

		
		while(completedProcesses<process.length) 
		{
			
			for (int i = 0; i < process.length; i++) //finding the process with the least remaining time "tempBurstTime"
			{
				if ((process[i].arrivalTime <= timer) &&(process[i].tempBurstTime < min) && process[i].tempBurstTime > 0) {
					min = process[i].tempBurstTime;
					shortest = i;

				}
			}
		
          process[shortest].tempBurstTime--; //decrementing the burst time of the current process
          min = process[shortest].tempBurstTime;

			if (process[shortest].tempBurstTime == 0) //if the current process is executed

			{
				min = Integer.MAX_VALUE;
				completedProcesses++;//incrementing the completed processes
				finish_time = timer + 1;
				process[shortest].completionTime=finish_time;

			}

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
