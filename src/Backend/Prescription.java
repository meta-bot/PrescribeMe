/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author tishpish
 */
public class Prescription
{
    private String time, date;
    private ArrayList<Test> allTest;
    private ArrayList<Medicine> allMedicine;

    public Prescription(String time, String date)
    {
        this.time = time;
        this.date= date;
        allTest = new ArrayList<Test>();
        allMedicine = new ArrayList<Medicine>();
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
    
    public void addTest(Test test)
    {
        allTest.add(test);
    }
    public void addMedicine(Medicine med)
    {
        allMedicine.add(med);
    }
    
    public String convertToJson()
    {
        String data = "";
        data+="{";
        data+="\"time\":\""+getTime()+"\",\n";
        data+="\"date\":\""+getDate()+"\",\n";
        data+="\"tests\":"+"[\n";
        
        for (int i=0;i<allTest.size();i++)
        {
            data+= allTest.get(i).getJson();
            if (i+1<allTest.size())
            {
                data+=",";
            }
        }
        data+="],\n";
         data+="\"medicines\":"+"[\n";
        for (int i=0;i<allMedicine.size();i++)
        {
            data+= allMedicine.get(i).getJson();
            if (i+1<allMedicine.size())
            {
                data+=",\n";
            }
        }
        
        
        data+="]\n";
        data+="}\n";
        //return data;
        return data;
    }
    
    
    
}
