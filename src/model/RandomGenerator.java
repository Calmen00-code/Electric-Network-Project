/**
* Randomly generated a name for the root, non-root, and leaf to produce the network tree
* @implements TreeGenerator.java
* @author Calmen Chia
*/

package Assignment1.model;

import java.util.*;
import java.io.*;

public class RandomGenerator implements TreeGenerator
{
    public static final int MIN_HEIGHT = 1;
    public static final int MAX_HEIGHT = 5;
    public static final int MIN_NODE = 2;
    public static final int MAX_NODE = 5;

    @Override 
    public City generateTree( String filename ) throws ModelException
    {
        Random rand = new Random();
        Queue <City> queue = new LinkedList<City>();
        String[] randomVal = readFileRandom( filename );
        String[] value = null;
        int depth = rand.nextInt(MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;

        // FIXME display should not be done in model
        System.out.println("Depth Randomised: " + depth + "\n");

        // Creating the root node
        String[] splitRoot = randomVal[0].split(",");
        int idxRoot = rand.nextInt(splitRoot.length - 1);
        String rootName = splitRoot[idxRoot];
        City city = new CityComponent( rootName, "", 0 );
        city.addComponent( city );

        // Creating Subsequent non-root node and leaf node
        int numChild;
        City currNd = city;
        for ( int i = 1; i < depth; ++i ) {
            // Randomly generate the number of childrens for current node
            numChild = rand.nextInt(MAX_NODE - MIN_NODE) + MIN_NODE;

            if ( i == 1 ) {
                createChild( currNd, numChild, randomVal, i );
                storeQueue( currNd.getCity(), queue );
                //displayQueue( queue );  // FIXME Displaying queue for createChild debug
            }
            else {
                // Create childrens for all nodes in current height
                while ( !queue.isEmpty() && queue.peek().getHeight() <= i ) {
                    currNd = queue.poll();

                    // Does not need to create child if currNd is leaf (CityBuilding)
                    if ( currNd instanceof CityComponent ) {
                        createChild( currNd, numChild, randomVal, i );
                        storeQueue( currNd.getCity(), queue );
                    }
                }
            }
        }
        return city;
    }

    public void displayQueue ( Queue <City> queue )
    {
        for ( City city : queue )
            System.out.println(city.getName());
    }

    /**
    * Store element node in networks to Queue
    */
    public void storeQueue( List <City> inNetworks, Queue<City> queue )
    {
        for ( int i = 0; i < inNetworks.size(); ++i ) {
            // Root value does not need to be added 
            if ( inNetworks.get(i).getHeight() != 0 )
            queue.add(inNetworks.get(i));
        }
    }

    public void createChild( City city, int numChild, String[] randomVal, int height )
    {
        Random rand = new Random();
        String[] splitChild = null;
        String parentName, childName;
        City cityNode;
        int idxChild;
        int nodeType;

        for ( int i = 0; i < numChild; ++i ) {
            // Create Non-Leaf (nodeType = 0), Create Leaf (nodeType = 1)
            nodeType = rand.nextInt(1); 
            parentName = city.getName();
            if ( nodeType == 0 ) {
                // randomVal[1] contains all values of childs
                splitChild = randomVal[1].split(","); 
        
                // Generate random index from 0 to largest index
                // to get the randomised value of child
                idxChild = rand.nextInt(splitChild.length - 1);
                childName = splitChild[idxChild];
                cityNode = new CityComponent( childName, parentName, height + 1 );
            } else {
                // randomVal[2] contains all values of childs
                splitChild = randomVal[2].split(","); 
            
                // Generate random index from 0 to largest index
                // to get the randomised value of child
                idxChild = rand.nextInt(splitChild.length - 1);
                childName = splitChild[idxChild];
                cityNode = new CityBuilding( childName, parentName, height + 1 );
            }
            city.addComponent( cityNode );
        }
    } 

    /**
    * Read a sets of values from the file
    * @export randomValues to be generated in generateTree
    */
    public String[] readFileRandom( String filename ) throws ModelException
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

    @Override
    public String readFile(String s)
    {
        return "";
    }
}
