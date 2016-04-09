/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

/**
 *
 * @author bhanuka
 */
public class Clock{
    
 
    private static Clock clock = new Clock();
    private double initTime = -1;
    
    private Clock(){
        this.initTime =System.currentTimeMillis();
    }
    
    public static Clock getInstance(){
        return clock;
    }
    
    public double getTime(){
        return System.currentTimeMillis()- this.initTime;
    }
    
    
}
