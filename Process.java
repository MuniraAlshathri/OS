
public class Process 
{
    String pID; //Process id in form of PN
    int processPriority; //process priority from 1 - 5 (the lower the value the higher the prority)
    int arrivalT;//The time the process enters into the ready queue.
    int burst;//time a process needs to execute.
    int startT; // The start time of process execution 
    int terminationT;//and the completion time
    int turnAroundT;//The amount of time to execute a process.
    int waitingT;//The amount of time a process has been waiting in the ready queue.
    int responseT;//The amount of time it takes from when a request was submitted until the first response is produced.
    int remaningT;
    
    public Process()
    {
        startT = -1;
        terminationT = -1;      
    }
    
    public void calculateWaitingTime()
    {
        waitingT = turnAroundT - burst;
    }
    
    public void calculateTurnAroundT()
    {
        turnAroundT = terminationT - startT;
    }
    
    public void calculateResponseTime()
    {
        responseT = startT - arrivalT;
    }
    
    public void setpID(int i) 
    {
        pID = "P" +(i+1);
    }
    
    public String getpID() {return this.pID;}
    
    
    
    public int getburst() {return this.burst;}
    
    public void setburst( int bur) {this.burst = bur;}
    
    public int getarrivalT() {return this.arrivalT;}
    
    public void setarrivalT( int Arrive) {this.arrivalT = Arrive;}
    
    public int getPriority() {return this.processPriority;}
    public void setPriority( int priority) {this.processPriority = priority;}
    
    public int getStart_time() {
    return this.startT;
    }
    public void setStart_time(int start) {
    this.startT = start;
    }
    public int getEnd_time() {
    return this.terminationT;
    }
    public void setEnd_time( int terminationT) {
    this.terminationT = terminationT;
    }
    public int getTotalTime() {
    return this.turnAroundT;
    }
    public void setTotalTime( int totatTime) {
    this.turnAroundT = totatTime;
    }
    public int getWaiting_T() {
    return this.waitingT;
    }
    public void setwaitingT( int wating) {
    this.waitingT = wating;
    }
    public int getResponse() {
    return this.responseT;
    }
    public void setResponse( int response_T) {
    this.responseT = response_T;
    }
    public int getremaningTT() {
    return this.remaningT;
    }
    public void setRemainT( int remain) {
    this.remaningT = remain;
    }
}


