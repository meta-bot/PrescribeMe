/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author tishpish
 */
public class Medicine extends Test
{
    private String
            timing, // 1+0+1
            duration;/// 15 days
            
    public Medicine(String name,String type,String timing,String duration)
    {
        super(name, type);
        this.timing = timing;
        this.duration = duration;
    }

    public String getDuration()
    {
        return duration;
    }

    @Override
    public String getName()
    {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType()
    {
        return super.getType(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getTiming()
    {
        return timing;
    }
    
    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public void setTiming(String timing)
    {
        this.timing = timing;
    }

    @Override
    public void setName(String name)
    {
        super.setName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setType(String type)
    {
        super.setType(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJson()
    {
        String data = "";
        data+="{\n";
        data+="\"name\":\""+getName()+"\",\n";
        data+="\"type\":\""+getType()+"\",\n";
        data+="\"timing\":\""+getTiming()+"\",\n";
        data+="\"duration\":\""+getDuration()+"\"\n";
        data+="}";
        return data;
    }
    
    
    
}
