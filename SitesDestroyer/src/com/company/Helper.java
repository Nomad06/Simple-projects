package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Helper
{
    private final static int FIELD_SIZE = 7;
    private ArrayList<String> locationCells;

    public ArrayList<String> setLocation(int comSize)
    {
        locationCells = new ArrayList<String>();
        boolean fixedColumn = false;
        int horizontalCell = (int) (Math.random()*7);

        if(FIELD_SIZE - horizontalCell >= comSize )
        {
            numToString(horizontalCell, fixedColumn);
        }
        else
        {
            fixedColumn = true;
            numToString(horizontalCell, fixedColumn);
        }

        return locationCells;

    }

    public String userInput()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your guess:");
        String userGuess = input.nextLine();

        return userGuess;
    }

    public void numToString(int horizontalCell, boolean fixedColumn)
    {
        String alphabetCell;
        String alphabet = "abcdefg";
        String convertedHorizontalCell;
        String finalCell;

        int verticalCell = (int)(Math.random() * 6);

        if (fixedColumn)
        {
            convertedHorizontalCell = Integer.toString(horizontalCell);
            for (int i = 0; i < 3; i++)
            {
                if(FIELD_SIZE - verticalCell >= 3)
                {
                    alphabetCell = alphabet.substring(verticalCell + i, verticalCell + (i + 1));
                    finalCell = alphabetCell + convertedHorizontalCell;
                    System.out.print(finalCell + "    ");
                    locationCells.add(finalCell);
                }
                else
                {
                    alphabetCell = alphabet.substring(verticalCell - (i + 1), verticalCell - i);
                    finalCell = alphabetCell + convertedHorizontalCell;
                    System.out.print(finalCell + "    ");
                    locationCells.add(finalCell);
                }

            }
        }
        else
        {
            alphabetCell = alphabet.substring(verticalCell, verticalCell + 1);
            for(int i = 0; i < 3; i++)
            {
                convertedHorizontalCell = Integer.toString(horizontalCell + i);

                finalCell = alphabetCell + convertedHorizontalCell;
                System.out.print(finalCell + "    ");
                locationCells.add(finalCell);
            }

        }



    }
}
