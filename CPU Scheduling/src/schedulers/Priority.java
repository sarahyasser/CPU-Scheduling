package schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;import javax.swing.tree.DefaultTreeCellEditor;
public class Priority {
	
	
	private ArrayList<Process> processList = new ArrayList<Process>();
	private ArrayList<Process> completed = new ArrayList<Process>();
	private ArrayList<Process> sequence = new ArrayList<Process>();
	private double cummWait = 0.0;
	private double cummTurn = 0.0;
	
	Comparator<Process> comparator = new Comparator<Process>() {
	    @Override
	    
	    public int compare(Process p1, Process p2) {
	        Integer i1 = new Integer(p1.getArrivalTime());
	        Integer i2 = new Integer(p2.getArrivalTime());
	        return i1.compareTo(i2);    
	    }
	};
	
	public void PrintCalculations()
	{

		System.out.println("\n\nWaiting Times and Turnaround Times:");
		for(int i = 0; i < completed.size(); i++)
		{
			int wTime = completed.get(i).waitingTime;
			int taTime = wTime + completed.get(i).getBurstTime();
			cummWait += wTime;
			cummTurn += taTime;
			System.out.println("\nProcess " + completed.get(i).getProcessID() + " stats: ");
            System.out.println("Waiting time: " + wTime);
            System.out.println("Turnaround time: " + taTime);
		}
		System.out.println("\n\nAverage waiting time: ");
		System.out.println(cummWait/(double)completed.size());
		
		System.out.println("\n\nAverage turnaround time: ");
		System.out.println(cummTurn/(double)completed.size());
	}
	
	
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
					if(p.getPriority()<executing.getPriority())
					{
						executing.setWaitingTime(executing.getWaitingTime()+1);
						if(executing.getWaitingTime() %5 ==0)
						{
							executing.setPriority(executing.getPriority()-1);
						}
						executing= p;
						index= i;
					}
					else 
					{
						p.setWaitingTime(p.getWaitingTime()+1);
						if(p.getWaitingTime() %5 ==0)
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
				executing.setTempBurstTime(executing.getTempBurstTime()-1);
				if(executing.getTempBurstTime()==0)
				{
					completed.add(processList.get(index));
					processList.remove(index);
					numOfProcess--;
				}
			}
			time++;
		}
		while(numOfProcess>0);
		System.out.println("\nExecution Timeline:\n");
		for(int i = 0; i < sequence.size(); i++)
		{
			System.out.println("Process: " + sequence.get(i).getProcessID() + " ||| Time: " + (initalTime+i));
            System.out.println("-------------------------------------");
		}
		PrintCalculations();
		
	}
	
	
	
}
