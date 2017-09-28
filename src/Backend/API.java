/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author tishpish
 */
public class API
{
    private static API singletonAPI;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://csedu.cf/toplines/querymaker.php";
    private API()
    {
    }
    
    public static API getInstance()
    {
        if (singletonAPI==null)
            singletonAPI =  new API();
        return singletonAPI;
    }
    
    public Prescription getEmptyPrescription(String doctorID,String time, String date, String patientName,String patientAge,String patientSex)
    {
        return Prescription.getEmptyPrescription(doctorID, time, date, patientName, patientAge, patientSex);
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
    
    public boolean syncPrescription(Prescription prescription)
    {
        String data = prescription.convertToJson();
        // seding post request to remote server
        System.out.println(data);
        
        
        URL obj;
        try
        {
            obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            String POST_PARAMS = "query=SAVE_DATA";
            
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            
            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) 
            { 
                   return true;
            }
            else 
            {
                    System.out.println("POST request not worked");
                    return false;
            }
        } 
        catch (Exception ex)
        {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        
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
    
    public boolean doctorLogin(String email, String password)
    {
        //Build parameter string
        String data = "email="+email+"&pass="+password;
        try 
        {
            
            // Send the request
            URL url = new URL(POST_URL);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            
            //write parameters
            writer.write(data);
            writer.flush();
            
            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            
            //Output the response
            System.out.println(answer.toString());
            return true;
        }
        catch (MalformedURLException ex) 
        {
            ex.printStackTrace();
            return false;
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
            return false;
        }
        
    }
    
}
