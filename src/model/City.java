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

    public List<City> getCity() { return null; }
    public String getName() { return ""; }
    public String getParentName() { return ""; }
    public City find(String s) { return null; }
    public void addComponent(City city) { }
    public void addConsumption(String s, double val) { }
    public String toString() { return ""; }
    public String toFileString() { return ""; }
    public int getHeight() { return 0; }
}
