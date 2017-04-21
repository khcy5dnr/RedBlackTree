package com.RBTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class RedBlackTreeTest {
	
	/*ArrayList for RBT validation*/
    private static ArrayList<Integer> elementArray = new ArrayList<Integer>();
    private static ArrayList<Integer> colourArray = new ArrayList<Integer>();
    private static ArrayList<Integer> positionArray = new ArrayList<Integer>();
    
	public static void main(String[] args)

    {            

       Scanner scan = new Scanner(System.in);

       /* Creating object of RedBlack Tree */

       RBTree rbt = new RBTree(Integer.MIN_VALUE); 

       System.out.println("Red Black Tree Test\n");          

       char ch;
       String RBT_validation_input = null;
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       
   	      	     	   
       /*  Perform tree operations  */

       do    
       {
           System.out.println("\nRed Black Tree Operations\n");
           System.out.println("1. insert ");
           System.out.println("2. search");
           System.out.println("3. count nodes");
           System.out.println("4. check empty");
           System.out.println("5. clear tree");
           System.out.println("6. check whether it is RBT");
           
           int choice = scan.nextInt();           

           switch (choice)
           {

           case 1 : 
               System.out.println("Enter integer element to insert");
               rbt.insert( scan.nextInt() );             
               break;                          

           case 2 :
               System.out.println("Enter integer element to search");
               System.out.println("Search result : "+ rbt.search( scan.nextInt() ));
               break;                                          

           case 3 : 
               System.out.println("Nodes = "+ rbt.countNodes());
               break;     

           case 4 : 
               System.out.println("Empty status = "+ rbt.isEmpty());
               break;     

           case 5 : 
               System.out.println("\nTree Cleared");
               rbt.makeEmpty();
               break; 
               
           case 6 :
        	   // Creating object of new RedBlack Tree
        	   RBTree newRB_Tree = new RBTree(Integer.MIN_VALUE);        	   
        	     	   
               System.out.println("\nEnter integer element to determine (integer colour position):");
               //read user input
               try {
				RBT_validation_input = br.readLine().trim();
               } 
               catch (IOException e) {				
				e.printStackTrace();
               }
               
               // split user input by  whitespace
               String[] input = RBT_validation_input.split("\\s+");
               
               //split by word or number
               int count = 0;
               boolean inputCheck = true;
       		   while(count < input.length){
	       			String[] res = input[count].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");    			
	       			count++;    			
	       			
	       			//store into respective array
	       			elementArray.add(Integer.parseInt(res[0]));
	       			
	       			/* BLACK - 1  RED - 0 */
	       			if(res[1].charAt(0) == 'B' || res[1].charAt(0) == 'b'){
   						colourArray.add(1);
   					}
	       			else if(res[1].charAt(0) == 'R' || res[1].charAt(0) == 'r'){
	       				colourArray.add(0);
	       			}
	       			else if(res[1].charAt(0) != 'B' || res[1].charAt(0) != 'b' || res[1].charAt(0) != 'R' || res[1].charAt(0) != 'r'){
	       				System.out.println("\nThis is a Red Black Tree. The colour can only be red or black. Please try again.");
	       				inputCheck = false;
	       				break;
	       			}
	       			
	       			positionArray.add(Integer.parseInt(res[2]));
	       			
       		   }	   
       		     		   
       		   if(!inputCheck){
       			   elementArray.clear();
        		   colourArray.clear();
        		   positionArray.clear();
       			   break;
       		   }
       			   
       		   
       		   //merge sort the order of input (Time complexity: O(n log (n)))  		   
       		   mergeSort(positionArray, elementArray, colourArray, 0, positionArray.size());
       		          		          		          		          		   
       		   //insert into new tree (Time complexity: O(log n))      		   
       		   
       		   count = 0;
       		   while(count < elementArray.size()){
       			   newRB_Tree.insert(elementArray.get(count));
       			   count++;
       		   }
               
       		   System.out.print("Result: ");
       		   
       		   count = 0;
       		   boolean flag = true;
       		   while(count < elementArray.size()){
       			   //search with element, colour and position (Time complexity: O(log n))
       			   if(newRB_Tree.search(elementArray.get(count), colourArray.get(count), positionArray.get(count))){
       				   flag = true;
       			   }
       			   else{
       				   flag = false;
       			   }
       			   count++;
       		   }
       		   
       		   //print result
       		   if(!flag)
       			   System.out.print(flag+"\n");
       		   else
       			   System.out.print(flag+"\n");
       		   
       		   
       		   //Display tree  

               System.out.print("\nPost order : ");
               newRB_Tree.postorder();

               System.out.print("\nPre order : ");
               newRB_Tree.preorder();

               System.out.print("\nIn order : ");
               newRB_Tree.inorder();
       		   
               //clear arraylist after using
       		   elementArray.clear();
       		   colourArray.clear();
       		   positionArray.clear();
               
               break;

           default : 
               System.out.println("Wrong Entry \n ");
               break;   
           }

           /*  Display tree  */

           if(choice != 6){
	           System.out.print("\nPost order : ");           
	           rbt.postorder();
	
	           System.out.print("\nPre order : ");
	           rbt.preorder();
	
	           System.out.print("\nIn order : ");
	           rbt.inorder(); 
           }
           System.out.println("\n\nDo you want to continue (Type y or n) \n");
           
           ch = scan.next().charAt(0);                        

       } while (ch == 'Y'|| ch == 'y');               
       scan.close();
    }
	
	public static void mergeSort(ArrayList<Integer> pos, ArrayList<Integer> element, ArrayList<Integer> colour, int low, int high) 
    {
        int N = high - low;         
        if (N <= 1) 
            return; 
        int mid = low + N/2; 
        // recursively sort 
        mergeSort(pos, element, colour, low, mid); 
        mergeSort(pos, element, colour, mid, high); 
        // merge two sorted subarrays
        int[] tempPosition = new int[N];
        int[] tempElement = new int[N];
        int[] tempColour = new int[N];
        
        int i = low, j = mid;
        int pivot = -1;
        for (int k = 0; k < N; k++) 
        {
            if (i == mid){
            	pivot = j++;
                tempPosition[k] = pos.get(pivot);
                tempElement[k] = element.get(pivot);
                tempColour[k] = colour.get(pivot);
            }
            else if (j == high){
            	pivot = i++;
                tempPosition[k] = pos.get(pivot);
                tempElement[k] = element.get(pivot);
                tempColour[k] = colour.get(pivot);
            }
            else if (pos.get(j) < pos.get(i)){
            	pivot = j++;
                tempPosition[k] = pos.get(pivot);
                tempElement[k] = element.get(pivot);
                tempColour[k] = colour.get(pivot);
            }
            else{
            	pivot = i++;
            	tempPosition[k] = pos.get(pivot);
            	tempElement[k] = element.get(pivot);
                tempColour[k] = colour.get(pivot);
            }
                
        }    
        for (int k = 0; k < N; k++){ 
            pos.set(low + k, tempPosition[k]);
            element.set(low + k, tempElement[k]);
            colour.set(low + k, tempColour[k]);
        }	
    }
	
}
