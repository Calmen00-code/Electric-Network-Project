----------------------
Command Line Arguments
----------------------

-r | -g
    Specifies the type of the way to generate the Network Tree
    (File name is required to be input when -r or -g was selected)

        -r - Network Tree is generated based on reading the contents from a file

        -g - Network Tree is generated based on randomly selected value from the filename which 
             consist of a sets of value for Root, Non-Root, and Leaf Node.
            
    Example 1: java -jar dist/Assignment1.jar "-r resources/input.txt -w resources/output.txt"
    Example 2: java -jar dist/Assignment1.jar "-g resources/random.txt -w resources/output.txt"


-w | -d 
    Specifies the type of the way to display the Network Tree
    (File name is required to be input when -w was selected)

        -w - Results of the Network Tree generated was written to the specified file

        -d - Results of the Network Tree generated was displayed to the console screen

    Example 1: java -jar dist/Assignment1.jar "-r resources/input.txt -w resources/output.txt"
    Example 2: java -jar dist/Assignment1.jar "-g resources/random.txt -d"


All possible outcome that are testable
    java -jar dist/Assignment1.jar "-r resources/input.txt -w resources/output.txt"
    java -jar dist/Assignment1.jar "-r resources/input.txt -d"
    java -jar dist/Assignment1.jar "-g -w resources/output.txt"
    java -jar dist/Assignment1.jar "-g -d"
