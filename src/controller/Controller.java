/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import operatingsystemscheduler.ProcessSimulator;
import operatingsystemscheduler.Process;
import java.util.ArrayList;
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
    
    public static String[][] getProcessDetails(){
        
        ArrayList<Process> processList = ProcessSimulator.getProcessList();
        String[][] processDetails = new String[ProcessSimulator.getNoOfProcesses()][4];
        
        for(int i=0; i<ProcessSimulator.getNoOfProcesses(); i++){
                processDetails[i][0]=Integer.toString(processList.get(i).getProcessId());
                processDetails[i][1]=Integer.toString(processList.get(i).getArrivalTime());
                processDetails[i][2]=Integer.toString(processList.get(i).getExpectedTime());
                processDetails[i][3]=Integer.toString(processList.get(i).getRemainingTime());
        }
        
        return processDetails;
    }
}
