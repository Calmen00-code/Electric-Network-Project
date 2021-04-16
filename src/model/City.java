/**
* Abstract class to implement to composite of City
* @subclass CityBuilding, CityComponent
* @author Calmen Chia
*/

package Assignment1.model;

import java.util.*;

public abstract class City
{
    public City() { }
    public List<City> getCity();
    public String getName();
}
