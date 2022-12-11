/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.schedulers.simulator;

import cpu.schedulers.simulator.Priority_Scheduling.compareTwoProcess;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author future
 */
public class preemptive_Priority_Scheduling {
    Scanner input=new Scanner(System.in);
    Scanner str=new Scanner(System.in);
    int ProcessArivalTime;
    int ExecutionTime;
    int ProcessPriority;
    int AgingTime = 0;
    
    
    class compareTwoProcess implements Comparator<Object>
        {
            public int compare(Object pr1,Object pr2){
            Process process1 = (Process)pr1;
            Process process2 = (Process)pr2;
            
            if(process1.getProcessArivalTime()<process2.getProcessArivalTime()){
                return -1;
            }else if((process1.getProcessPriority()<process2.getProcessPriority()) && (process1.getProcessArivalTime() == process2.getProcessArivalTime())){
                return -1;
            }else{
                return 1;
            }
            }
        }
        TreeSet<Process> ts = new TreeSet<Process>(new compareTwoProcess());
    public preemptive_Priority_Scheduling(){
        //An Iterator is an object that can be used to loop through collection of process
        Iterator<Process> iterator = ts.iterator();

        ArrayList<Process> processes = new ArrayList<Process>();
        float avgWait = 0;
        float avgTurnAround = 0;
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        
        System.out.println("Enter processes properties. ");
//        String name;
        int  IDD;
        for(int i=0;i<n;i++) {
            Process process = new Process();
            
            System.out.print("Enter process ID for : ");
            IDD = input.nextInt();
            process.setProcessId(IDD);
            
            
            System.out.print("Enter Burst Time of Processes: ");
            ExecutionTime = input.nextInt();
            process.setExecutionTime(ExecutionTime);
            
            
            System.out.print("Enter Priority Time of Processes: ");
            ProcessPriority = input.nextInt();
            process.setProcessPriority(ProcessPriority);
            
            
            System.out.print("Enter Arrival Time of Processes: ");
            ProcessArivalTime = input.nextInt();
            process.setProcessArivalTime(ProcessArivalTime);
            
            
            AgingTime+=process.getExecutionTime();
            if(AgingTime>=10){
                while(iterator.hasNext()){
                    Process temp = (Process)iterator.next();
                    temp.setProcessPriority(temp.getProcessPriority()-1);
                }
                AgingTime%=10;
            }
            processes.add(process);
        }
        

        ArrayList<Process> processesCpy = copyProcesses(processes);
        ArrayList<GanttRecord> gantt = new PreemptiveScheduling().getGantt(processes);

        //all processes
        System.out.println("List of all processes.");
        for(Process p : processesCpy){
            System.out.println(p.toString());
        }
        System.out.println();

        //gantt chart
        System.out.println("GANTT chart");
        for(int i = 0 ; i < gantt.size() ; i++){
            GanttRecord gR = gantt.get(i);
            if(i == 0)
                System.out.print(gR.toString());
            else
                System.out.print(" --P" + gR.getProcessId() + "-- |" + gR.getOutTime() +"|");
        }
        System.out.println();
        System.out.println();

        //completion time(koha e perfundimit)
        System.out.println("Completion Time");
        for(Process p : processesCpy){
            int completionTime = PreemptiveScheduling.getCompletionTime(p, gantt);
            System.out.println("P" + p.getProcessId()+": t = " + completionTime);
        }

        //turn around time(koha e qendrimit)
        System.out.println("Turn Around Time");
        for(Process p : processesCpy){
            int turnAroundTime = PreemptiveScheduling.getTurnAroundTime(p, gantt);
            System.out.println("P" + p.getProcessId()+": t = " + turnAroundTime);
            avgTurnAround += turnAroundTime;
        }
        avgTurnAround = avgTurnAround / processesCpy.size();

        //waiting time(koha e pritjes)
        System.out.println("Waiting Time");
        for(Process p : processesCpy){
            int waitingTime = PreemptiveScheduling.getWaitingTime(p, gantt);
            System.out.println("P" + p.getProcessId()+": t = " + waitingTime);
            avgWait += waitingTime;
        }
        avgWait = avgWait / processesCpy.size();

        System.out.println("Average Turn Around Time : " + avgTurnAround);
        System.out.println("Average Waiting Time : " + avgWait);
    }
    
    public static ArrayList<Process> copyProcesses(ArrayList<Process> processes){
        ArrayList<Process> processesCpy = new ArrayList<>();
        for(Process p : processes){
            processesCpy.add(new Process(p.getProcessId(),p.getProcessArivalTime(),p.getExecutionTime(),p.getProcessPriority()));
        }
        return  processesCpy;
    }
}
