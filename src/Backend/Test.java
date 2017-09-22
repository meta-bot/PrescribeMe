/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.Serializable;

/**
 *
 * @author tishpish
 */
public class Test implements Serializable
{
    private String name,type;
    public Test(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getJson()
    {
        String data = "";
        data+="{\n";
        data+="\"name\":\""+getName()+"\",\n";
        data+="\"type\":\""+getType()+"\"\n";
        data+="}";
        return data;
    }
    
    
}
