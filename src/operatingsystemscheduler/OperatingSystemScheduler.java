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
public class OperatingSystemScheduler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Clock c = Clock.getInstance();
   
        int i =0;
        while(i<100000){
            if(i%1000==0){
                System.out.println(c.getTime());
            }
            i++;
        }
        
        // TODO code application logic here
    }
    
}
