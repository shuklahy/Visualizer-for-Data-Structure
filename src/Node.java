
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitendra
 */
public class Node{
    
     public int data;
     public Node next;
     public Node(int data){
         this.data = data;
     }
     
     public void printNode(){
         System.out.println("["+this.data+"]");
     }
     
     }
