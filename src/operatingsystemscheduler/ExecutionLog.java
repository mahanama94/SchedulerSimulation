package operatingsystemscheduler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhanuka
 */
public class ExecutionLog {
    
    private double startTime;
    private double endTime;
    
    public ExecutionLog(double start){
        this.startTime = start;
        this.endTime = start;
    }
    
    public double getStartTime(){
        return this.startTime;
    }
    
    public double getEndTime(){
        return this.endTime;
    }
    
    public void end(double end){
        this.endTime = end;
    }
    
    public void print(){
        System.out.println("Start Time : "+ this.startTime);
        System.out.println("End Time   : "+ this.endTime);
    }
}
