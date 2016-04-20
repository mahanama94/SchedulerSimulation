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
    
 
    private static Clock clock = new Clock();
    private int time;
    
    public Clock(){
        this.time = -1;
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters">
        
    public int getTime(){
        return this.time;
    }
    
    //</editor-fold>
    
    public void increment(){
        this.time++;
        System.out.println("Time : "+ this.getTime());
        this.setChanged();
        this.notifyObservers(new Notification("clockNotification", this.getTime()));
    }
    
    public void increment(int time){
        for(int i =0; i < time; i++){
            this.increment();
        }
    }
    
}
