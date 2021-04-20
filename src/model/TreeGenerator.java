/**
* Interface to implement the function for generation of the tree to create Composite Pattern
* @subclass FileGenerator.java, RandomGenerator.java
* @author Calmen Chia
*/

package Assignment1.model;

public interface TreeGenerator
{
    public City generateTree( String s ) throws ModelException;
    public String[] readFile( String s ) throws ModelException;
    public int getDepth() throws ModelException;
}
