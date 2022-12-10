package schedulers;
import java.lang.*;
import java.util.ArrayList;

public class AG_Scheduling {
	
	Process[] process;
	ArrayList<Process> ready= new ArrayList<Process>();
	ArrayList<String> ganttChart= new ArrayList<String>();
	
//	int min = Integer.MAX_VALUE;

	Process currentProcess;
	int timer =0;

	public AG_Scheduling(Process[] process) {
		this.process = process;
		currentProcess = null;
		
		for(int i=0;i<process.length;i++)
		{
			process[i].remainingBurstTime=process[i].getBurstTime();
			process[i].remainingQuantumTime=process[i].quantumTime;
		}
		
		sortProcesses();
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
		boolean switchFatoum= true;
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
			
			
			
			
			if(currentProcess == null) 
			{
				currentProcess = ready.remove(0);
			}
			
			if(currentProcess.remainingBurstTime <= 0)
			{
				currentProcess.quantumTime=0;
				currentProcess.remainingQuantumTime=0;
				currentProcess= ready.remove(0);
				continue;
				//tany msm3tsh lala msm3tkesh
			}
				
			
			if(switchFatoum)
			{
				ganttChart.add(currentProcess.getProcessID());	
			}
			
			
			if(!FCFS())
			{
				currentProcess = null;
				continue;
			}
						
			Process p = checkPriority();
			
			if(p != currentProcess)
			{
				switchFatoum = true;
				
				if(currentProcess.remainingBurstTime>0)//if process is not done yet,add it again to the ready queue to be processed 
				{
					System.out.println("add hna ya m3lm");
					ready.add(currentProcess);
				}	
						
				currentProcess.quantumTime += Math.ceil(currentProcess.remainingQuantumTime * 0.5);
			
				currentProcess = p;
				for(int i = 0;i<process.length;i++)
				{
					System.out.print( process[i].quantumTime+" ");
				}
				System.out.println();
				continue;
			}
			else
				switchFatoum = false;
			
			p = checkSJF();   
			
			if(p != currentProcess)
			{
				
				switchFatoum = true;

				
				if(currentProcess.remainingBurstTime>0)//if process is not done yet,add it again to the ready queue to be processed 
				{
					System.out.println("add hna ya m3lm");
					ready.add(currentProcess);
				}	
				
				currentProcess.quantumTime += currentProcess.remainingQuantumTime;
			
				currentProcess = p;
				for(int i = 0;i<process.length;i++)
				{
					System.out.print( process[i].quantumTime+" ");
				}
				System.out.println();
				continue;
			}
			else
				switchFatoum = false;
			
			
			for(int i = 0;i<process.length;i++)
			{
				System.out.print( process[i].quantumTime+" ");
			}
			System.out.println();
			
		}
		
		print();
	}
	
	
	public Process checkSJF()    
	{
		Process p = null;
		int min = Integer.MAX_VALUE;
		
		for(int i =0;i<ready.size();i++)
		{
			if (ready.get(i).remainingBurstTime < min && ready.get(i).remainingBurstTime > 0) 
			{
				min = ready.get(i).remainingBurstTime;
				p = ready.get(i);

			}
		}
		
		return p;		
	}
	
	public Process checkPriority()
	{
		Process p = null;
		int min = Integer.MAX_VALUE;
		
		for(int i =0;i<ready.size();i++)
		{
			if (ready.get(i).priority < min && ready.get(i).remainingBurstTime > 0) 
			{
				min = ready.get(i).priority;
				p = ready.get(i);

			}
		}
		
		return p;	
	}
	
	public boolean FCFS()
	{
		int counter=0;
		double rob3quantum = Math.ceil(0.25*currentProcess.quantumTime); 
		

		while((counter<rob3quantum)&& currentProcess.remainingBurstTime>0 && currentProcess.quantumTime >0)
		{
			currentProcess.remainingBurstTime-=1;				
			timer+=1;
			counter++;
			checkArrival(timer,process);
			currentProcess.remainingQuantumTime--;
			
			if(currentProcess.remainingQuantumTime <= 0 && currentProcess.remainingBurstTime>0)
			{
				currentProcess.quantumTime+=2;
				ready.add(currentProcess);
				return false;
			}
			
			
		}
	/*	
		if(currentProcess.quantumTime <= 0)
		{
			currentProcess.quantumTime+=2;
		}
	*/	
		currentProcess.completionTime=timer; 
		
		return true;
	//	currentProcess.remainingQuantumTime -= Math.ceil(0.25*currentProcess.quantumTime);
	}
	
	
	public void checkArrival(int timer, Process[] process) {
	
		for(int i=0;i<process.length;i++)
		{
			if(process[i].arrivalTime==timer)
			{
				ready.add(process[i]);
			}
		}
		
	}
	
	public void print()
	{
		System.out.println(ganttChart);
		System.out.println("\npid  arrival  burst  completion  turn  waiting  quantum"); // shut up // most7el
		for(int i=0;i<process.length;i++)
		{
			System.out.println(process[i].getProcessID()+" \t "+process[i].getArrivalTime()+" \t"+process[i].getBurstTime()+" \t"+process[i].getCompletionTime()+
					"\t"+process[i].getTurnAroundTime()+" \t"+process[i].getWaitingTime()+" \t"+process[i].quantumTime+ " \t"+process[i].remainingQuantumTime);
		}
		
		/*
		System.out.println("\nAverage waiting time: "+ (avgW/process.length));     // printing average waiting time.
		System.out.println("Average turnaround time:"+(avgT/process.length)); 
		*/
	}


	
}
