package cpu.schedulers.simulator;

//import model.GanttRecord;
//import model.Process;
//import model.ReadyQueue;

import java.util.ArrayList;

public class PreemptiveScheduling {
    //Gantt
    private ArrayList<GanttRecord> gantt;
    private int currentTime;
    private int exeTime;
    private ReadyQueue readyQueue;

    public PreemptiveScheduling(){
        gantt = new ArrayList<>();
        currentTime = 0;
        exeTime = 0;
        readyQueue = new ReadyQueue();
    }

    public ArrayList<GanttRecord> getGantt(ArrayList<Process> processes){
        
        currentTime = this.getFirstArrivingTime(processes);
        int in = currentTime ,out = currentTime;

        
        ArrayList<Process> processes1 = this.getFirstArrivingProcesses(processes);

        for(Process process : processes1){
            
            readyQueue.enqueue(process);
            processes.remove(process);
        }

        
        ArrayList<Process> orderedByArrivingTime = this.orderProcessesByArrivingTime(processes);

        
        while(!readyQueue.isEmpty()){
            
            Process process = readyQueue.dequeue();


            
            if(orderedByArrivingTime.size() > 0) {
                
                for (int i = 0; i < orderedByArrivingTime.size(); i++) {
                    Process p = orderedByArrivingTime.get(i);
                    
                    if (p.getProcessArivalTime()>= process.getProcessArivalTime()
                            && p.getProcessArivalTime() < (process.getExecutionTime()+ currentTime)
                            && p.getProcessPriority()>= process.getProcessPriority()) {
                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;
                    }
                    
                    else if (p.getProcessArivalTime() >= process.getProcessArivalTime()
                            && p.getProcessArivalTime() < (process.getExecutionTime() + currentTime)
                            && p.getProcessPriority() < process.getProcessPriority()) {
                        in = currentTime;
                        currentTime = p.getProcessArivalTime();
                        process.reduceTime(currentTime - in);
                        out = currentTime;
                        readyQueue.enqueue(process);
                        GanttRecord gR = new GanttRecord(in, out, process.getProcessId());
                        gantt.add(gR);

                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;

                        break;
                    }
                    
                    if (i == orderedByArrivingTime.size() - 1) {
                        in = currentTime;
                        currentTime += process.getExecutionTime();
                        out = currentTime;
                        gantt.add(new GanttRecord(in, out, process.getProcessId()));
                        //kontrollohet nese ne fund te ekzekutimit pa nderprerje te nje procesi kemi nje proces te ri qe
                        //vjen dhe shtohet ne radhen gati
                        if(orderedByArrivingTime.size() > 0
                                && readyQueue.isEmpty()) {
                            readyQueue.enqueue(orderedByArrivingTime.get(0));
                        }
                    }
                }
            }
            //rasti tjeter kur nuk kemi me procese te reja qe vijojne por trajtohen vetem ato qe ndodhen ne radhen gati
            else{
                in = currentTime;
                currentTime += process.getExecutionTime();
                out = currentTime;
                gantt.add(new GanttRecord(in, out, process.getProcessId()));
            }
        }
        return gantt;
    }

    public static int getCompletionTime(Process p, ArrayList<GanttRecord> gantt) {
        int completionTime = 0;
        for(GanttRecord gR : gantt){
            if(gR.getProcessId() == p.getProcessId())
                completionTime = gR.getOutTime();
        }
        return completionTime;
    }

    public static int getTurnAroundTime(Process p, ArrayList<GanttRecord> gantt) {
        int completionTime = PreemptiveScheduling.getCompletionTime(p,gantt);
        return completionTime-p.getProcessArivalTime();
    }

    public static int getWaitingTime(Process p, ArrayList<GanttRecord> gantt) {
        int turnAroundTime = PreemptiveScheduling.getTurnAroundTime(p,gantt);
        return turnAroundTime-p.getExecutionTime();
    }

    private ArrayList<Process> orderProcessesByArrivingTime(ArrayList<Process> processes){
        ArrayList<Process> newProcesses = new ArrayList<>();
        while(processes.size() != 0) {
            Process p = this.getFirstArrivingProcess(processes);
            processes.remove(p);
            newProcesses.add(p);
        }
        return newProcesses;
    }

    private Process getFirstArrivingProcess(ArrayList<Process> processes){
        int min = Integer.MAX_VALUE;
        Process process = null;
        for(Process p : processes){
            if(p.getProcessArivalTime()< min){
                min = p.getProcessArivalTime();
                process = p;
            }
        }
        return process;
    }

    private ArrayList<Process> getFirstArrivingProcesses(ArrayList<Process> processes){
        int min = this.getFirstArrivingTime(processes);
        ArrayList<Process> processes1 = new ArrayList<>();
        for(Process p : processes){
            if(p.getProcessArivalTime() == min){
                processes1.add(p);
            }
        }
        return processes1;
    }

    private int getFirstArrivingTime(ArrayList<Process> processes){
        int min = Integer.MAX_VALUE;
        for(Process p : processes){
            if(p.getProcessArivalTime() < min){
                min = p.getProcessArivalTime();
            }
        }
        return min;
    }

}