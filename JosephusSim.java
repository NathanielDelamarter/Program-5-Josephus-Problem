import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         size = 0;
         
         Scanner file = new Scanner(new File(fileName));
         circle = new PersonNode(file.next());
         size++;
         
         track = circle;
         while(file.hasNext()){
           // add(file);
           track.next = new PersonNode(file.next());
           track = track.next;
           size++;
         }
         
         // make the ring circular by attaching last node's next to front
         track.next = circle; 
         
         // remember the last node as the one in front of the next to get eliminated
         //I used track to loop through my list so track already is the last node
         
         // generate, print, and save the random elimination count
         Random random = new Random();
         eliminationCount = random.nextInt(size / 2) + 1;
         System.out.println("Elimination count is " + eliminationCount);
         
         } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
      
   }
   
   public void eliminate() {
      if(isOver()){
         //do nothing since there is only one person left
      }
      // count to the elimination count
      for(int i = 0; i < eliminationCount - 1; i++){
         track = track.next;
      }
      // print who will be eliminated
      System.out.println(track.next.name + " eliminated!");
      // eliminate the person and update "front" of the circle and size
      track.next = track.next.next;
      size--;
      track = track.next;
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      // print the remaining survivors (watch out for infinite loop since list is circular)
      return "";
   }

}