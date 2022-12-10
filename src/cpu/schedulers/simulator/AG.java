/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.schedulers.simulator;

import java.util.ArrayList;

/**
 *
 * @author future
 */
public class AG {
    private ArrayList<Process> process = new ArrayList<>();
	private ArrayList<Process> process_order = new ArrayList<>(); 
	private int sum_Waitingtime = 0;
	private int sum_Turnaroundtime =0;
	private Process Current = null;
	private int time = 0;
	
	
	public AG() {
//		ArrayList<Process> m = new ArrayList<>();
		Process p1 = new Process("P1" ,17 , 0, 4 , 7);
		process.add(p1);
		Process p2 = new Process("P2" , 6 , 2 , 7 , 9);
		process.add(p2);
		Process p3 = new Process("P3" ,11 , 5, 3, 4);
		process.add(p3);
		Process p4 = new Process("P4" , 4 , 15 , 6 , 6);
		process.add(p4);
	}
	
	public Process get_first_process() {
		Process p = null;
		int min_arrival = Integer.MAX_VALUE;
		for(Process i : process) {
			if(i.getProcessArivalTime() < min_arrival && i.getExecutionTime()!=0) {
				min_arrival = i.getProcessArivalTime();
				p = i;
			}
		}
		return p; 
	}
	
	
	public void updated_quantm() {
		for(Process i : process) {
			System.out.print(i.Quantm + " ");
		}System.out.println("\n");
	}
	
	
	public void FCFS(Process P) {
		
		if(P == null) return; 
		
		updated_quantm(); 
		process_order.add(P);
		int idx = 0;
		
		for(Process i : process) {
			if(i.getProcessName() == P.getProcessName()) {
				break;
			}
			idx ++ ;
		}
		
		boolean finshed = false; 
		int counter = (int)Math.ceil(0.25 * P.RQ);
		
		while(counter > 0) {
			if(P.RQ ==0 && P.getExecutionTime() > 0) {
				P.Quantm *=2;
				P.RQ = P.Quantm;
				P.CompletionTime = time; 
				process.set(idx, P);
				finshed = true;			 
				break; 
			}
			if(P.getExecutionTime() == 0) {
				P.Quantm = 0;
				P.RQ = 0;
				P.CompletionTime = time;
				process.set(idx, P);
				finshed = true;				
				break; 
			}
			
			P.RQ --;
			counter -- ; 
                        P.setExecutionTime(P.getExecutionTime()-1);
//			P.BurstTime --; 
			time++;
			P.CompletionTime = time;  
		}
		
		if(!finshed && P.getExecutionTime() == 0) {
			P.RQ = P.Quantm = 0; 
			process.set(idx,P); 
			FCFS(get_first_process());	
		}
		else if(finshed) {	
			FCFS(get_first_process());
		}
		else if(!finshed && P.getExecutionTime() > 0) {
			process.set(idx, P);
			non_preemptive(P , idx);
		}
		
		
   }
	
	public void non_preemptive(Process P , int index ) {
		
		int min_proi = Integer.MAX_VALUE;
		
		Process p = null;
		
		int idx = 0;
		int min_idx = 0;
		
		for(Process i : process) {
			if(i.getProcessPriority() < min_proi && i.getProcessArivalTime() <= time && i.getExecutionTime()!=0) {
				min_proi = i.getProcessPriority();
				p = i;
				min_idx = idx; 
			}
			idx ++ ; 
		}
		
		boolean finsh = false; 
		if(min_idx  == index) {
			int counter = (int)Math.ceil(0.25 * P.RQ);
			
			while(counter > 0) {
				if(P.RQ == 0 && P.getExecutionTime() > 0 ) {
					P.Quantm *=2;
					P.RQ = P.Quantm;
					P.CompletionTime = time; 
					process.set(index, P);
					finsh = true;
					break; 
				}	
				if(P.getExecutionTime() == 0) {
					P.Quantm = 0;
					P.RQ = 0;
					P.CompletionTime = time;
					process.set(index, P);
					finsh = true;
					break; 
				}
				
				P.RQ --;
				counter -- ; 
                                P.setExecutionTime(P.getExecutionTime()-1);
//				P.BurstTime --; 
				time ++;
				P.CompletionTime = time; 
			}

			if(!finsh && P.getExecutionTime() == 0) {
				P.RQ = P.Quantm = 0; 
				process.set(index,P); 
				FCFS(get_first_process());	
			}
			else if(finsh) {	
				FCFS(get_first_process());
			}
			else if(!finsh && P.getExecutionTime() > 0) {
				process.set(index, P);
				sjf(P , index);
			}
		}
		else {
			double rem_q = ((double)P.RQ/2);
			
			P.Quantm = (int) (P.Quantm + Math.ceil(rem_q));
			
			
			P.RQ = P.Quantm;
			process.set(index, P);
			
			FCFS(p);
		}
		
	}
	

	public void sjf(Process P , int index) {
		
		int min_burst = Integer.MAX_VALUE;
		Process p = null;
		int idx = 0;
		int min_idx = 0;
		for(Process i : process) {
			if(i.getExecutionTime() < min_burst && i.getProcessArivalTime() <= time && i.getExecutionTime()!= 0) {
				min_burst = i.getExecutionTime(); 
				p = i;
				min_idx = idx; 
			}
			idx ++ ; 
		}
		
		if(min_idx  != index) {
			P.Quantm = (P.Quantm + P.RQ);
			P.RQ = P.Quantm;
			process.set(index, P);
			FCFS(p);
		}else {
			p.RQ =0;p.Quantm = 0;
		
			while(p.getExecutionTime() > 0) {
				time ++;
                                p.setExecutionTime(p.getExecutionTime()-1);
//				p.getExecutionTime() --;
			}
			
			
                        p.setExecutionTime(0);
//			p.BurstTime = 0;
			p.CompletionTime = time;
			process.set(min_idx, p);
			FCFS(get_first_process());
			
		}
		
		
	}
		
	
	
	public void run() {
		FCFS(Current);
		for(Process i : process_order)
			System.out.println(i.getProcessName());
		
		Calc_WaitingTime();
		Calc_TurnTime();	
		System.out.println("Average Waiting Time :"  + (double)sum_Waitingtime / process.size());
		System.out.println("Average Turnaround Time :"  + (double)sum_Turnaroundtime / process.size());
		
	}
	public void Calc_WaitingTime() {
		for(Process i : process) {
		     System.out.println("Waiting Time of " + i.getProcessName() + " is : " + i.getWaitingTime());
		     sum_Waitingtime+=i.getWaitingTime();
		}
	}
	
	public void Calc_TurnTime() {
		for(Process i : process) {
		     System.out.println("TurnAround Time of " + i.getProcessName() + " is : " + i.getTurnaroundTime());
		     sum_Turnaroundtime+=i.getTurnaroundTime();
		}
	}
}
