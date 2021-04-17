/**
* This class will implement writing the outcome of the network tree into a file
* @implements Output.java
* @author Calmen Chia
*/

package Assignment1.view;

import Assignment1.model.*;
import java.util.*;
import java.io.*;

public class WriteFileTree implements Output
{
    public WriteFileTree() { }

    @Override
    public void print ( Object obj, String filename )
    {
        FileOutputStream fos = null;
        PrintWriter pw;
        try
        {
            fos = new FileOutputStream(filename);
            pw = new PrintWriter(fos);
            City city = (City)obj;

            pw.println(city.getName());
            pw.print(city.toString());

            pw.close();
        }
        catch (IOException e)
        {
            if ( fos != null ) {
                try { fos.close(); }
                catch(IOException ex2) { }
            }
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }
}
