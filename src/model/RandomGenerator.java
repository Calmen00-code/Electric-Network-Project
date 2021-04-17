/**
* Randomly generated a name for the root, non-root, and leaf to produce the network tree
* @implements TreeGenerator.java
* @author Calmen Chia
*/

package Assignment1.model;

import java.util.*;

public class RandomGenerator implements TreeGenerator
{
    @Override 
    public City generateTree( String s ) throws ModelException;
    {
    }

    /**
    * Read a sets of values from the file
    * @export randomValues to be generated in generateTree
    */
    public String[] readFile( String filename ) throws ModelException;
    {
        // [0] = root values, [1] = non-root values, [2] = leaf values
        String[] randomValues = new String[3];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            int i = 0;

            line = reader.readLine();
            while ( line != null ) {
                randomValues[i] = line;
                ++i;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new ModelException("Error while reading file: " + e.getMessage());
        }
        return randomValues;
    }            
}
