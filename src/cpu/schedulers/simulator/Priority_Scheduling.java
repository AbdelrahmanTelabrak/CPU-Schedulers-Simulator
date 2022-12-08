///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package cpu.schedulers.simulator;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.TreeSet;
//import java.util.Comparator;
//import java.util.Iterator;
//
///**
// *
// * @author future
// */
//
//
//public class Priority_Scheduling {
//    Scanner input=new Scanner(System.in);
//    Scanner str=new Scanner(System.in);
//    
//    String ProcessName;
//    int ProcessArivalTime;
//    int ExecutionTime;
//    int ProcessPriority;
//    
//    
//    int initialArivalTime;
//    int completionTime;
//    int AgingTime=0; //to solving of starvation problem
//    
//    ArrayList<Process> AllProcesses=new ArrayList<Process>(); //to save it at first
//    ArrayList<Integer> startArivalForProcess=new ArrayList<Integer>(); //each process when start
//    ArrayList<Process> Final=new ArrayList<Process>(); //to save final result for each process
//    
//    //A comparator interface is used to order the objects of user-defined classes.
//    //A comparator object is capable of comparing two objects of the same class. Following function compare obj1 with obj2.
//    class compareTwoProcess implements Comparator<Object>
//    {
//        public int compare(Object pr1,Object pr2){
//            Process process1 = (Process)pr1;
//            Process process2 = (Process)pr2;
//            
//            if(process1.getProcessArivalTime()<process2.getProcessArivalTime()){
//                return -1;
//            }else if((process1.getProcessPriority()<process2.getProcessPriority()) && (process1.getProcessArivalTime() == process2.getProcessArivalTime())){
//                return -1;
//            }else{
//                return 1;
//            }
//        }
//    }
//    
//    
//    
//    
//    /*
//        This constructor is used to build a TreeSet object containing all the elements from the 
//        given collection in which elements will get stored in default natural sorting order. 
//        In short, this constructor is used when any conversion is needed from any Collection object to TreeSet object.
//    */
//    TreeSet<Process> ts = new TreeSet<Process>(new compareTwoProcess());
//    
//    
//    
//    
//    public void  Simulator(){
//        System.out.print("Enter Number of Processes: ");
//        int no_of_processes = input.nextInt();
//        
//
//        //An Iterator is an object that can be used to loop through collection of process
//        Iterator<Process> iterator = ts.iterator();
//        
//        
//        for(int i=0;i<no_of_processes;i++){
//            Process process = new Process();
//            
//            
//            
//            System.out.print("Enter Name of Processes: ");
//            ProcessName = str.nextLine();
//            process.setProcessName(ProcessName);
//            
//            
//            System.out.print("Enter Burst Time of Processes: ");
//            ExecutionTime = input.nextInt();
//            process.setExecutionTime(ExecutionTime);
//            
//            System.out.print("Enter Priority Time of Processes: ");
//            ProcessPriority = input.nextInt();
//            process.setProcessPriority(ProcessPriority);
//            
//            
//            System.out.print("Enter Arrival Time of Processes: ");
//            ProcessArivalTime = input.nextInt();
//            process.setProcessArivalTime(ProcessArivalTime);
//            
//    
//            
//            
//            //the solving of starvation problem
//            AgingTime+=process.getExecutionTime();
//            if(AgingTime>=10){
//                while(iterator.hasNext()){
//                    Process temp = (Process)iterator.next();
//                    temp.setProcessPriority(temp.getProcessPriority()-1);
//                }
//                AgingTime%=10;
//            }
//            
//            ts.add(process);
//            AllProcesses.add(process);   
//        }
//        
//        Iterator<Process> iterator2 = ts.iterator();
//        
//        
//        //وعشان انا مرتبهم فوق
//        initialArivalTime = ts.first().getProcessArivalTime();
//        
//        
//        while(iterator2.hasNext()){
//            Process p = (Process)iterator2.next();
//            
//            
//            startArivalForProcess.add(initialArivalTime);
//            int x=initialArivalTime+p.getExecutionTime();
//            if(initialArivalTime!=x){
//                initialArivalTime+=1;
//                continue;
//            }
////            initialArivalTime+=p.getExecutionTime();
//            completionTime=initialArivalTime;
//            startArivalForProcess.add(completionTime);
//            p.setTurnaroundTime(completionTime-p.getProcessArivalTime());
//            p.setWaitingTime(p.getTurnaroundTime()-p.getExecutionTime());
//     
//            Final.add(p);
//        }
//        
//        /*To calculate output
//                Processes execution order
//                Waiting Time for each process
//                Turnaround Time for each process
//                Avg Waiting Time
//                Avg Turnaround Time
//        */
//        
//        float sumOfWaitingTimee=0,sumOfTurnaroundTime=0;
//        System.out.println("\n\nProcess \t Brust Time \t Priority \t Arrival Time \t Turnaround Time    Waiting Time");
//        for(int i =0;i<Final.size();i++){
//            sumOfWaitingTimee+=Final.get(i).getWaitingTime();
//            sumOfTurnaroundTime+=Final.get(i).getTurnaroundTime();
//            System.out.print("\n "+Final.get(i).getProcessName()+"\t\t  "+Final.get(i).getExecutionTime()+"\t\t  "+Final.get(i).getProcessPriority()+"\t\t  "+Final.get(i).getProcessArivalTime()+"\t\t\t  "+Final.get(i).getTurnaroundTime()+"\t\t  "+Final.get(i).getWaitingTime());
//        }
//        
//        System.out.println("\nAvg Waiting Time: "+sumOfWaitingTimee/no_of_processes);
//        System.out.println("Avg Turnaround Time: "+sumOfTurnaroundTime/no_of_processes);
//    }
//    
//    
//    
//    
//    
//}
