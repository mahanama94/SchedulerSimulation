/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author bhanuka
 */
public class Process{

    private int pId;
    private int arrivalTime;
    private int expectedTime;
    private int remainingTime;
    private ArrayList<ExecutionLog> executionLog;
    private boolean finished;
    private boolean active;
    
    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Process(int pId, int simulatorTime){
        this.pId =pId;
        Random randomGen = new Random();
        this.expectedTime = randomGen.nextInt(20);
        this.remainingTime = this.expectedTime;
        this.arrivalTime = randomGen.nextInt(simulatorTime - this.getExpectedTime());
        this.executionLog = new ArrayList();
    }
    
    public Process(int pId, int arrivalTime, int expectedTime ){
        this.pId = pId;
        this.arrivalTime = arrivalTime;
        this.expectedTime= expectedTime;
        this.remainingTime = this.expectedTime;
        this.executionLog = new ArrayList();
    }
    
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getters" >
    
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    
    public int getExpectedTime(){
        return this.expectedTime;
    }
    
    public int getRemainingTime(){
        return this.remainingTime;
    }
    
    public ArrayList<ExecutionLog> getExecutionLog(){
        return this.executionLog;
    }
    
    public int getStartTime(){
        if(this.executionLog.isEmpty()){
            return -1;
        }
        return this.executionLog.get(0).getStartTime();
    }
    public int getFinishedTime(){
        if(this.isFinished()){
            return this.executionLog.get(this.executionLog.size()-1).getEndTime();
        }
        return -1;
    }
    
    public boolean isFinished(){
        return this.finished;
    }
    
    public boolean isActive(){
        return this.active;
    }
    
    public int getWatitingTime(int simulatorTime){
        // waiting time = elapsed time - executedTime
        return (simulatorTime - this.arrivalTime) - (this.expectedTime - this.remainingTime);
    }
    
    public int getTurnoaroundTime(int simulatorTime){
        //Job has Finished
        if(this.isFinished()){
            return this.getFinishedTime() - this.arrivalTime;
        }
        // Not yet Finished - arrived
        else if(this.arrivalTime > simulatorTime){
            return simulatorTime - this.arrivalTime;
        }
        // not yet arrived
        else{
            return 0;
        }
    }
    
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="setters" >  
    public void setRemainingTime(int time){
        this.remainingTime = time;
    }
    
    public void addExecutionLog(ExecutionLog log){
        this.executionLog.add(log);
    }
    
    // </editor-fold>

    public void start(int simulatorTime){
        this.executionLog.add(new ExecutionLog(simulatorTime));
        this.active = true;
    }
    
    /**
     * Stops the current process
     * Updates the end time of the last log of the 
     * last execution log to the simulator time
     * @param simulatorTime time in the simulator
     * 
     */
    public void stop(int simulatorTime){
        ExecutionLog lastLog =this.executionLog.get(this.executionLog.size()-1);
        this.setRemainingTime(this.getRemainingTime() - (simulatorTime - lastLog.getStartTime()));
        this.active = false;
        lastLog.setEndTime(simulatorTime);
    }
    
    public void show(){
        System.out.println("Process ID : "+ this.pId);
        System.out.println("Status Active : "+ this.active);
        System.out.println("Arrival time : "+ this.getArrivalTime());
        System.out.println("Remaining time : "+ this.getRemainingTime());
        System.out.println("Expected time: "+ this.getExpectedTime());
        System.out.println("No of logs : "+ this.getExecutionLog().size());
        System.out.println("========= Start of Logs =========");
        for(ExecutionLog log:this.getExecutionLog()){
            System.out.println("=================================");
            log.print();
            System.out.println("=================================");
        }
        System.out.println("========== End of Logs ==========");
    }
    
}
