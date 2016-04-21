/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.Simulation;
/**
 *
 * @author bhanuka
 */
public class OperatingSystemScheduler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*Scheduler testScheduler = new Scheduler(3);
        Process testProcess1 = new Process(1, 0, 20);
        Process testProcess2 = new Process(2, 10, 10);
        Process testProcess3 = new Process(3, 20, 5);
        //Process testProcess = new Process(1, 100);
        
        /*testProcess1.show();
        testProcess2.show();
        testProcess3.show();
        
        
        testScheduler.addProcess(testProcess1);
        testScheduler.addProcess(testProcess2);
        testScheduler.addProcess(testProcess3);
        System.out.println("================================================");
        testScheduler.show();
        */

        ProcessSimulator simulator = new ProcessSimulator(5, 10);
        Simulation sm = new Simulation();
        sm.setVisible(true);
        System.out.println("5 second");
        simulator.show();
       
        
        // TODO code application logic here
        
    }
    
}
