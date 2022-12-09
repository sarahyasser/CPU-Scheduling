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
		for(int i=0;i<process.length;i++)
		{
			for(int j=0;j<process.length;j++)
			{
				if(process[i].getArrivalTime()<process[j].getArrivalTime())
				{
					temp=process[i];
					process[i]=process[j];
					process[j]=temp;
				}
			}
			
		}
		
	}
	
	public void calculations(Process[]process) //mmken nb2a n2asem kol hesba fe method
	{   int sum=0;
		for(int i=0;i<process.length;i++)
		{
			
			sum=sum+process[i].getBurstTime();
			process[i].setCompletionTime(sum);
			process[i].setTurnAroundTime((process[i].getCompletionTime())-(process[i].getArrivalTime()));
			process[i].setWaitingTime((process[i].getTurnAroundTime())-(process[i].getBurstTime()));
			/*System.out.println("\nprocess :"+process[i].getProcessID()+"arrival  time is "+process[i].getArrivalTime()+"burst time is "+process[i].getBurstTime()+"waiting time is "+process[i].getWaitingTime()+"competion time is "+process[i].getCompletionTime()+"turn around time is "+process[i].getTurnAroundTime());*/
			System.out.println(process[i]);
		}
	}
	
	
	
}
