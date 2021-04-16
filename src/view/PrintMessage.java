/**
* Display any message passed into the console
* @implement Output.java
* @author Calmen Chia
*/

package Assignment1.view;

public class PrintMessage implements Output
{
    public PrintMessage() { }

    @Override
    public void print( Object msgObj )
    {
        String msg = (String)msgObj;
        System.out.print(msg);
    }
}
