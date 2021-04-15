/**
* Handle all the exceptions being thrown in the model folder
* @extends Exception
* @author Calmen Chia
*/

package Assignment1.model;

public class ModelException extends Exception
{
    public ModelException( String msg )
    {
        super(msg);
    }

    public ModelException( String msg, Throwable cause )
    {
        super(msg, cause);
    }
} 
