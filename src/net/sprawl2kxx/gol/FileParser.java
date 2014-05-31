package net.sprawl2kxx.gol;

import java.io.*;
import java.util.*;
import java.awt.*;

public class FileParser
{
    public static ArrayList<Point> getConfiguration(File inputFile)
    {
        ArrayList<Point> newLiveCells = new ArrayList<Point>();

        try
        {
            Scanner fin = new Scanner(inputFile);

            while (fin.hasNextInt())
            {
                Point p = new Point(fin.nextInt(), fin.nextInt());
                newLiveCells.add(p);
            }

            fin.close();
            
            return newLiveCells;
        }
        catch (FileNotFoundException e)
        {
            return newLiveCells;
        }
    }
}
