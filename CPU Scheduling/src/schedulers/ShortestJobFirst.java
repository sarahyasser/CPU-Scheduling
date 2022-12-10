package schedulers;

public class ShortestJobFirst {
    
    public void sjf(Process process [])
    {
        
       int completedProcesses=0;
       Process curProcess;
       curProcess=process[0];
       int curTimeInterval=0; //current time interval of the current executing process
      while (completedProcesses<process.length)
      {
          for(int i=0;i<process.length;i++)
            { 
              if(process[i].getTempBurstTime()>0) //lw el burst time bta3 el process de akbar mn zero y3ny lesa mkhlsh
            {
                curProcess=process[i];//fa el current process hye de
          break;
          }
              
          }
          for(int i=0;i<process.length;i++)
          {
            
            if(process[i].getArrivalTime()>curTimeInterval||process[i].getTempBurstTime()==0)
            {
                continue;
            }
            if(process[i].getCompletionTime()<curProcess.getCompletionTime())
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