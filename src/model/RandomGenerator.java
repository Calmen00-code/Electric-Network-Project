/**
* Randomly generated a name for the root, non-root, and leaf to produce the network tree
* @implements TreeGenerator.java
* @author Calmen Chia
*/

package Assignment1.model;

import java.util.*;

public class RandomGenerator implements TreeGenerator
{
    public static final int MIN_HEIGHT = 1;
    public static final int MAX_HEIGHT = 5;
    public static final int MIN_NODE = 2;
    public static final int MAX_NODE = 5;

    @Override 
    public City generateTree( String filename ) throws ModelException;
    {
        Random rand = new Random();
        String[] randomVal = readFile( filename );
        String[] value = null;
        int depth = rand.nextInt(MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;

        System.out.println("Depth Randomised: " + depth);

        // Creating the root node
        String[] splitRoot = randomVal[0].split(",");
        int idxRoot = rand.nextInt(splitRoot.length - 1);
        String rootName = splitRoot[idxRoot];
        City city = new CityComponent( rootName, "", 0 );

        // Creating Subsequent non-root node and leaf node
        int numChild;
        for ( int i = 1; i < depth; ++i ) {
            numChild = rand.next(MAX_NODE - MIN_NODE) + MIN_NODE;
            createChild( city, numChild );
        }
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
