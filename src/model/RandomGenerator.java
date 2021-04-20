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
    public static final int AVAILABLE = 1;
    public static final int UNAVAILABLE = -1;
    private Random rand = new Random();
    private int depth = 0;

    @Override 
    public City generateTree( String filename ) throws ModelException
    {
        rand.setSeed(System.currentTimeMillis());
        Queue <City> queue = new LinkedList<City>();
        // HashMap is used to keep track if there is any repeating values 
        HashMap <String,String> randomValues = new HashMap<String,String>();    
        // col=25 as that is the longest possible outcome 
        // max. depth = 5, max. child = 5, therefore 5x5 = 25
        int[][] recordVal = new int[3][25];     

        String[] randomVal = readFile( filename );
        initialiseRecordVal( recordVal );
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
                createChild( currNd, numChild, randomVal, i, recordVal );
                storeQueue( currNd.getCity(), queue );
            }
            else {
                // Create childrens for all nodes in current height
                while ( !queue.isEmpty() && queue.peek().getHeight() <= i ) {
                    currNd = queue.poll();

                    // Does not need to create child if currNd is leaf (CityBuilding)
                    if ( currNd instanceof CityComponent ) {
                        createChild( currNd, numChild, randomVal, i, recordVal );
                        storeQueue( currNd.getCity(), queue );
                    } 
                }
            }
        }
        return city;
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

    private void createChild( City city, int numChild, String[] randomVal, 
                              int height, int[][] recordVal ) throws ModelException
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

                childName = generateRandomChild( splitChild, recordVal[1] );

                // Returned childName is always unique
                cityNode = new CityComponent( childName, parentName, height + 1 );
            } else {
                // randomVal[2] contains all values of childs
                splitChild = randomVal[2].split(","); 
            
                childName = generateRandomChild( splitChild, recordVal[2] );

                // Returned childName is always unique
                cityNode = new CityBuilding( childName, parentName, height + 1 );
                generateRandomComposition( cityNode );
            }
            city.addComponent( cityNode );
        }
    } 

    /**
    * @export Return the childName that was never been used 
    */
    private String generateRandomChild( String[] splitChild, int[] record ) throws ModelException
    {
        int idxChild;
        String childName = "";

        if ( !isRecordFull(record) ) {
            // Generate random index from 0 to largest index
            // to get the randomised value of child
            idxChild = rand.nextInt(splitChild.length - 1);
            childName = splitChild[idxChild];

            // Repeat to generate randomly until record has an available index
            while ( record[idxChild] != AVAILABLE ) {
                idxChild = rand.nextInt(splitChild.length - 1);
                childName = splitChild[idxChild];
            }
            // Remove the item from the record so that other component wont use it 
            record[idxChild] = UNAVAILABLE;
        } 
        else 
            throw new ModelException("Not enough random value\n");
        return childName;
    }

    private boolean isRecordFull( int[] record )
    {
        int i = 0;
        boolean full = true;

        // ASSERTION: full becomes false when record[i] = AVAILABLE
        while ( i < record.length && full ) {
            full = record[i] != AVAILABLE;
            ++i;
        }
        return full;
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
    * Initialise all the contents of recordVal to 1 (Unused)
    */
    private void initialiseRecordVal( int[][] recordVal )
    {
        for ( int i = 0; i < recordVal.length; ++i ) {
            for ( int j = 0; j < recordVal[i].length; ++j )
                recordVal[i][j] = AVAILABLE; 
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
