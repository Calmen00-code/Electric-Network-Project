/**
* Controller class to start up the program for Electric Network Demonstration
* @associate Output.java, TreeGenerator.java
* @author Calmen Chia
*/

package Assignment1.controller;

/*
import Assignment1.model.*;
import Assignment1.view.*;
*/

public class ElectricNetworkDemo
{
    public static void main(String[] args)
    {
        String[] splitArg = args[0].split(" ");
        readArgument(splitArg);
    }

    public static void readArgument ( String[] splitArg ) throws ControllerException
    {
        if ( splitArg[0].equals("-r") )
        {
            // Check if the next argument after -r is a file
            // NOTE: A file must have an extension of "." (eg: *.txt, *.csv)
            if ( splitArg[1].contains(".") )
            {
                if ( splitArg[2].equals("-d") )         // Output result to screen
                {
                    // TODO: Write to screen mode for
                    //       read from a file tree
                }
                else if ( splitArg[2].equals("-w") )    // Output result to file
                {
                    // Check if the next argument after -w is a file
                    // NOTE: A file must have an extension of "." (eg: *.txt, *.csv)
                    if ( splitArg[3].contains(".") )
                    {
                        // TODO: Write to file mode
                        //       for read from a file tree
                    }
                    else
                        throw new ControllerException(
                            "Write to a file mode was selected but no file was provided\n");
                }
                else
                    throw new ControllerException(
                        "Unrecognised Output Mode (Expected: -d (Print to screen) or -w (Write to file)\n");
            }
            else
                throw new ControllerException(
                    "Read from a file mode was selected but no file was provided\n");
        }
        else if ( splitArg[0].equals("-g") )
        {
            if ( splitArg[1].equals("-d") )
            {
                // TODO: Write to screen mode for 
                //       random generated tree
            }
            else if ( splitArg[1].equals("-w") )
            {
                if ( splitArg[2].contains(".") )
                { 
                    // TODO: Write to file mode for 
                    //       random generated tree
                }
                else
                    throw new ControllerException(
                        "Write to a file mode was selected but no file was provided\n");
            }
            else
                throw new ControllerException(
                    "Unrecognised Output Mode (Expected: -d (Print to screen) or -w (Write to file)\n");
        }
        else
            throw new ControllerException(
                "Please specify the mode: -g (generate) or -r (read)\n");
    }
}
