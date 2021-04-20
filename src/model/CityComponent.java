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
        Stack<City> cityStack = new Stack<City>();
        String str = "";
        City cityNd;

        storeStack( networks, cityStack );
        cityStack.pop();    // Root is not needed as it had been displayed

        while ( !cityStack.isEmpty() ) {
            cityNd = cityStack.pop();

            // Building is the leaf hence no more children nodes
            // Therefore there is nothing to store in the stack
            if ( cityNd instanceof CityBuilding )
                str += getSpace(cityNd) + cityNd.toString() + "\n";
            else {
                str += getSpace(cityNd) + cityNd.getName() + "\n";
                storeStack( cityNd.getCity(), cityStack );
            }
        }
        return str;
    }

    public String toFileString()
    {
        String str = "";

        for ( City city : networks ) {
            if ( city instanceof CityBuilding )
                str += city.toFileString() + "\n";
            else
                str += city.getName() + "," + city.getParentName() + "\n";
        }
        return str;
    }

    /**
    * Store element node in networks to Stack
    */
    private void storeStack( List <City> inNetworks, Stack <City> stack )
    {
        // Store city starting from the last element as Stack is LIFO
        // Therefore to maintain the sequence, store starting from the last element of network
        for ( int i = inNetworks.size() - 1; i >= 0; --i )
            stack.push(inNetworks.get(i));
    }
   
    @Override
    public int getHeight()
    {
        return height;
    } 

    private boolean hasValue ( String searchValue )
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

    private boolean isBuilding ( City city )
    {
        return (city instanceof CityBuilding);
    }

    private String getSpace( City city )
    {
        String space = "";
        int height = city.getHeight();

        for ( int i = 0; i < height; ++i )
            space += "  ";
        return space;
    }
}
