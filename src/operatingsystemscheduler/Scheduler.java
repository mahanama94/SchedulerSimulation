/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;


/**
 *
 * @author Rajith Bhanuka
 */
   
    public class Scheduler implements Observer{
    
    protected static Comparator<Process> SRTcomparator= new RemainingTimeComparator();
    protected PriorityQueue<Process> readyQueue;
    protected Process currentProcess; 
    
    
    public Scheduler(int capacity){
        this.readyQueue = new PriorityQueue(capacity, SRTcomparator);
        this.currentProcess = null;
    }
    
    public Scheduler(int capacity, PriorityQueue<Process> queue){
        this.readyQueue = queue;
        this.currentProcess = this.readyQueue.poll();
    }
    
    public void start(int simulatorTime){
        if(this.currentProcess!=null){
            this.currentProcess.start(simulatorTime);
        }
    }
    
    public void addProcess(Process newProcess){
        
        int currentTime = newProcess.getArrivalTime();
        //add new process to the ready queue
        this.readyQueue.add(newProcess);
        
        //stop the current process and add to the queue
        if(this.currentProcess!=null){
            this.currentProcess.stop(currentTime);
            this.readyQueue.add(this.currentProcess);
        }
        
        
        // get the shortest remainig time job
        this.currentProcess = this.readyQueue.poll();
        this.currentProcess.start(currentTime);             
    }
    
    public void show(){
        System.out.println("Current Process ");
        this.currentProcess.show();
        System.out.println("Waiting Queue");
        for(Process process:this.readyQueue){
            process.show();
        }
    }

    @Override
    public void update(Observable observable, Object arg) {
        Notification notification = (Notification) arg;
        int time = notification.getTime();        
        this.currentProcess.stop(notification.getTime());
        
        this.currentProcess= this.readyQueue.poll();
        
        if(this.currentProcess!= null){
            this.currentProcess.start(time);
        }

    }  
}
