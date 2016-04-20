/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

/**
 *
 * @author Rajith Bhanuka
 */
public class Notification {
    
    private String type;
    private int time;
    
    public Notification(String type, int time){
        this.time = time;
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
    
    public int getTime(){
        return this.time;
    }
    
}
