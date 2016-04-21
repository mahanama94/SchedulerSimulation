/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import operatingsystemscheduler.ProcessSimulator;

/**
 *
 * @author wolfpack
 */
public class Controller {
    public static void incrementTime(){
        ProcessSimulator.run();
    }
    
    public static int getTime(){
        return ProcessSimulator.getClock().getTime();
    }
    
    public static int getSimTime(){
        return ProcessSimulator.getSimulationTime();
    }
    
    public static void jumptoEnd(){
        ProcessSimulator.goTo(getSimTime());
    }
    
    public static int getCurrentProcessID(){
        if(ProcessSimulator.getScheduler().getCurrentProcess()!=null){
            return ProcessSimulator.getScheduler().getCurrentProcess().getProcessId();
    }else{
            return 0;
    }
    }
}
