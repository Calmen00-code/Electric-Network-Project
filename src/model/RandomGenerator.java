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
    public static final int MIN_POWER = 0;
    public static final int MAX_POWER = 1000;
    private Random rand = new Random();
    private int depth = 0;

    @Override 
    public City generateTree( String filename ) throws ModelException
    {
        rand.setSeed(System.currentTimeMillis());
        Queue <City> queue = new LinkedList<City>();
        // HashMap is used to keep track if there is any repeating values 
        HashMap <String,String> randomValues = new HashMap<String,String>();    
        String[] value = null;

        String[] randomVal = readFile( filename );
        depth = rand.nextInt(MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;

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

    /**
    * Store element in the string array into the hash map
    * The hashmap is used to keep track the repeating values in the string array
    */
    private void storeHashMap( String[] randomVal, HashMap <String,String> randomValues )
    {
        String[] splitRandom;
        for ( int i = 0; i < randomVal.length; ++i ) {
            splitRandom = randomVal[i].split(",");
            for ( int j = 0; j < splitRandom.length; ++j )
                randomValues.put(splitRandom[j], splitRandom[j]);
        }
    }

    /**
    * Store element node in networks to Queue
    */
    private void storeQueue( List <City> inNetworks, Queue<City> queue )
    {
        for ( int i = 0; i < inNetworks.size(); ++i ) {
            // Root value does not need to be added 
            if ( inNetworks.get(i).getHeight() != 0 )
            queue.add(inNetworks.get(i));
        }
    }

    private void createChild( City city, int numChild, 
                             String[] randomVal, int height )
    {
        String[] splitChild = null;
        String parentName, childName;
        City cityNode;
        int idxChild;
        int nodeType;

        for ( int i = 0; i < numChild; ++i ) {
            // Create Non-Leaf (nodeType = 0), Create Leaf (nodeType = 1)
            nodeType = rand.nextInt(2); 
            parentName = city.getName();
            if ( nodeType == 0 ) {
                // randomVal[1] contains all values of childs
                splitChild = randomVal[1].split(","); 

                // Generate random index from 0 to largest index
                // to get the randomised value of child
                idxChild = rand.nextInt(splitChild.length - 1);
                childName = splitChild[idxChild];

                // Returned childName is always unique
                cityNode = new CityComponent( childName, parentName, height + 1 );
            } else {
                // randomVal[2] contains all values of childs
                splitChild = randomVal[2].split(","); 
            
                // Generate random index from 0 to largest index
                // to get the randomised value of child
                idxChild = rand.nextInt(splitChild.length - 1);
                childName = splitChild[idxChild];

                // Returned childName is always unique
                cityNode = new CityBuilding( childName, parentName, height + 1 );
                generateRandomComposition( cityNode );
            }
            city.addComponent( cityNode );
        }
    } 

    private String generateRandomChild( String[] splitChild, HashMap<String,String> randomValues )
    {
        int idxChild;
        String childName = "";

        // Generate random index from 0 to largest index
        // to get the randomised value of child
        idxChild = rand.nextInt(splitChild.length - 1);
        childName = splitChild[idxChild];

        // Repeat to generate randomly until a hash map has the value
        while ( !randomValues.containsKey(childName) && !randomValues.isEmpty() ) {
            idxChild = rand.nextInt(splitChild.length - 1);
            childName = splitChild[idxChild];
        }
        // Remove the key from hash map so that other component wont use it 
        randomValues.remove(childName);     
        return childName;
    }

    private void generateRandomComposition( City building )
    {
        String[] consumptionType = { "dm", "da", "de", "em", 
                                     "ea", "ee", "h", "s" };
        double randomPower = 0.0;

        for ( int i = 0; i < consumptionType.length; ++i ) {
            randomPower = (Math.random() * ((MAX_POWER - MIN_POWER) + 1)) + MIN_POWER; 
            building.addConsumption( consumptionType[i], randomPower );
        }
    }

    /**
    * Read a sets of values from the file
    * @export randomValues to be generated in generateTree
    */
    @Override
    public String[] readFile( String filename ) throws ModelException
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
    public int getDepth() throws ModelException
    {
        // Depth should not be 0 after random generation
        if ( depth == 0 )
            throw new ModelException("Error while retrieving depth\n");
        return depth;
    }
}
