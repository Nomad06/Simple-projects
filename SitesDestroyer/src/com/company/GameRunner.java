package com.company;


import java.util.ArrayList;

public class GameRunner
{
    private Helper gameHelper = new Helper();
    private ArrayList<Sites> sitesArrayList = new ArrayList<Sites>();
    private int numOfGuesses = 0;

    public void setUpGame()
    {
        Sites site1 = new Sites();
        Sites site2 = new Sites();
        Sites site3 = new Sites();

        site1.setName("Awax.ru");
        site2.setName("4pda.ru");
        site3.setName("yandex.ru");

        sitesArrayList.add(site1);
        sitesArrayList.add(site2);
        sitesArrayList.add(site3);

        for (Sites site: sitesArrayList)
        {
            ArrayList<String> newLocation = gameHelper.setLocation(3);
            site.setSiteLocation(newLocation);
            System.out.println();
        }
    }

    public void  startPlaying()
    {

        while (!sitesArrayList.isEmpty())
        {
            String userGuess = gameHelper.userInput();
            checkUserGuess(userGuess);
        }

        finishGame();

    }

    public String checkUserGuess(String userGuess)
    {
        numOfGuesses++;
        String result = "Miss";

        for(Sites site: sitesArrayList)
        {
            result = site.checkYourself(userGuess);

            if(result.equals("Hitted"))
            {
                break;
            }
            if(result.equals("Destroyed"))
            {
                sitesArrayList.remove(site);
                break;
            }
        }
        System.out.println(result);

        return result;
    }

    private void finishGame()
    {
        System.out.println("All sites has been destroyed! Your shares are no longer has a price!");

        if(numOfGuesses <= 18)
        {
            System.out.println("It took only " + numOfGuesses + " attempts to end a game!");
            System.out.println("You go out in time, before your shares has been destroyed");
        }
        else
        {
            System.out.println("It took you a long time. " + numOfGuesses + " attempts.");
            System.out.println("Fishes are going around of your investments.");
        }
    }


}
