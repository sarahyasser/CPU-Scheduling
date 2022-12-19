package schedulers;

public class Process {
    String processID;
    public int arrivalTime;
    public int burstTime; //executionTime
    public int waitingTime;
    public int completionTime;
    public int turnAroundTime;
    static int counter=0;
    public int remainingBurstTime;
    public int remainingQuantumTime;
    public int trial;

    public int t;
	int priority;
	int quantumTime;
    
    Process(int processID,int arrivalTime,int burstTime )
    {
    	this.processID="p"+processID;
    	this.arrivalTime=arrivalTime;
    	this.burstTime=burstTime;
    	this.remainingBurstTime=burstTime;
        counter++;
    	
    }
    Process(int processID,int arrivalTime,int burstTime,int quantumTime,int priority )
    {
    	this.processID="p"+processID;
    	this.arrivalTime=arrivalTime;
    	this.burstTime=burstTime;
    	this.quantumTime=quantumTime;
    	this.priority=priority;
        //counter++;
    	
    }
    public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
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
    public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getRemainingBurstTime() {
		return remainingBurstTime;
	}
	public void setRemainingBurstTime(int remainingBurstTime) {
		this.remainingBurstTime = remainingBurstTime;
	}
	public int getRemainingQuantumTime() {
		return remainingQuantumTime;
	}
	public void setRemainingQuantumTime(int remainingQuantumTime) {
		this.remainingQuantumTime = remainingQuantumTime;
	}
	public int getQuantumTime() {
		return quantumTime;
	}
	public void setQuantumTime(int quantumTime) {
		this.quantumTime = quantumTime;
	}



	
}
