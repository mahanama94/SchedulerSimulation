/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystemscheduler;

import java.util.Comparator;

/**
 *
 * @author Rajith Bhanuka
 */
public class RemainingTimeComparator implements Comparator<Process>{

    @Override
    public int compare(Process process1, Process process2) {
        if(process1.getRemainingTime()< process2.getRemainingTime()){
            return -1;
        }
        else{
            return 1;
        }
    }
    
}
