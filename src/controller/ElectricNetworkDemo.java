/**
* Controller class to start up the program for Electric Network Demonstration
* @associate Output.java, TreeGenerator.java
* @author Calmen Chia
*/

package Assignment1.controller;
import Assignment1.view.*;
import Assignment1.model.*;

public class ElectricNetworkDemo
{
    public static void main(String[] args)
    {
        PrintMessage printMsg = new PrintMessage();

        try
        {
            String[] splitArg = args[0].split(" ");

            // Minimum parameter that the user must supply
            if ( splitArg.length < 2 )
                printMsg.print(null, "Arguments cannot be empty!\n");
            else if ( splitArg.length > 4 )
                printMsg.print(null, "Number of Arguments exceeded the limit!\n");
            else
            {
                try
                {
                    parseArgument(splitArg);
                } catch ( ControllerException e ) {
                    printMsg.print(null, e.getMessage());
                }
            }
        } catch ( ArrayIndexOutOfBoundsException e ) {
            printMsg.print(null, "Please supply arguments as mode!\n");
        }
    }

    public static void parseArgument ( String[] splitArg ) throws ControllerException
    {
        TreeGenerator generator = null;
        PrintMessage printMsg = new PrintMessage();
        ScreenDisplayTree screen = new ScreenDisplayTree();
        WriteFileTree writeFile = new WriteFileTree();
        City city = null;

        if ( splitArg[0].equals("-r") )
        {
            // Check if the next argument after -r is a file
            // NOTE: A file must have an extension of "." (eg: *.txt, *.csv)
            if ( splitArg[1].contains(".") )
            {
                try
                {
                    generator = new FileGenerator();
                    city = generator.generateTree(splitArg[1]);

                    if ( splitArg[2].equals("-d") ) {        // Output result to screen
                        if ( splitArg.length > 3 )
                            throw new ControllerException("Maximum mode selected has reached!\n");
                        screen.print( city, "" );
                    }
                    else if ( splitArg[2].equals("-w") ) { // Output result to file
                        try
                        {
                            // Check if the next argument after -w is a file
                            // NOTE: A file must have an extension of "." (eg: *.txt, *.csv)
                            if ( splitArg[3].contains(".") )
                            {
                                if ( splitArg.length > 4 )
                                    throw new ControllerException("Maximum mode selected has reached!\n");
                                writeFile.print( city, splitArg[3] );
                                // TODO: Write to file mode
                                //       for read from a file tree
                            }
                            else
                                throw new ControllerException(
                                    "Write to a file mode was selected but no file was provided\n");
                        }
                        catch(ArrayIndexOutOfBoundsException e) {
                            throw new ControllerException(
                                "Write to a file mode was selected but no file was provided\n");
                        }
                    }
                    else
                        throw new ControllerException(
                            "Unrecognised Output Mode (Expected: -d (Print to screen) or -w (Write to file)\n");
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    throw new ControllerException(
                        "Unrecognised Output Mode (Expected: -d (Print to screen) or -w (Write to file)\n");
                }
                catch(ModelException e) {
                    printMsg.print(null, e.getMessage());
                }
            }
            else
                throw new ControllerException(
                    "Read from a file mode was selected but no file was provided\n");
        }
        else if ( splitArg[0].equals("-g") )
        {
            if ( splitArg[1].equals("-d") ) {   // Output to the screen
                if ( splitArg.length > 2 )
                    throw new ControllerException("Maximum mode selected has reached!\n");
                // TODO: Write to screen mode for 
                //       random generated tree
            }
            else if ( splitArg[1].equals("-w") ) {
                try
                {
                    if ( splitArg[2].contains(".") ) { 
                        if ( splitArg.length > 3 )
                            throw new ControllerException("Maximum mode selected has reached!\n");
                        // TODO: Write to file mode for 
                        //       random generated tree
                    }
                    else
                        throw new ControllerException(
                            "Write to a file mode was selected but no file was provided\n");
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    throw new ControllerException(
                        "Unrecognised Output Mode (Expected: -d (Print to screen) or -w (Write to file)\n");
                }
            }
            else
                throw new ControllerException(
                    "Unrecognised Output Mode (Expected: -d (Print to screen) or -w (Write to file)\n");
        }
        else
            throw new ControllerException(
                "Please specify the mode: -g (generate) or -r (read) in the beginning\n");
    }
}
