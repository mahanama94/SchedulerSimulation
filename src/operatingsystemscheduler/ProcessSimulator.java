/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajith Bhanuka
 */
public class ProcessSimulator implements Observer{
    
    private static ArrayList<Process> processes;
    private static int simulationTime;
    private static Clock simulatorClock;
    private static Scheduler scheduler;
    
    
    public ProcessSimulator(int numberOfProcesses, int simulationTime){
        this.processes = new ArrayList(numberOfProcesses);
        this.simulatorClock = new Clock();
        this.simulationTime = simulationTime;
        this.scheduler = new Scheduler(numberOfProcesses);
        this.simulatorClock.addObserver(this);
        
        // add processes to the simulation
        for(int i=0; i<numberOfProcesses; i++){
            Process newProcess = new Process(i+1, this.simulationTime);
            this.simulatorClock.addObserver(newProcess);
            this.processes.add(newProcess);
            newProcess.addObserver(this.scheduler);
        }
        
    }

    
    //<editor-fold defaultstate="collapsed" desc="getters">
    
    public static Clock getClock(){
        return simulatorClock;
    }
    
    public static int getSimulationTime(){
        return simulationTime;
    }
    
    public static int getCurrentTime(){
        return getClock().getTime();
    }
    
    public static Scheduler getScheduler(){
        return scheduler;
    }
    
    public static int getNoOfProcesses(){
        return processes.size();
    }
    
    public static ArrayList<Process> getProcessList(){
        return processes;
    }
    
    //</editor-fold>
    
    

    public static void run(){
            getClock().increment();
        
    }
    
    public static void goTo(int time){
        for(int i= getCurrentTime(); i< time; i ++){
            run();
        }
    }
    
    @Override
    public void update(Observable observable, Object arg) {
        Notification notification = (Notification)arg;
        
        if("clockNotification".equals(notification.getType())){
            int time = notification.getTime();
            for(Process process:this.processes){
                if(process.getArrivalTime()== time){
                    this.scheduler.addProcess(process);
                }
            }
            if(time == this.simulationTime){
                //this.scheduler.show();
                this.show();
            }
        }
    }
    
    public void show(){
        System.out.println("Simulator Out");
        for(Process process:this.processes){
            process.show();
            System.out.println("----------------------------------------------");
        }   
    }
    
}
