/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import operatingsystemscheduler.ProcessSimulator;
import operatingsystemscheduler.Process;
import java.util.ArrayList;
import operatingsystemscheduler.ExecutionLog;
import view.*;
/**
 *
 * @author wolfpack
 */
public class Controller {
    
    private static ProcessSimulator processSimulator;
    
    //private static Simulation sm;
    public Controller(){
        
    }
    
    public static void incrementTime(){
        processSimulator.run();
    }
    
    public static int getTime(){
        return processSimulator.getClock().getTime();
    }
    
    public static int getSimTime(){
        return processSimulator.getSimulationTime();
    }
    
    public static void jumptoEnd(){
        processSimulator.goTo(getSimTime());
    }
    
    public static int getCurrentProcessID(){
        if(processSimulator.getScheduler().getCurrentProcess()!=null){
            return processSimulator.getScheduler().getCurrentProcess().getProcessId();
    }else{
            return 0;
    }
    }
    
    public static String[][] getProcessDetails(){
        
        ArrayList<Process> processList = processSimulator.getProcessList();
        String[][] processDetails = new String[processSimulator.getNoOfProcesses()][4];
        
        for(int i=0; i<processSimulator.getNoOfProcesses(); i++){
                processDetails[i][0]=Integer.toString(processList.get(i).getProcessId());
                processDetails[i][1]=Integer.toString(processList.get(i).getArrivalTime());
                processDetails[i][2]=Integer.toString(processList.get(i).getExpectedTime());
                processDetails[i][3]=Integer.toString(processList.get(i).getRemainingTime());
        }
        
        return processDetails;
    }
    
    public static String[][] getFinishedProcessDetails(){
        
        ArrayList<Process> processList = processSimulator.getProcessList();
        String[][] processDetails = new String[processSimulator.getNoOfProcesses()][4];
        int i =0;
        for(Process process:processList){
            if(process.isFinished()){
                processDetails[i][0]=Integer.toString(process.getProcessId());
                processDetails[i][1]=Integer.toString(process.getWatitingTime(processSimulator.getCurrentTime()));
                processDetails[i][2]=Integer.toString(process.getTurnoaroundTime(processSimulator.getCurrentTime()));
                processDetails[i][3]=Integer.toString(process.getFinishedTime());
                i++;
            }
        }
        
        return processDetails;
    }
        
    public static String[][] getReadyQueue(){
        ArrayList<Process> readyQueue = new ArrayList(processSimulator.getScheduler().getReadyQueue());
        String[][] readyQueueDet = new String[readyQueue.size()][2];
        
        for(int i=0; i<readyQueue.size(); i++){
                readyQueueDet [i][0]=Integer.toString(readyQueue.get(i).getProcessId());
                readyQueueDet [i][1]=Integer.toString(readyQueue.get(i).getRemainingTime());
        }
        
        return readyQueueDet;
        
    }
    
    public static String[][] getProcessLogs(int processId){
        
        ArrayList<ExecutionLog> executionLogs = processSimulator.getProcessList().get(processId-1).getExecutionLog();
        String[][] logDetails = new String[executionLogs.size()][2];
        int i =0;
        for(ExecutionLog log:executionLogs){

            logDetails[i][0]=Integer.toString(log.getStartTime());
            logDetails[i][1]=Integer.toString(log.getEndTime());
            i++;
        }
        
        return logDetails;
    }
    
    public static void createSimulator(int processNo, int time, boolean automatic){
        processSimulator = new ProcessSimulator(processNo, time, automatic);
        if(automatic){            
            startSimulation();
        }
        else{
            AddProcess processAdder = new AddProcess();
            processAdder.setVisible(true);
        }
    }
    
    public static void startSimulation(){
        Simulation sm =new Simulation();
        sm.setVisible(true);
    }
    
    public static void launch(){
        Launcher lr = new Launcher();
        lr.setVisible(true);
    }
    
    public static void addProcess(int pId, int arrivalTime, int expectedTime, int remainingTime){
        processSimulator.addProcess(pId, arrivalTime, expectedTime, remainingTime);
    }
}
