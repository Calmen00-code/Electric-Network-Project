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
    private String parentName;
    private HashMap <String,Double> powConsumption = new HashMap <String,Double>();

    public CityBuilding ( String inName, String inParentName )
    {
        this.name = inName;
        this.parentName = inParentName;
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

    @Override
    public String toString()
    {
        String str = "";
        str += name + "," + parentName + ",";
        for ( Map.Entry<String, Double> consumption : powConsumption.entrySet() )
            str += consumption.getKey() + "=" + consumption.getValue();
        return str;
    }
}
