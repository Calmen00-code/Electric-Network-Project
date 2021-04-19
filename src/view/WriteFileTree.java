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
    public void print ( Object context, String filename )
    {
        FileOutputStream fos = null;
        PrintWriter pw;
        try
        {
            fos = new FileOutputStream(filename);
            pw = new PrintWriter(fos);
            String str = (String)context;
            pw.println(context);

            pw.close();
        }
        catch (IOException e)
        {
            if ( fos != null ) {
                try { fos.close(); }
                catch(IOException ex2) { }
            }
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }
}
