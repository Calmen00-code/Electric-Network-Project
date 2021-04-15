/**
* Subclass for TreeGenerator interface that reads the data from a file
* @implements TreeGenerator
* @author Calmen Chia
*/

package Assignment1.model;

import java.util.*;
import java.io.*;

public class FileGenerator implements TreeGenerator
{
    private TreeSet<String> networks;

    public FileGenerator() 
    { 
        this.networks = new TreeSet<String>();
    }

    @Override
    public TreeSet generateTree( String filename ) throws ModelException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
        }
        catch(IOException e)
        {
            throw new ModelException("Error while reading the file: " e.getMessage());
        }
    }
}
