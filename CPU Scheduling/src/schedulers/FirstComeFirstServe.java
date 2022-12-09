package schedulers;

public class FirstComeFirstServe {

	//Process process;
	/*FirstComeFirstServe(Process process)
	{
		this.process=process;
	}*/
	public void sortProcesses(Process process[])
	{   
		Process temp; //to swap
		for(int i=0;i<(process.length);i++)
		{
			for(int j=0;j<(process.length-(i+1));j++)
			{
				if(process[j].getArrivalTime()>process[j+1].getArrivalTime())
				{
					temp=process[j];
					process[j]=process[j+1];
					process[j+1]=temp;
				}
			}
			
		}
		
	}
	public void completionTime(Process process[])
	{
		int sum=0;
		sum=sum+process[0].getBurstTime()+process[0].getArrivalTime();
		process[0].setCompletionTime(sum);
		for(int i=1;i<process.length;i++)
		{
			if(process[i].getArrivalTime()>process[i-1].getCompletionTime())
			{
				process[i].setCompletionTime(process[i].getArrivalTime()+process[i].getBurstTime());
			}
			else
			{
				process[i].setCompletionTime(process[i-1].getCompletionTime()+process[i].getBurstTime());
			}
			
//			while(sum<process[i].getBurstTime())
//			{
//				sum++;
//				System.out.println("here");
//			}
//			sum=sum+process[i].getBurstTime();
//			process[i].setCompletionTime(sum);
		}
	}
	public void calculations(Process[]process) //mmken nb2a n2asem kol hesba fe method
	{  
		double avgW=0;
		double avgT=0;
		completionTime(process);
		for(int i=0;i<process.length;i++)
		{
			process[i].setTurnAroundTime((process[i].getCompletionTime())-(process[i].getArrivalTime()));
			process[i].setWaitingTime((process[i].getTurnAroundTime())-(process[i].getBurstTime()));
			/*System.out.println("\nprocess :"+process[i].getProcessID()+"arrival  time is "+process[i].getArrivalTime()+"burst time is "+process[i].getBurstTime()+"waiting time is "+process[i].getWaitingTime()+"competion time is "+process[i].getCompletionTime()+"turn around time is "+process[i].getTurnAroundTime());*/
			//System.out.println(process[i]);
			avgT+=process[i].getTurnAroundTime();
			avgW+=process[i].getWaitingTime();
		}
		print(process, avgT, avgW);
	}
	public void print(Process process[],double avgT,double avgW)
	{
		System.out.println("\npid  arrival  burst  complete turn waiting");
		for(int i=0;i<process.length;i++)
		{
			System.out.println(process[i].getProcessID()+" \t "+process[i].getArrivalTime()+" \t"+process[i].getBurstTime()+" \t"+process[i].getCompletionTime()+
					"\t"+process[i].getTurnAroundTime()+" \t"+process[i].getWaitingTime());
		}
		
		System.out.println("\nAverage waiting time: "+ (avgW/process.length));     // printing average waiting time.
		System.out.println("Average turnaround time:"+(avgT/process.length)); 
	}
	
	
	
}
