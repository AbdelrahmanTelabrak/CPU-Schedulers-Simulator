package cpu.schedulers.simulator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import cpu.schedulers.simulator.Process;
import java.util.ArrayList;

/**
 *
 * @author future
 */
public class sjfScheduling {
    ArrayList<Process> processList = new ArrayList<>();
    public sjfScheduling(){
//         processList.add(new Process("P1", 2, 6, 1));
//         processList.add(new Process("P2", 5, 2, 1));
//         processList.add(new Process("P3", 1, 8, 1));
// 	processList.add(new Process("P4", 0, 3, 1));
// 	processList.add(new Process("P5", 4, 4, 1));
    System.out.println("Enter the number of processes:");
    int pNum = Integer.valueOf(s.nextLine());
    for(int i=0; i<pNum;i++) {
	System.out.println("Enter Arrival time/ burst time");
	input = s.nextLine();
   	processList.add(new Process("P"+(i+1), Integer.valueOf(input.split("\\s")[1]), Integer.valueOf(input.split("\\s")[0]), 1));
    }
    	int context;
	System.out.println("Enter value of context switching");
	input = s.nextLine();
	context = Integer.valueOf(input);
        sjfScheduling(processList, context);
    }
    
    
    public static void sjfScheduling(ArrayList<Process> processList, int context) {
        System.out.println("##############  SJF Scheduling  ##############");
	ArrayList<Process> copyProcessList = new ArrayList<>();

	copyList(copyProcessList, processList);
		
	int totalTime = 0;
	for(Process p:processList) {
		totalTime += p.getExecutionTime();
	}

		
	int index = -1;
	int burstTime;
	int completed =0;
	int i=0;
		
	while(completed<processList.size()) {
            //get the process with the minimum burst time

            if(index!=-1) {
		if(index != findMinProcess(copyProcessList, i)) {
		    i += context;
                    index = findMinProcess(copyProcessList, i);
                    System.out.println("Process "+copyProcessList.get(index).getProcessName()+" is being excuted.");
		}
            }
            else {
                index = findMinProcess(copyProcessList, i);
                if(index==-1) {
                    i++;
                    continue;
                }
                System.out.println("Process "+copyProcessList.get(index).getProcessName()+" is being excuted.");
            }

            burstTime = copyProcessList.get(index).getExecutionTime()-1;
            copyProcessList.get(index).setExecutionTime(burstTime);
            if(burstTime == 0) {
                int turnATime = (i+1)-copyProcessList.get(index).getProcessArivalTime();
                int waitTime = turnATime - processList.get(index).getExecutionTime();
                processList.get(index).setTurnaroundTime(turnATime);
                processList.get(index).setWaitingTime(waitTime);
                completed++;
            }
            i++;
			
        }
		//Waiting Time
        double total=0;
        for(Process p:processList) {
            System.out.println(p.getProcessName()+" waiting time = "+p.getWaitingTime());
            total += p.getWaitingTime();
	}
	System.out.println("Average Waiting time = "+ (total/processList.size()));
		
	//Turn Around Time
	total = 0;
	for(Process p:processList) {
            System.out.println(p.getProcessName()+" turn around time = "+p.getTurnaroundTime());
            total += p.getTurnaroundTime();
	}
	System.out.println("Average Turn around time = "+ (total/processList.size()));
    }
	
    public static int findMinProcess(ArrayList<Process> processList, int aTime) {
	int index = -1;
	int minTime = 1000;
	for (int i=0; i<processList.size(); i++) {
            if(processList.get(i).getProcessArivalTime()<=aTime&&processList.get(i).getExecutionTime()>0&&processList.get(i).getExecutionTime()<minTime) {
                index = i;
		minTime = processList.get(i).getExecutionTime();
            }
	}
	return index;
    }
    public static void copyList(ArrayList<Process> copy, ArrayList<Process> source){
        copy.clear();
        for (int i = 0; i < source.size(); i++) {
            copy.add(new Process(source.get(i)));
        }
    }
}
