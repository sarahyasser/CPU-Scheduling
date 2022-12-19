package schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Priority {
	
	
	private ArrayList<Process> processList = new ArrayList<Process>();
	private ArrayList<Process> completed = new ArrayList<Process>();
	private ArrayList<Process> sequence = new ArrayList<Process>();
	ArrayList<String> gantt=new ArrayList<String>();
	private double cummWait = 0.0;
	private double cummTurn = 0.0;
	
	Comparator<Process> comparator = new Comparator<Process>() {
	    @Override
	    
	    public int compare(Process p1, Process p2) {
	       
			Integer i1 = new Integer(p1.getArrivalTime());
				//	Integer(p1.getArrivalTime());
	      
			Integer i2 = new Integer(p2.getArrivalTime());
	        return i1.compareTo(i2);    
	    }
	};
	
	public void sortProcessWithAging(Process [] process,int numOfProcess)
	{
		for(int i = 0; i< numOfProcess;i++)
		{
			processList.add(process[i]);
		}
		
		Collections.sort(processList,comparator);
		int time = 0;
		int initalTime = processList.get(0).getArrivalTime();
		
		do
		{
			Process executing = processList.get(0);
			int index= 0;
			for (int i = 1; i <numOfProcess; i++)
			{
				Process p = processList.get(i);
				if(time>= p.getArrivalTime())
				{
					if(p.getPriority()<executing.getPriority()) //solving starvation problem (with aging)
					{
						executing.setWaitingTime(executing.getWaitingTime()+1);
						if(executing.getWaitingTime() %5 ==0) //if the waiting time of current process increases ,it gets a higher priority
						{
							executing.setPriority(executing.getPriority()-1);
						}
						executing= p;
						index= i;
					}
					else 
					{
						p.setWaitingTime(p.getWaitingTime()+1);
						if(p.getWaitingTime() %5 ==0)//if the waiting time of another process increases ,it gets a higher priority
						{
							p.setPriority(p.getPriority()-1);
						}
					}
					
				}
				else break;	
			}
			if(time >=executing.getArrivalTime())
			{
				sequence.add(executing);
				gantt.add(executing.processID);
				executing.remainingBurstTime=executing.remainingBurstTime-1;
				if(executing.remainingBurstTime==0)
				{
					completed.add(processList.get(index));
					
					processList.remove(index);
					numOfProcess--;
				}
			}
			time++;
		}
		while(numOfProcess>0);
		
		print(completed);
		
	}
	public void print(ArrayList<Process> arr)
	{
		System.out.println();
		System.out.println("Gantt Chart: "+gantt);
		System.out.println();
		
		System.out.println("\npid  arrival  burst  turna  waiting");
		for(int i = 0; i < completed.size(); i++)
		{
			int wTime = completed.get(i).waitingTime;
			int taTime = wTime + completed.get(i).getBurstTime();
			cummWait += wTime;
			cummTurn += taTime;
			System.out.println(completed.get(i).getProcessID()+" \t "+completed.get(i).getArrivalTime()+" \t"+completed.get(i).getBurstTime()+
					"\t"+taTime+" \t"+wTime);
		}
		
		System.out.println("\nAverage waiting time: "+ (cummWait/(double)completed.size()));     // printing average waiting time.
		System.out.println("Average turnaround time:"+(cummTurn/(double)completed.size())); 
	}
	
	
	
}
