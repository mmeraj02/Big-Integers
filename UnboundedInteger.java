/**
 * Project: BigIntegers
 * @author Mehedi Meraj
 */

import java.util.*;
public class UnboundedInteger
{
	public LinkedListNode head;
	private int size;
    private int sign;
	
	// Constructor 1
	// creating a null linked list.
	public UnboundedInteger() 
    {
		this.head = null;
		this.size = 0;
	}
	// constructor 2
	// The String => Object
	// The construct takes a string then construct a linkedlist to represent that string
	// in a reverse odrer having the sign at the head node
    public UnboundedInteger(String str) 
    {
		String aStr = "";
		char c = str.charAt(0);
        if(str.length() == 1 && str.equals("0"))
        {
            sign = 1;
            aStr = "";
        }
		else if(str.length() == 2 && str.equals("-0"))
        {
            sign = 1;
            aStr = "";
        }
        else if(c == '-')
        {
            sign = -1;
            aStr = str.substring(1, str.length());
        }
        else
        {
            sign = 1;
            aStr = str.substring(0, str.length());
        }

		addIntegersToList(aStr);
	}

	// constractor 3
	// LinkedListNode => Object
	// This constructor takes a LinkedListNode and returns an object adding that node to the tail
	public UnboundedInteger(LinkedListNode aNode) 
	{
        this.addToTheLast(aNode);
    }
 
	// Getters and Setters

	// getSize method
	// to get the size of the linked list
	public int getSize() 
	{
		return this.size;
	}

	// getHeadData method
	// returns int
	// to get the head data of the linked list
	public int getHeadData()
	{
		return (int) head.data;
	}
	// getNextData method
	// returns LinkedListNode
	// to the the next node from head
	public LinkedListNode getNextNode()
	{
		return  head.getNext();
	}
	// removeFirst method
	// return UnboundedInteger object
	// this method will remove the head node from the begining of the linked list
	public UnboundedInteger removeFirstNode()
	{
		UnboundedInteger returnObj = null;;
		if(this.head.getNext() != null)
		{
			returnObj =  new UnboundedInteger(this.head.getNext());
		}
		return returnObj;
	}
	// addToTheLast
	public void addToTheLast(LinkedListNode aNode) {
        if(head == null) 
		{
            head = aNode;
        } 
		else 
		{
            LinkedListNode temp = head;
            while(temp.getNext() != null) 
			{
                temp = temp.getNext();
            }
            temp.setNext(aNode);
        }
        int nLen = 0;
        while(aNode != null) 
		{
            nLen++;
            aNode = aNode.getNext();
        }
        size += nLen;
    }
	

    // addIntegersToList method
    // String => void
    // This method takes a string and return void.
    // The method makes a list from the string of integers to represents each number also with the sign at the beginnig.
    private void addIntegersToList(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            addFirst(Integer.parseInt(str.substring(i, i+1)));
        }
        addFirst(sign);
    }
	// addFirst method.
	// adding a data to the beginning of the linked list
	private void addFirst(Object item)
    {
		head = new LinkedListNode(item, head);
		size++;
	}
	
	// printList method
    // This method print the UnboundedInteger object.
	public String toString() 
    {
		String output = "";
		if(head != null)
		{
			LinkedListNode current = head;
			output += "[" + current.getData() + "], ";
			current = current.getNext();
			while(current != null)
			{
				output += "[" + current.getData() + "], ";
				current = current.getNext();
			}
		}
		return output; 
    }

	// equals
	// UnboundedInteger => boolean
	// This method takes an unbounded integer and check if that is equal to the reference object
	// then retuns true or false according to the algorithm. 
    public boolean equals(UnboundedInteger obj)
    {
        boolean flag = false;
        if(this == null || obj == null)
        {
            flag = false;
        }
		else if((this.getHeadData() == obj.getHeadData()) && (this.getNextNode() == null && obj.getNextNode() == null))
		{
			flag = true;
		}
        while(this.getNextNode() != null && obj.getNextNode() != null)
		{
			if(this.getHeadData() == obj.getHeadData())
			{
				flag = true;
			}
			else
			{
				flag = false;
				break;
			}
			this.head = this.getNextNode();
			obj.head = obj.getNextNode();
		}
		return flag;
    }

	// this LinkedListNode class will help us to use the functions of the LinkedListNode.
	private static class LinkedListNode 
    {
		private Object data; // reference to the data 
		private LinkedListNode next;	 // reference to the next node
		// Constructors
		
		/** Creates a new node with a null next field.
		 * 
		 *  @param dataItem The data we want to store
		 *   
		 */
		
		private LinkedListNode(Object dataItem) 
        {
			data = dataItem;
			next = null;
		}
		
		/** Creates a new node that references another node.
		 * 
		 *  @param dataItem The data we want to store
		 *  @param nodeRef The node references by the new node
		 *  
		 */
		
		private LinkedListNode(Object dataItem, LinkedListNode nodeRef) 
        {
				data = dataItem;
				next = nodeRef;
		}

        // Getters and setters.
		private LinkedListNode getNext() 
        {
			return next;
		}
		
		private void setNext(LinkedListNode aRef) 
        {
			this.next = aRef;
		}
		
		private void setData(Object dataItem) 
        {
			this.data = dataItem;
		}
		
		private Object getData() 
        {
			return this.data;
		}
		
	}
	
}