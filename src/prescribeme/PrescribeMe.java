/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prescribeme;

import Backend.*;
import Backend.Prescription;
import Backend.Test;
import Frontend.MainPanel;

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
        new MainPanel().doTask(args);
        
        String testName[]={"Blood test","Eye test","Sugar test"};
        String testType[]={"Blood","Eye","Sugar"};
        
        String medName[]={"Histasin","Alatrol","Algin"};
        String medType[]={"Tab.","Cap.","Liq."};
        
        Prescription pres = Prescription.getEmptyPrescription("Doctor101","24-9-17", "18_52");
        
        for (int i=0;i<3;i++)
        {
            Test te = new Test(testName[i],testType[i]);
            pres.addTest(te);
        }
        
        for (int i=0;i<3;i++)
        {
            Medicine te = new Medicine(medName[i],medType[i],"1+1+1","15days");
            pres.addMedicine(te);
        }
        
        System.out.println(pres.convertToJson());
        
        String pathToSave = "/home/tishpish/NetBeansProjects";

        API api = API.getInstance();
        
        if (api.savePrescription(pathToSave,"tiash",pres))
            System.out.println("saved");
        else
            System.out.println("not saved");
        
        Prescription tp = api.loadPrescription(pathToSave+"/tiash.ta");
        if (tp!=null)
        {
            System.out.println(tp.getDoctorId());
            if (api.syncPrescription(pres))
            {
                System.out.println("Success");
            }
        }
        
    }
    
}
