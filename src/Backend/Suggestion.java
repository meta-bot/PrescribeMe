/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tishpish
 */
public class Suggestion implements Serializable
{
    ArrayList<Medicine> medSuggestion;
    ArrayList<Test> testSuggestion;

    public Suggestion()
    {
        medSuggestion = new ArrayList<Medicine>();
        testSuggestion = new ArrayList<Test>();
    }
    
    public void addMedicine(Medicine med)
    {
        medSuggestion.add(med);
    }
    
    public void addTest(Test test)
    {
        testSuggestion.add(test);
    }
    
    public ArrayList<String> getMedSuggestion(String prefix)
    {
        ArrayList<String> array = new ArrayList<>();
        for (int i=0;i<medSuggestion.size();i++)
        {
            if (medSuggestion.get(i).getName().startsWith(prefix))
                array.add(medSuggestion.get(i).getName());
        }
        return array;
    }
    
    public ArrayList<String> getTestSuggestion(String prefix)
    {
        ArrayList<String> array = new ArrayList<>();
        for (int i=0;i<testSuggestion.size();i++)
        {
            if (testSuggestion.get(i).getName().startsWith(prefix))
                array.add(testSuggestion.get(i).getName());
        }
        return array;
    }
    
    public void tryToAddMedicine(Medicine med)
    {
        for (int i=0;i<medSuggestion.size();i++)
        {
            String name = medSuggestion.get(i).getName();
            if (name.matches(med.getName()))
                return;
        }
        medSuggestion.add(med);
    }
    
    public void tryToAddTest(Test test)
    {
        for (int i=0;i<testSuggestion.size();i++)
        {
            String name = testSuggestion.get(i).getName();
            if (name.matches(test.getName()))
                return;
        }
        testSuggestion.add(test);
    }
    
    
    
}
