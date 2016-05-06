/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import operatingsystemscheduler.ProcessSimulator;
import operatingsystemscheduler.Process;
import java.util.ArrayList;
import java.util.PriorityQueue;
import operatingsystemscheduler.ExecutionLog;
import view.*;
/**
 *
 * @author wolfpack
 */
public class Controller {
            
    //private static Simulation sm;
    
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
    
    public static String[][] getFinishedProcessDetails(){
        
        ArrayList<Process> processList = ProcessSimulator.getProcessList();
        String[][] processDetails = new String[ProcessSimulator.getNoOfProcesses()][4];
        int i =0;
        for(Process process:processList){
            if(process.isFinished()){
                processDetails[i][0]=Integer.toString(process.getProcessId());
                processDetails[i][1]=Integer.toString(process.getWatitingTime(ProcessSimulator.getCurrentTime()));
                processDetails[i][2]=Integer.toString(process.getTurnoaroundTime(ProcessSimulator.getCurrentTime()));
                processDetails[i][3]=Integer.toString(process.getFinishedTime());
                i++;
            }
        }
        
        return processDetails;
    }
        
    public static String[][] getReadyQueue(){
        ArrayList<Process> readyQueue = new ArrayList(ProcessSimulator.getScheduler().getReadyQueue());
        String[][] readyQueueDet = new String[readyQueue.size()][2];
        
        for(int i=0; i<readyQueue.size(); i++){
                readyQueueDet [i][0]=Integer.toString(readyQueue.get(i).getProcessId());
                readyQueueDet [i][1]=Integer.toString(readyQueue.get(i).getRemainingTime());
        }
        
        return readyQueueDet;
        
    }
    
    public static String[][] getProcessLogs(int processId){
        
        ArrayList<ExecutionLog> executionLogs = ProcessSimulator.getProcessList().get(processId-1).getExecutionLog();
        String[][] logDetails = new String[executionLogs.size()][2];
        int i =0;
        for(ExecutionLog log:executionLogs){

            logDetails[i][0]=Integer.toString(log.getStartTime());
            logDetails[i][1]=Integer.toString(log.getEndTime());
            i++;
        }
        
        return logDetails;
    }
    
    public static void createSimulator(int processNo, int time){
        ProcessSimulator simulator = new ProcessSimulator(processNo, time);
        Simulation sm =new Simulation();
        sm.setVisible(true);
    }
    
    public static void launch(){
        Launcher lr = new Launcher();
        lr.setVisible(true);
    }
    
    public  void Hello(){
            System.out.println("Hello");
    }
   
}
