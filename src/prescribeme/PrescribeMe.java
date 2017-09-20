/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prescribeme;

import Backend.*;
import Backend.Prescription;
import Backend.Test;

/**
 *
 * @author anando
 */
public class PrescribeMe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        // test communication
        Prescription pres = new Prescription("cccc", "yyyy");
        
        for (int i=0;i<3;i++)
        {
            Test te = new Test("name","type");
            pres.addTest(te);
        }
        
        for (int i=0;i<3;i++)
        {
            Medicine te = new Medicine("name","type","fff","ffff");
            pres.addMedicine(te);
        }
        
        System.out.println(pres.convertToJson());
    }
    
}
