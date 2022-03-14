/**
 * Project: BigIntegers
 * @author Mehedi Meraj
 */

import java.util.*;
public class UnboundedIntegerOperations extends UnboundedInteger
{
    private String sign;
    private String diff; 
    private String sum; 
    private int comparable;

    // Constructor
    // This constuctor takes two string and decides the sign base on operation. 
    // and the operations depends on the condition
    public UnboundedIntegerOperations(String str1, String str2)
    {
        // Using checkSign helper method to determine the sign of the each number.
        String firstSign = checkSign(str1);
        String secondSign = checkSign(str2);

        // Sign check for add method
        // determining the sign after the add operation
        if((firstSign.equals("") && secondSign.equals("")) || (firstSign.equals("-") && secondSign.equals("-")))
        {
            if(firstSign.equals("") && secondSign.equals(""))
            {
                sign = "";
            }
            else if(firstSign.equals("-") && secondSign.equals("-"))
            {
                sign = "-";
            }

        }
        // sign check for substruct method
        // determining the sign after the subtract operation
        else
        {
            if(firstSign.equals("") && secondSign.equals("-"))
            {
                if(compareAbs(str1, str2.substring(1)) == 0)
                {
                    sign = "";
                    comparable = 0;
                }
                else if(compareAbs(str1, str2.substring(1)) == -1)
                {
                    sign = "-";
                    comparable = -1;
                }
                else if(compareAbs(str1, str2.substring(1)) == 1)
                {
                    sign = "";
                    comparable = 1;
                }
            }
            else if(firstSign.equals("-") && secondSign.equals(""))
            {
                if(compareAbs(str1.substring(1), str2) == 0)
                {
                    sign = "";
                    comparable = 0;
                }
                else if(compareAbs(str1.substring(1), str2) == -1)
                {
                    sign = "";
                    comparable = -1;
                }
                else if(compareAbs(str1.substring(1), str2) == 1)
                {
                    sign = "-";
                    comparable = 1;
                }
            }
        }
    }
    
    // doOperations method
    // UnboundedInteger, UnboundedInteger => UnboundedInteger
    // This public method will take two UnboundedInteger and will do the operation
    // according to the sign of the inputs.
    public UnboundedInteger doOperations(UnboundedInteger uI1, UnboundedInteger uI2)
    {
        if(uI1.getSize() == 1 && uI2.getSize() == 1)
        {
            return new UnboundedInteger("0");
        }

        else if(((uI1.getHeadData() == 1) && (uI2.getHeadData() == 1)) || ((uI1.getHeadData() == -1) && (uI2.getHeadData() == -1)))
        {
            return this.add(uI1, uI2);
        }
        else
        {
            return this.subtract(uI1, uI2);
        }
    }


    // add method
    // UnboundedInteger, UnboundedInteger => UnboundedInteger
    // the add method takes two UnboundedIntegers and will return the sum of those integers represented by a linked list
    private UnboundedInteger add(UnboundedInteger num1, UnboundedInteger num2)
    {
        // calling recursive helper method.
        String addition = addRecursion(num1.removeFirstNode(), num2.removeFirstNode(), 0);
        StringBuffer sbr = new StringBuffer(addition);
        // reversing the string
        String revString = sign + sbr.reverse().toString();
        // representing the string of sum by a linkedlist
        UnboundedInteger addValue = new UnboundedInteger(revString);
        return addValue;

    }

    // subtract method
    // UnboundedInteger, UnboundedInteger => UnboundedInteger
    // the add method takes two UnboundedIntegers and will return the subtract of those integers represented by a linked list.
    // The algorithm of this method is designed in a way so that it only subtract the 2nd argument from 1st argument.
    private UnboundedInteger subtract(UnboundedInteger num1, UnboundedInteger num2)
    {
        String difference = "";
        if(comparable == 0)
        {
            // calling recursive helper method.
            difference =  subRecursion(num1.removeFirstNode(), num2.removeFirstNode(), 0);   
        }
        else if(comparable == 1)
        {
            // calling recursive helper method.
            difference =  subRecursion(num1.removeFirstNode(), num2.removeFirstNode(), 0);
        }
        else if(comparable == -1)
        {
            // calling recursive helper method.
            difference =  subRecursion(num2.removeFirstNode(), num1.removeFirstNode(), 0);
        }
        // StringBuffer object.
        StringBuffer sbr = new StringBuffer(difference);

        // reversing the string
        String revString = sign + sbr.reverse().toString();

        //getting rid of leading zeros.
        String finalString = noLeadingZeroStr(revString);
        // representing the string of subtract by a linkedlist
        UnboundedInteger subValue = new UnboundedInteger(finalString);
        return subValue;
    }

    // addRecursion
    // UnboundedInteger, UnboundedInteger  => String
    // This method takes two UnboundedIntegers of linked list and add them using recursion.
    private String addRecursion(UnboundedInteger uI1, UnboundedInteger uI2, int carryOver)
    {
        int result = 0;
        sum = "";
        // checking if both objects are null and carryover is 0
        if(uI1 == null && uI2 == null && carryOver == 0)
        {
            sum = sum;
        }
        else
        {
            // if the object is not null then returns the head data and store it into a variable else make that data eqauls 0
            int topValue = (int) ((uI1 != null) ? uI1.getHeadData() : 0);
            int bottomValue = (int) (uI2 != null ? uI2.getHeadData() : 0);


            result = (topValue + bottomValue + carryOver) % 10;
            carryOver = (topValue + bottomValue + carryOver) / 10;

            // if the object is not null remove the head node else returns null.
            UnboundedInteger newObj1 = (uI1 != null) ? uI1.removeFirstNode() : null;
            UnboundedInteger newObj2 = (uI2 != null) ? uI2.removeFirstNode() : null;
    
            if(uI1 != null || uI2 != null) 
            {
                // recursive call
                sum += Integer.toString(result) + addRecursion(newObj1, newObj2, carryOver);
            }

        }
        return sum;
    }

    // subRecursion
    // UnboundedInteger, UnboundedInteger  => String
    // This method takes two UnboundedIntegers of linked list and subtract them using recursion.
    private String subRecursion(UnboundedInteger uI1, UnboundedInteger uI2, int carryOver)
    {
        int result = 0;
        diff = "";
        // checking if both objects are null and carryover is 0
        if(uI1 == null && uI2 == null && carryOver == 0)
        {
            diff = diff;
        }
        else
        {
            // if the object is not null then returns the head data and store it into a variable else make that data eqauls 0
            int topValue = (int) ((uI1 != null) ? uI1.getHeadData() : 0);
            int bottomValue = (int) ((uI2 != null) ? uI2.getHeadData() : 0);

            result = (topValue - bottomValue - carryOver);
            if(result < 0)
            {
                result = result + 10;
                carryOver = 1;
            }
            else
            {
                carryOver = 0;
            }
            // if the object is not null remove the head node else returns null.
            UnboundedInteger newObj1 = (uI1 != null)? uI1.removeFirstNode() : null;
            UnboundedInteger newObj2 = (uI2 != null)? uI2.removeFirstNode() : null;
            if((uI1 != null) || (uI2 != null))
            {
                // recursive call
                diff += Integer.toString(result) + subRecursion(newObj1, newObj2, carryOver);
            }
        }
        return diff;
    }
    // checkSign
    // String => String
    // This method will return the sign of a number represented in a string. 
    private String checkSign(String str)
    {
        if(str.substring(0,1).equals("-"))
        {
            return "-";
        }
        return "";
    }

    // compareAbs method
    // String => int
    // This helper method is designed to determine which number is relativelt bigger out of two numbers
    // The method is only used to help to determine which input goes into subtract method.
    public int compareAbs(String uI1, String uI2)
    {
        byte[] valueArr1 = toArrays(uI1);
        byte[] valueArr2 = toArrays(uI2);
        int comparable = 0;
        if(uI1 == null || uI2 == null)
        {
            throw new IllegalArgumentException("Either of argument is null.");
        }
        else if(valueArr1.length < valueArr2.length)
        {
            comparable = -1;
        }
        else if(valueArr1.length > valueArr2.length)
        {
            comparable = 1;
        }
        // if the length is equal then executes the codes below.
        else
        {
            for(int i = valueArr1.length - 1, j = valueArr2.length - 1; i >= 0 && j >= 0; i--, j--)
            {
                if(valueArr1[i] == valueArr2[j])
                {
                    comparable = 0;
                }
                else if(valueArr1[i] < valueArr2[j])
                {
                    comparable = -1;
                    break;
                }
                else if(valueArr1[i] > valueArr2[j])
                {
                    comparable = 1;
                    break;
                }
            }
        }
        return comparable;
    }
    // toArrays method
    // String => Array
    // This method will take a string of numbers and create an array of having each digits in each index.
    // The method is a helper method for compareAbs method.
    private byte[] toArrays(String str)
    {
        int len = str.length();
        byte[] arr = new byte[len];
        for(int i = len - 1, j = 0 ; i >= 0 && j < len; i--, j++)
        {
            arr[i]= Byte.parseByte(str.substring(j, j+1));
        }
        return arr;
    }

    // Helper method for subtract method.
    // String => String
    // This method drops zeros from the string of the number and 
    // then store the new number in a updated string with considering the positive and negative sign.
    private String noLeadingZeroStr(String inValidStr)
    {
        String finalStr = "";

        if(inValidStr.charAt(0) == '-')
        {
            int count = 1;
            for(int i = 1; i < inValidStr.length(); i++)
            {
                if(inValidStr.charAt(i) == '0')
                {
                    finalStr = "0";
                    count = count + 1;
                }
                else
                {
                    finalStr = "-";
                    break;
                }
            }
            finalStr = finalStr + inValidStr.substring(count);
        }
        else if(inValidStr.charAt(0) == '+')
        {
            int count = 1;
            for(int i = 1; i < inValidStr.length(); i++)
            {
                if(inValidStr.charAt(i) == '0')
                {
                    finalStr = "0";
                    count = count + 1;
                }
                else
                {
                    finalStr = "";
                    break;
                }
            }
            finalStr = finalStr + inValidStr.substring(count);
        }
        else
        {
            int count = 0;
            for(int i = 0; i < inValidStr.length(); i++)
            {
                if(inValidStr.charAt(i) == '0')
                {
                    finalStr = "0";
                    count = count + 1;
                }
                else
                {
                    finalStr = "";
                    break;
                }
            }
            finalStr = finalStr + inValidStr.substring(count);
        }
        return finalStr;
    }
}