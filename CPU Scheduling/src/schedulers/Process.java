package schedulers;

public class Process {
    int processID;
    int arrivalTime;
    int burstTime; //executionTime
    int waitingTime;
    int completionTime;
    int turnAroundTime;
    //int tempBurstTime;
    static int counter=0;
    
    Process(int processID,int arrivalTime,int burstTime )
    {
    	this.processID=processID;
    	this.arrivalTime=arrivalTime;
    	this.burstTime=burstTime;
    	//this.tempBurstTime=burstTime;
        counter++;
    	
    }
    public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public int getTurnAroundTime() {
		return turnAroundTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}
	 @Override
	    public String toString() {
	        return "\nProcess"+processID+"{" + "\n\tprocessID=" + processID + "\n\tarrivalTime=" + arrivalTime
	                + "\n\tburstTime=" + burstTime + "\n\tCompletion time=" + completionTime+"\n\twaitingTime=" + waitingTime + "\n\tturnaroundTime=" + turnAroundTime + "\n}";
	    }
//	public int getTempBurstTime() {
//		return tempBurstTime;
//	}
//	public void setTempBurstTime(int tempBurstTime) {
//		this.tempBurstTime = tempBurstTime;
//	}


	
}
