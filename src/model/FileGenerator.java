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
    public FileGenerator() { }

    /**
    * Methods Overriden from TreeGenerator
    * Reads and generateTree from the file
    */
    @Override 
    public List<City> generateTree( String filename ) throws ModelException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            String[] splitLine, consumptionVal;
            City city = null, cityNode = null, parent = null;

            line = reader.readLine();

            city = new CityComponent( line );    // Creating root

            while ( line != null ) {
                splitLine = line.split(",");

                // Check if line contains abbreviation
                if ( isLeaf( line ) ) { 
                    cityNode = new CityBuilding( splitLine[0] );
                    parent = city.find( splitLine[1] );

                    if ( parent == null )
                        throw new ModelException("City component does not exist in the network\n");
                    else 
                    {
                        // Add all power consumption
                        for ( int i = 2; i < splitLine.length; ++i ) {
                            // Parse the splitLine to get the consumption value
                            consumptionVal = splitLine[i].split("=");
                            cityNode.addConsumption(consumptionVal[0], 
                                    Double.parseDouble(consumptionVal[1])); 
                        }
                        parent.add( cityNode );
                    }

                } else {

                    for ( int i = 0; i < splitLine.length - 1; ++i ) {
                        cityComponent = new CityComponent( splitLine[0] );
                        
                    
                }
                line = reader.readLine();
            }
                    
        }
        catch(IOException e)
        {
            throw new ModelException("Error while reading the file: " e.getMessage());
        }
    }
}
