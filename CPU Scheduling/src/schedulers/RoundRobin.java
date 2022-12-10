package schedulers;

import java.util.ArrayList;

public class RoundRobin {

	int quantum;
	Process[] process;
	int context;
	double avgT=0;
	double avgW=0;
	ArrayList<Process> ready= new ArrayList<Process>();
	ArrayList<String> ganttChart= new ArrayList<String>();
	public RoundRobin(int quantum, Process[] process, int context) {
		this.quantum=quantum;
		this.context=context;
		this.process=process;
		for(int i=0;i<process.length;i++)
		{
			process[i].remainingBurstTime=process[i].getBurstTime();
		}
		sortProcesses();
		schedule();
		calc();
		print();
	}
	public void sortProcesses()
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
	public void schedule()
	{
		int timer=0;
		while(timer<process[0].arrivalTime)
		{
			timer++;
		}
		ready.add(process[0]);
		while (true)
		{
			
			boolean flag =true;
			//to break the while loop
			for(int i=0;i<process.length;i++)
			{
				if(process[i].remainingBurstTime!=0)
				{
					flag=false;
					break;
				}
			}
			if(flag)break;//to break the while loop
			
			
			Process p=ready.remove(0);//get process from ready queue
			System.out.print(p.getProcessID()+"  ");
			int counter=0;
			while((counter<quantum)&& p.remainingBurstTime>0)
			{
				p.remainingBurstTime-=1;
				timer+=1;
				counter++;
				checkArrival(timer,process);
			}
			p.completionTime=timer;
			if(p.remainingBurstTime!=0)//if process is not done yet,add it again to the ready queue to be processed 
			{
				ready.add(p);
			}
			counter=0;
			while(counter<context)//add context time and check if any process arrived at that time
			{
				timer+=1;
				checkArrival(timer,process);
				counter++;
				
			}
			
		}
	}
	
	
	private void checkArrival(int timer, Process[] process) {
	
		for(int i=0;i<process.length;i++)
		{
			if(process[i].arrivalTime==timer)
			{
				ready.add(process[i]);
			}
		}
		
	}
	
	public void calc()
	{
		
		for(int i=0;i<process.length;i++)
		{
			process[i].turnAroundTime=process[i].completionTime-process[i].arrivalTime;
			process[i].waitingTime=process[i].turnAroundTime-process[i].burstTime;
			avgT+=process[i].getTurnAroundTime();
			avgW+=process[i].getWaitingTime();
		}
	}
	
	public void print()
	{
		System.out.println("\npid  arrival  burst  completion  turn  waiting");
		for(int i=0;i<process.length;i++)
		{
			System.out.println(process[i].getProcessID()+" \t "+process[i].getArrivalTime()+" \t"+process[i].getBurstTime()+" \t"+process[i].getCompletionTime()+
					"\t"+process[i].getTurnAroundTime()+" \t"+process[i].getWaitingTime());
		}
		System.out.println("\nAverage waiting time: "+ (avgW/process.length));     // printing average waiting time.
		System.out.println("Average turnaround time:"+(avgT/process.length)); 
	}

}
