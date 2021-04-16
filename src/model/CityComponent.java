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
    private List<City> networks = new ArrayList<City>();

    public CityComponent( String inName )
    {
        this.name = inName;
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
    public City find( String searchValue )
    {
        City city = null;
        if ( networks.contains(searchValue) )
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
}
