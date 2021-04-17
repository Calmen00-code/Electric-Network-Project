/**
* Composite class that handles the component of the city 
* (The Composition could be a part of the city or a building
*  representing the non-root, non-leaf node of the city)
* 
* @extends City
* @author Calmen Chia
*/

package Assignment1.model;

import Assignment1.view.*;
import java.util.*;

public class CityComponent extends City
{
    private String name;
    private String parentName;
    private int height;
    private List<City> networks = new ArrayList<City>();

    public CityComponent( String inName, String inParentName, int inHeight )
    {
        this.name = inName;
        this.parentName = inParentName;
        this.height = inHeight;
    }

    /**
    * Adding new component into the city
    * @import city could be CityComponent or CityBuilding
    */
    public void addComponent( City city )
    {
        networks.add( city );
    }

    @Override
    public List<City> getCity()
    {
        return Collections.unmodifiableList(networks);
    }

    /**
    * Traversing the networks and return the CityComponent object if exist
    * @import value that represents the object to be return
    */
    @Override
    public City find( String searchValue )
    {
        City city = null;

        if ( hasValue(searchValue) )
        {
            Iterator networkIte = networks.iterator();
            boolean found = false;

            while ( networkIte.hasNext() && !found ) {
                city = (City)networkIte.next();
                found = city.getName().equals(searchValue);
            }
        }
        return city;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getParentName()
    {
        return parentName;
    }

    @Override
    public String toString()
    {
        String str = "";

        // Component does not have any building or any sub-component
        if ( networks.isEmpty() )
            str = name + "," + parentName;
        else {
            for ( City city : networks ) {
                if ( isBuilding( city ) )
                    str += city.toString() + "\n";
                else
                    str += city.getName() + "\n";
            }
        }
        return str;
    }

    @Override
    public int getHeight()
    {
        return height;
    } 

    public boolean hasValue ( String searchValue )
    {
        Iterator networkIte = networks.iterator();
        City city = null;
        boolean found = false;

        while ( networkIte.hasNext() && !found ) {
            city = (City)networkIte.next();
            found = city.getName().equals(searchValue);
        }
        return found;
    }

    public boolean isBuilding ( City city )
    {
        return (city instanceof CityBuilding);
    }
}
