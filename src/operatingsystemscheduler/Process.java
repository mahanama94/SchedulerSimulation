/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.ArrayList;

/**
 *
 * @author bhanuka
 */
public class Process{

    private ArrayList<ExecutionLog> executionlog;
    private double remainingTime;
    private double expectedTime;
    private boolean status;
    
    public Process(double time){
        this.expectedTime = time;
        this.remainingTime = time;
        this.executionlog = new ArrayList();
    }
    
    public double getRemainingTime(){
        return this.remainingTime;
    }
    
    public double getExpectedTime(){
        return this.expectedTime;
    }
    
    public boolean getStatus(){
        return this.status;
    }
    public void execute(){
        //get the system clock value
        this.executionlog.add(new ExecutionLog(12.0));
    }
    
    public void stop(){
        ExecutionLog lastLog =this.executionlog.get(this.executionlog.size()-1);
        //get time from the clock and store in the execution log object
        lastLog.end(15.0);
        this.remainingTime = this.remainingTime - (lastLog.getEndTime() - lastLog.getStartTime());
    }
    
}
