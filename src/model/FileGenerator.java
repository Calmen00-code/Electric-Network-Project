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
    * @export City Object which contains the whole tree
    */
    @Override 
    public City generateTree( String filename ) throws ModelException
    {
        City city = null;
        int height = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            String[] splitLine, consumptionVal;
            City cityNode = null, parent = null;

            line = reader.readLine();

            if ( line != null ) {
                if ( line.contains(",") )
                    throw new ModelException("Root should not contain any element else!\n");
                else {
                    city = new CityComponent( line, "", height );    // Creating root
                    city.addComponent( city );
                }
            } 
            else 
                throw new ModelException("Missing the root node\n");

            line = reader.readLine();
            while ( line != null ) {
                splitLine = line.split(",");

                // Lines contain only a single non-root name but without the name of its parent 
                // CORRECT EXAMPLE: non-root,parent
                if ( splitLine.length <= 1 )
                    throw new ModelException("Missing parent node for a non-root node\n");

                // Check if line contains abbreviation
                if ( isLeaf( line ) ) { 
                    //System.out.println("ENTERED IS-LEAF ->" + line +"\n");
                    parent = city.find( splitLine[1] );
                    height = parent.getHeight() + 1;
                    cityNode = new CityBuilding( splitLine[0], splitLine[1], height );

                    if ( parent == null )
                        throw new ModelException("Parent of this City building does not exist in the network\n");
                    else 
                    {
                        // Add all power consumption
                        for ( int i = 2; i < splitLine.length; ++i ) {
                            // Parse the splitLine to get the consumption value
                            consumptionVal = splitLine[i].split("=");
                            cityNode.addConsumption(consumptionVal[0], 
                                    Double.parseDouble(consumptionVal[1])); 
                        }
                        // System.out.println("Child: " + cityNode.getName() + ", Parent: " + parent.getName());
                        parent.addComponent( cityNode );
                    }

                } else {

                    // System.out.println("ENTERED ELSE IS-LEAF");
                    if ( splitLine.length > 2 )
                        throw new ModelException("A child component can only have one parent component!\n");
                    else {
                        parent = city.find( splitLine[1] );
                        height = parent.getHeight() + 1;
                        cityNode = new CityComponent( splitLine[0], splitLine[1], height );

                        if ( parent == null )
                            throw new ModelException("Parent of this City component does not exist in the network\n");
                        else
                            parent.addComponent( cityNode );
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException e)
        {
            throw new ModelException("Error while reading the file: " + e.getMessage());
        }
        return city;
    }

    @Override
    public String[] readFile( String filename ) throws ModelException
    {
        String[] context = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            int i = 0;
            int numLine = getNumberLine( filename );
            context = new String[numLine];

            line = reader.readLine();
            while ( line != null ) {
                context[i] = line;
                ++i;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new ModelException("Error while reading the file: " + e.getMessage());
        }
        return context;
    }

    private boolean isLeaf ( String str )
    {
        boolean leaf = false;
        if ( str.contains("dm=") || str.contains("da=") || str.contains("de=") || str.contains("em=") ||
             str.contains("ea=") || str.contains("ee=") || str.contains("h=") || str.contains("s=") )
            leaf = true;

        return leaf;
    }

    private int getNumberLine( String filename ) throws ModelException
    {
        int num = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";

            line = reader.readLine();
            while ( line != null ) {
                ++num;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new ModelException("Error while reading the file: " + e.getMessage());
        }
        return num; 
    }

    @Override
    public int getDepth() throws ModelException
    {
        throw new ModelException("This method is not supported in FileGenerator\n");
        // throw new UnsupportedOperationException("This method is not supported in FileGenerator\n");
    }
}
