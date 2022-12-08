/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.schedulers.simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author future
 */
public class roundRobin {
    Scanner input = new Scanner(System.in);
    ArrayList<Process> processList = new ArrayList<>();
    public roundRobin(){
        processList.add(new Process("P1", 2, 6, 1));
        processList.add(new Process("P2", 5, 2, 1));
        processList.add(new Process("P3", 1, 8, 1));
	processList.add(new Process("P4", 0, 3, 1));
	processList.add(new Process("P5", 4, 4, 1));
        int quantum;
	System.out.println("Enter quantum:");
	quantum = input.nextInt();
        roundRobin(processList, quantum);

    }
    public static void roundRobin(ArrayList<Process> processList, int quantum) {
	System.out.println("##############  Round Robin Scheduling  ##############");

	ArrayList<Process> copyProcessList = new ArrayList<>();
	Queue<Integer> readyQueue = new LinkedList<>();
	Queue<Integer> waitQueue = new LinkedList<>();
	Queue<Process> processQueue = new LinkedList<>();

	copyList(copyProcessList, processList);
		
	for (int i = 1; i < copyProcessList.size(); ++i) {
            Process key = copyProcessList.get(i);
            int j = i - 1;
            
            while (j >= 0 && copyProcessList.get(j).getProcessArivalTime()> key.getProcessArivalTime()) {
                copyProcessList.set(j+1, copyProcessList.get(j));
                j = j - 1;
            }
            copyProcessList.set(j+1, key);
        }
		
        for(Process p : copyProcessList) {
            processQueue.add(p);
            System.out.println(p.getProcessArivalTime()+" name:"+p.getProcessName());
        }
		
	copyList(copyProcessList, processList);
		
	int index = -1;
	int burstTime=0;
	int counter = 0;
	int completed=0;
	int i=0;
	while (completed<processList.size()) {
			
            if(counter==0) {
		if(burstTime>0) {
                    waitQueue.add(index);
		}
		if(readyQueue.size()==0) {
                    findNextProcess(processQueue, readyQueue,copyProcessList, i);
                    for(int j=0;j<waitQueue.size();j++) {
                        readyQueue.add(waitQueue.poll());
                    }
                    if(readyQueue.size()==0) {
                        i++;
			continue;
                    }
		}
				
		counter = quantum;
                index = readyQueue.poll();
                System.out.println("Process "+copyProcessList.get(index).getProcessName()+" is being excuted.");
            }
			
            burstTime = copyProcessList.get(index).getExecutionTime() -1;
            copyProcessList.get(index).setExecutionTime(burstTime);
            if(burstTime == 0) {
                int turnATime = (i+1)-copyProcessList.get(index).getProcessArivalTime();
		int waitTime = turnATime - processList.get(index).getExecutionTime();
		processList.get(index).setTurnaroundTime(turnATime);
		processList.get(index).setWaitingTime(waitTime);
		counter = 0;
		completed++;
		System.out.println(processList.get(index).getProcessName()+" completed at time "+(i+1));
		//System.out.println("completed = "+completed);
		i++;
		continue;
            }
            counter = counter -1;
            i++;
        }
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
    public static void findNextProcess(Queue<Process> processQueue, Queue<Integer> readyQueue, ArrayList<Process> copy, int time) {	
        //System.out.println(processQueue);
        int size = processQueue.size();
	for(int i=0;i<size;i++) {
            if(processQueue.peek().getProcessArivalTime()<=time) {
                for(Process p: copy) {
                    if(p.getProcessName().equals(processQueue.peek().getProcessName())) {
			readyQueue.add(copy.indexOf(p));
						
                    }
                }
		processQueue.poll();
            }
            else
		break;
	}
		//System.out.println(readyQueue+" in time: "+time);
	}
    public static void copyList(ArrayList<Process> copy, ArrayList<Process> source){
        copy.clear();
        for (int i = 0; i < source.size(); i++) {
            copy.add(new Process(source.get(i)));
        }
    }
}
