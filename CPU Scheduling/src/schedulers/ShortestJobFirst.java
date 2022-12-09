package os2;

public class ShortestJobFirst {
	
	public void sjf(Process process [])
	{
		
	   int completedProcesses=0;
       Process curProcess;
       curProcess=process[0];
       int curTimeInterval=0;
      while (completedProcesses<process.length)
      {
    	  for(int i=0;i<process.length;i++)
    	    { if(process[i].getBurstTime()>0)
    	    {
    	    	curProcess=process[i];
    	  break;
    	  }
    		  
    	  }
    	  for(int i=0;i<process.length;i++)
    	  {
    		
			if(process[i].getArrivalTime()>curTimeInterval||process[i].getTempBurstTime()==0)
			{
				continue;
			}
			if(process[i].getCompletionTime()<curProcess.getTempBurstTime())
			{
				curProcess=process[i];
			}
    	  }
    	 //curProcess.getTempBurstTime()= curProcess.getTempBurstTime()-1;
    	curProcess.tempBurstTime-=1;
    	if(curProcess.getTempBurstTime()==0)
    	{
    		completedProcesses++;
    		curProcess.completionTime=curTimeInterval+1;
    		
    	}
    	curTimeInterval++;
      }
      for(int i=0;i<process.length;i++)
      {
    	  process[i].setWaitingTime((process[i].getCompletionTime())-(process[i].getBurstTime())-(process[i].getArrivalTime()));
    	  process[i].setTurnAroundTime((process[i].getWaitingTime())-(process[i].getBurstTime()));
    	  System.out.println(process[i]);
      }
	}
	 

}
