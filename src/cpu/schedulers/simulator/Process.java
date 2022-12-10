/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.schedulers.simulator;

/**
 *
 * @author future
 */
public class Process {
    int processId;

    
    String ProcessName;
    int ProcessArivalTime;
    public int ExecutionTime;//(brust)
    int WaitingTime;
    int TurnaroundTime; 
    public int CompletionTime;
    int ProcessPriority;//for Priority Scheduling
    
    public static int Burst;
    public int Quantm;
    public int RQ; 
    
    Process() {
        
    }
    public Process(Process p) {
		// TODO Auto-generated constructor stub
        this.ProcessName = p.getProcessName();
        this.ExecutionTime = p.getExecutionTime();
	this.ProcessArivalTime = p.getProcessArivalTime();
        this.ProcessPriority = p.getProcessPriority();
	this.WaitingTime = p.getWaitingTime();
	this.TurnaroundTime = p.getTurnaroundTime();
    }

    public Process(int ID,int ProcessArivalTime,int ExecutionTime,int ProcessPriority){
        this.processId = ID;
        this.ProcessArivalTime = ProcessArivalTime;
        this.ExecutionTime = ExecutionTime;
        this.ProcessPriority = ProcessPriority;
        WaitingTime = 0;
        TurnaroundTime = 0;
        CompletionTime = 0;
    }
    public Process(String ProcessName,int ProcessArivalTime,int ExecutionTime,int ProcessPriority){
        this.ProcessName = ProcessName;
        this.ProcessArivalTime = ProcessArivalTime;
        this.ExecutionTime = ExecutionTime;
        this.ProcessPriority = ProcessPriority;
        WaitingTime = 0;
        TurnaroundTime = 0;
        CompletionTime = 0;
    }

    //for AG
    Process(String name , int BurstTime , int ArrivalTime , int p ,  int Q){
	this.ProcessName  = name ;
	this.ExecutionTime = BurstTime;
	this.ProcessArivalTime =  ArrivalTime;
	this.ProcessPriority = p;
	this.Quantm = Q;
	this.RQ = Q;
	Process.Burst = BurstTime;
    }

    
    //Getters and Setters
    public void setProcessName(String ProcessName) {
        this.ProcessName = ProcessName;
    }

    public void setProcessArivalTime(int ProcessArivalTime) {
        this.ProcessArivalTime = ProcessArivalTime;
    }

    public void setExecutionTime(int ExecutionTime) {
        this.ExecutionTime = ExecutionTime;
    }

    public void setWaitingTime(int WaitingTime) {
        this.WaitingTime = WaitingTime;
    }

    public void setTurnaroundTime(int TurnaroundTime) {
        this.TurnaroundTime = TurnaroundTime;
    }

    public void setCompletionTime(int CompletionTime) {
        this.CompletionTime = CompletionTime;
    }

    public void setProcessPriority(int ProcessPriority) {
        this.ProcessPriority = ProcessPriority;
    }

    public String getProcessName() {
        return ProcessName;
    }

    public int getProcessArivalTime() {
        return ProcessArivalTime;
    }

    public int getExecutionTime() {
        return ExecutionTime;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public int getTurnaroundTime() {
        return TurnaroundTime;
    }

    public int getCompletionTime() {
        return CompletionTime;
    }

    public int getProcessPriority() {
        return ProcessPriority;
    }
    
    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }
    public void reduceTime(int time) {
        if(ExecutionTime >= time)
            ExecutionTime -= time;
    }
    public String toString() {
        return "{" +
                "process ID = " + processId +
                ", priority = " + ProcessPriority +
                ", arrivingTime = " + ProcessArivalTime +
                ", burstTime = " + ExecutionTime +
                '}';
    }

    public int turnaroundtime() {
		return (this.CompletionTime - this.ProcessArivalTime);
    }
    public int waitingtime() {
	return (this.CompletionTime - this.Burst - this.ProcessArivalTime);
		
    }
    
   
}
