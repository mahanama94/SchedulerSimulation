/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rajith Bhanuka
 */
public class ProcessSimulator implements Observer{
    
    private  ArrayList<Process> processes;
    private int simulationTime;
    private Clock simulatorClock;
    private Scheduler scheduler;
    
    
    public ProcessSimulator(int numberOfProcesses, int simulationTime, boolean automatic){
        this.processes = new ArrayList(numberOfProcesses);
        this.simulatorClock = new Clock(simulationTime);
        this.simulationTime = simulationTime;
        this.scheduler = new Scheduler(numberOfProcesses);
        this.simulatorClock.addObserver(this);
        simulatorClock.addObserver(scheduler);
        
        if(automatic){
            // add processes to the simulation
            for(int i=0; i<numberOfProcesses; i++){
                Process newProcess = new Process(i+1, this.simulationTime);
                this.simulatorClock.addObserver(newProcess);
                this.processes.add(newProcess);
                newProcess.addObserver(this.scheduler);
            }
        }
    }

    
    //<editor-fold defaultstate="collapsed" desc="getters">
    
    public Clock getClock(){
        return this.simulatorClock;
    }
    
    public int getSimulationTime(){
        return this.simulationTime;
    }
    
    public int getCurrentTime(){
        return this.getClock().getTime();
    }
    
    public Scheduler getScheduler(){
        return this.scheduler;
    }
    
    public int getNoOfProcesses(){
        return this.processes.size();
    }
    
    public ArrayList<Process> getProcessList(){
        return this.processes;
    }
    
    //</editor-fold>
    
    

    public void run(){
            this.getClock().increment();
        
    }
    
    public void goTo(int time){
        for(int i= getCurrentTime(); i< time; i ++){
            this.run();
        }
    }
    
    public void addProcess(int PId, int arrivalTime, int expectedTime, int remainingTime){
        Process newProcess = new Process(PId, arrivalTime, expectedTime, remainingTime);
        this.simulatorClock.addObserver(newProcess);
        this.processes.add(newProcess);
        newProcess.addObserver(scheduler);
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
        }
        if("endOfSimulation".equals(notification.getType())){
            //this.scheduler.show();
            this.show();
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
    