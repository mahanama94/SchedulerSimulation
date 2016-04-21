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
public class ProcessSimulator extends Thread implements Observer{
    
    private ArrayList<Process> processes;
    private int simulationTime;
    private Clock simulatorClock;
    private Scheduler scheduler;
    
    ProcessSimulator(int numberOfProcesses, int simulationTime){
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
    
    public Clock getClock(){
        return this.simulatorClock;
    }
    
    public int getSimulationTime(){
        return this.simulationTime;
    }
    
    public int getCurrentTime(){
        return this.getClock().getTime();
    }
    
    //</editor-fold>
    
    
    @Override
    public void run(){
        try {
            this.sleep(100);
            this.getClock().increment();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goTo(int time){
        for(int i=this.getCurrentTime(); i< time; i ++){
            this.run();
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
                this.interrupt();
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
