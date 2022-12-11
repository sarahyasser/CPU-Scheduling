package schedulers;
import java.lang.*;
import java.net.SecureCacheResponse;
import java.util.ArrayList;


public class AG_Scheduling 
{
    //ArrayList<Process> processes = new ArrayList<>();
    ArrayList<Process> ready = new ArrayList<>();
    ArrayList<String> grantChart = new ArrayList<>();
    Process[] process;
    double avgW=0,avgT=0;
    int smallest=0;
    int timer=0;
    int completeProcesses=0;
    int casee = 0;
    int min= 0;  // to get the ndex of process with min priority 
    Process tempProcess = null;
    Process currentProcess = null;
    
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
    public void printQuarter()
    {
    	System.out.print("\nQuantum(");
        for (int i = 0; i < process.length; i++) {
            if (i != 0)
                System.out.print(',');
            System.out.print(process[i].getQuantumTime());
        }
        System.out.print(")-> ceil(25%) = (");
        for (int i = 0; i < process.length; i++) {
            if (i != 0)
                System.out.print(',');
            if(currentProcess.getProcessID().equals(process[i].getProcessID())) {
                System.out.print((int) Math.ceil(currentProcess.getQuantumTime() / 4.0));
            }
            else{
                System.out.print('-');
            }
        }
        System.out.print(')');
    }
    public void printHalf(int half)
    {
    	System.out.print(" && ceil(50%) = (");
        for (int i = 0; i < process.length; i++) {
            if (i != 0)
                System.out.print(',');
            if(currentProcess.getProcessID().equals(process[i].getProcessID())) {
                System.out.print(half);
            }
            else{
                System.out.print('-');
            }
        }
        System.out.print(')');
    }
    
    public void FCFS()
    {
        if(currentProcess==null)
        {
        	currentProcess= ready.remove(0);
        }
        int quarter = Math.min(currentProcess.getRemainingBurstTime(), (int) Math.ceil(currentProcess.getQuantumTime()*0.25));
        printQuarter();
        currentProcess.remainingQuantumTime=   currentProcess.getRemainingQuantumTime() - quarter;
        currentProcess.remainingBurstTime=   currentProcess.getRemainingBurstTime() - quarter;
        timer += quarter;
        grantChart.add(currentProcess.getProcessID());
       
    }
    
    
    public void checkArrival()
    {
    	 while (smallest < process.length && process[smallest].getArrivalTime() <= timer) {
             ready.add(process[smallest]);
             smallest++;
         }
         if(ready.isEmpty())
         {
         	timer++;
         }
    	
    }
    
    public void checkPriority()
    {
        for (int i = 0; i < ready.size(); i++) {
            if (ready.get(i).getPriority() < currentProcess.getPriority()) {
                currentProcess = ready.get(i);
                min = i;
            }
        }
    }
    
    public void SJF()
    {
    	
        for (int i = 0; i < ready.size(); i++) 
        {
            if (ready.get(i).getRemainingBurstTime() < currentProcess.getRemainingBurstTime()) {
                currentProcess = ready.get(i);
                min = i;
            }
        }
    
    
    }
    public void calculations()
    {
    	 for(int i=0;i<process.length;i++)
         {
    		 process[i].turnAroundTime=process[i].completionTime-process[i].arrivalTime;
    		 avgT+=process[i].turnAroundTime;
    		 process[i].waitingTime=process[i].turnAroundTime-process[i].burstTime;
    		 avgW+=process[i].waitingTime;
         }
    }
    public void print()
    {
    	
    	System.out.println("\n");
        System.out.println("Gantt Chart:"+grantChart);
        System.out.println("\npid  arrival  burst  completion  turn  waiting  quantum  "); // shut up // most7el
        for(int i=0;i<process.length;i++)
        {
        	
            System.out.println(process[i].getProcessID()+" \t "+process[i].getArrivalTime()+" \t"+process[i].getBurstTime()+" \t"+process[i].getCompletionTime()+
                    " \t  "+process[i].getTurnAroundTime()+"  \t "+process[i].getWaitingTime()+"  \t "+process[i].quantumTime);
        }
        System.out.println("\nAverage waiting time: "+ (avgW/process.length));     // printing average waiting time.
		System.out.println("Average turnaround time:"+(avgT/process.length)); 


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
    public void completeProcess()
    {
    	currentProcess.quantumTime=0;
        currentProcess.remainingQuantumTime=0;
        currentProcess.completionTime=timer;
        completeProcesses++;
        tempProcess = currentProcess;
        currentProcess = null;
        casee=0;
    }
    public void schedule()
    {
    	
        while (completeProcesses < process.length)
        {
        	while (smallest < process.length && process[smallest].getArrivalTime() <= timer) {
                ready.add(process[smallest]);
                smallest++;
            }
            if(ready.isEmpty())
            {
            	timer++;
            }
            if (casee== 0)
            {
            	FCFS();
                if (currentProcess.getRemainingBurstTime() == 0)
                {
                    completeProcess();
                    continue;
                }  
                tempProcess = currentProcess;
                casee = 1;
                
            }
            else if (casee==1)
            {
            	checkPriority();
            	 if (currentProcess!=tempProcess) {
            		 casee = 0;
                     tempProcess.quantumTime=tempProcess.getQuantumTime() + (int) Math.ceil(tempProcess.getRemainingQuantumTime() / 2.0);
                     tempProcess.remainingQuantumTime=   tempProcess.getQuantumTime();
                     ready.remove(min);
                     ready.add(tempProcess);
                     continue;
                 }
                 int half = (int) Math.ceil(currentProcess.getQuantumTime() / 2.0);
                 int spent = Math.min(currentProcess.getRemainingBurstTime(), half - (currentProcess.getQuantumTime() - currentProcess.getRemainingQuantumTime()));
                 printHalf(half);
                 currentProcess.remainingQuantumTime=  currentProcess.getRemainingQuantumTime() - spent;
                 currentProcess.remainingBurstTime=  currentProcess.getRemainingBurstTime() - spent;
                 timer+= spent;
                 
                 if (currentProcess.getBurstTime() == 0)
                 {
                     completeProcess();
                     continue;
                 }  
                 casee = 2;
                 tempProcess = currentProcess;
            }
            else 
            {
            	min=0;
            	SJF();
            	 if (currentProcess!=tempProcess)
            	 {
            		 casee = 0;
                     tempProcess.quantumTime=tempProcess.getQuantumTime() + tempProcess.getRemainingQuantumTime();
                     tempProcess.remainingQuantumTime=   tempProcess.getQuantumTime();
                     ready.remove(min);
                     ready.add(tempProcess);
                
                     continue;
   
            	 }
            	   currentProcess.remainingQuantumTime=  currentProcess.getRemainingQuantumTime() - 1;
            	   currentProcess.remainingBurstTime=  currentProcess.getRemainingBurstTime() - 1;
                   timer++;
                   if (currentProcess.getRemainingBurstTime() == 0)
                   {
                       completeProcess();
                       continue;
                   }  
                   tempProcess = currentProcess;
              
            }
        }
        calculations();
        print() ;
        }
}
