package com.company;

import java.util.ArrayList;

public class Sites {

    private String name;
    private ArrayList<String> siteLocation;


    public void setSiteLocation(ArrayList<String> siteLocation) {
        this.siteLocation = siteLocation;
    }

    void setName(String name)
    {
        this.name = name;
    }

    public String checkYourself(String userInput)
    {
        String result = "Miss";

        int index = siteLocation.indexOf(userInput);

        if(index >= 0)
        {
            siteLocation.remove(index);

            if(siteLocation.isEmpty())
            {
                result = "Destroyed";
                System.out.println("You destroyed a " + name + " : ( ");
            }
            else
            {
                result = "Hitted";
            }
        }
        return result;
    }



}
