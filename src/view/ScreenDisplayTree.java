/**
* Implements the printing of the city electrical network to the screen 
* @implements Output.java
* @author Calmen Chia
*/

package Assignment1.view;

import Assignment1.model.*;
import java.util.*;

public class ScreenDisplayTree implements Output
{
    public ScreenDisplayTree() { }

    @Override
    public void print( Object obj )
    {
        City city = (City)obj;
        List <City> cityNetwork = city.getCity();

        for ( City cityNode: cityNetwork )
            System.out.print(cityNode.toString());
    }
} 
