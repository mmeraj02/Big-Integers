/**
 * Project: BigIntegers
 * @author Mehedi Meraj
 */

// The test class is designed to test the cases only scanning from files. 
import java.util.*;
import java.io.*;

public class TestUIOps
{
    public static void main(String[] args)
    {
        // Step 1
        // Constructing a scanner object hold a file path from the user input to a string.
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file location: ");
        String filePath = sc.nextLine();
        sc.close();

        // Step 2
        // Constructing a file object to hold the file path. 
        File myfile = new File(filePath);

        // Step 3
        // try and catch clause
        // the object only can take a string as input from a flie.
        try
        {
            Scanner readFile = new Scanner(myfile);
            // Scanning each lines and storing them into their designated variable
            String num1 = readFile.nextLine();
            String num2 = readFile.nextLine();
            String sumOrDiff = readFile.nextLine();
            String comment = readFile.nextLine();

            // Creating the UnboundedInteger object using the operands.
            UnboundedInteger firstLinkedList = new UnboundedInteger(num1);
            UnboundedInteger secondLinkedList = new UnboundedInteger(num2);

            // Calling UnboundedIntegerOperations to determine the sign.
            UnboundedIntegerOperations obj = new UnboundedIntegerOperations(num1, num2);

            // constructing UnboundedInteger null object.
            UnboundedInteger printObj = new UnboundedInteger();

            // assing the expected UnboundedInteger object from doOperations method to the printObj.
            printObj = obj.doOperations(firstLinkedList, secondLinkedList);

            // Printing the expected sum or substract.
            System.out.println(printObj.toString());

            // making a object of the result from text file. 
            UnboundedInteger output = new UnboundedInteger(sumOrDiff);
            System.out.println(output.toString());

            // Printing the boolean value of equals method. 
            System.out.println(output.equals(printObj));
            // Printing out corresponding comments.
            if(output.equals(printObj))
            {
                System.out.println(comment);
            }
            else
            {
                throw new IllegalArgumentException("Either input is not valid or the algorithm does not work in this case");
            }
            

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}