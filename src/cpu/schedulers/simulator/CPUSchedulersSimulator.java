/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cpu.schedulers.simulator;

import java.util.Scanner;

/**
 *
 * @author future
 */
public class CPUSchedulersSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner input=new Scanner(System.in);
        while(true){
            System.out.println("Choose Type Of Algorithm");
            System.out.println("1. preemptive Shortest- Job First (SJF) Scheduling");
            System.out.println("2. Round Robin (RR) With context switching");
            System.out.println("3. preemptive Priority Scheduling");
            System.out.println("4. AG Scheduling");
            System.out.println("5. Exit");
            int option = input.nextInt();
            if(option==1){
                new sjfScheduling();
            }else if(option==2){
                new roundRobin();
            }else if(option==3){
                new preemptive_Priority_Scheduling();
            }else if(option==4){
                AG a = new AG();
		a.run();
            }else if(option==5){
                break;
            }
        }   
    }
    
}










//ArrayList<String> cars = new ArrayList<String>();
//    cars.add("Volvo");
//    cars.add("BMW");
//    cars.add("Ford");
//    cars.add("Mazda");
//  
//    // Get the iterator
//    Iterator<String> it = cars.iterator();
//    
//    // Loop through a collection
//    while(it.hasNext()) {
//      System.out.println(it.next());
//    }


//Set<String> ts = new TreeSet<>();
// 
//        // Adding elements in above object
//        // using add() method
//        ts.add("Geek");
//        ts.add("For");
//        ts.add("Geeks");
//        ts.add("A");
//        ts.add("B");
//        ts.add("Z");
// 
//        // Now we will be using for each loop in order
//        // to iterate through the TreeSet
//        for (String value : ts)
// 
//            // Printing the values inside the object
//            System.out.print(value + ", ");
// 
//        System.out.println();