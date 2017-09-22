/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tishpish
 */
public class API
{
    private static API singletonAPI;
    private API()
    {
    }
    
    public static API getInstance()
    {
        if (singletonAPI==null)
            singletonAPI =  new API();
        return singletonAPI;
    }
    
    public boolean savePrescription(String path,String filename, Prescription prescription)
    {
        if (!filename.endsWith(".ta"))
            filename+=".ta";
        
        FileOutputStream fileOutputStream;  
        try
        {
            fileOutputStream = new FileOutputStream(path+"/"+filename);
            try
            {
                ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
                writer.writeObject(prescription);  
                writer.flush();
                return true;
            } 
            catch (IOException ex)
            {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean savePrescription(String path, Prescription prescription)
    {
        String fileName = prescription.getDate()+"_"+prescription.getTime()+".ta";
        return savePrescription(path,fileName, prescription);
    }
    
    
    public Prescription loadPrescription(String fullPathWithFileName)
    {
        ObjectInputStream in=null;
        try
        {
            in = new ObjectInputStream(new FileInputStream(fullPathWithFileName));  
            Prescription s=(Prescription)in.readObject();
            in.close();
            return s;
        } 
        catch (Exception ex)
        {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public Prescription loadPrescription(String path, String fileName)
    {
        if (!path.endsWith("/"))
            path+="/";
        return loadPrescription(path+fileName);
    }
    
}
