/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.Observable;

/**
 *
 * @author bhanuka
 */
public class Clock extends Observable{
    
 
    private int time;
    private int endTime;
    
    public Clock(int endTime){
        this.time = -1;
        this.endTime = endTime;
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters">
        
    public int getTime(){
        return this.time;
    }
    
    public int getEndTime(){
        return this.endTime;
    }
    
    //</editor-fold>
    
    public void increment(){
        this.time++;
        System.out.println("Time : "+ this.getTime());
        this.setChanged();
        if(this.getTime()==this.getEndTime()){
            this.notifyObservers(new Notification("endOfSimulation", this.getTime()));
        }
        else{
            this.notifyObservers(new Notification("clockNotification", this.getTime()));
        }
    }
    
    public void increment(int time){
        for(int i =0; i < time; i++){
            this.increment();
        }
    }
    
}
