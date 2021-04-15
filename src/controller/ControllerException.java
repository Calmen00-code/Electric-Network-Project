/**
* Class to handle all exception being thrown by all files in controller folder 
* @inherit Exception
* @author Calmen Chia
*/

package Assignment1.controller;

public class ControllerException extends Exception
{
    public ControllerException(String msg)
    {
        super(msg);
    }

    public ControllerException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
