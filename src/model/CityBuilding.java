/**
* Class to handle the leaf node of the composite pattern in City
* @extends City
* @author Calmen Chia
*/

package Assignment1.model;

import java.util.*;

public class CityBuilding extends City
{
    private String name;
    private HashMap <String,Double> powConsumption;

    public CityBuilding ( String inName )
    {
        this.name = inName;
    }

    public void addConsumption( String type, double value )
    {
        powConsumption.put(type, value);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
