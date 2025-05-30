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
         size = 0; //initialize the size to be zero
         
         Scanner file = new Scanner(new File(fileName));
         circle = new PersonNode(file.next()); // initialize front of the list to be 
         size++; // inciment the size upon adding
         
         track = circle; // initialize the tracking pointer to the front of the list
         while(file.hasNext()){ // use tracker to move through the list and continuously add nodes with names = file.next()
           // add(file);
           track.next = new PersonNode(file.next());
           track = track.next;
           size++; // increment size each time you add a name;
         }
         
         // make the ring circular by attaching last node's next to front
         track.next = circle; //since track was used to read everything in to the list, track should be at the end already
         //so simply link the end to the front node
         
         // remember the last node as the one in front of the next to get eliminated
         //track already is the last node, see above comment
         
         // generate, print, and save the random elimination count
         Random random = new Random(); // create random obj
         eliminationCount = random.nextInt(size / 2) + 1; //specified that the elimination count should be a random number between ...
         // 1 and half the size
         System.out.println("Elimination count is " + eliminationCount);
         
         } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
      //did not use this because I figured if you did use add you would have to 
      //loop through the entire list and get to the end every time you wanted to add on a nae to the list
      //I believe this is O(n^2) which is not very efficient 
      //I could be wrong though.
   }
   
   public void eliminate() {
      if(isOver()){
         //do nothing since there is only one person left
      }
      else{
      // count to the elimination count
      for(int i = 0; i < eliminationCount - 1; i++){
         track = track.next; // increment track till you reach one before the one you have to eliminate
         //this makes it so you can easiy eliminate the target node
      }
      // print who will be eliminated
      System.out.println(track.next.name + " eliminated!");
      // eliminate the person and update "front" of the circle and size
      track.next = track.next.next; // 'skip' over the node and thus eliminate it 
      size--; // just eliminated so decrement the size
      circle = track.next; // update the front to track.next, since we always want track to be the last node
      // and since we're circular track.next must always be the front node
      }
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
   String str = "Remaining survivors: ";
      // if there's only one person left, print them as the last survivor
      if(isOver()){ 
         str = track.name + " is the last survivor!";
      }
      // print the remaining survivors (watch out for infinite loop since list is circular)
      else{
         int tempNum = 0; //this is to handle the fence post problem of putting a comma after the last node is printed
         for(int i = 1; i <= size - 1; i++){ // loop till one before the last node to solve the fence post
            track = track.next;
            str += i + "-" + track.name + ", "; // add the position and the name to the string
            tempNum = i; // track i to be used in the last nodes printing 
         }
         //handle the fence post by doing the last node manually
         track = track.next;
         tempNum += 1;
         str += tempNum + "-" + track.name; 
      }
      return str;
   }

}

/*
  ----jGRASP exec: java JosephusDriver
 Elimination count is 3
 Remaining survivors: 1-Brian, 2-Delon, 3-Aryton, 4-Eli, 5-Nate, 6-Cameron, 7-Mohamed, 8-Adrian, 9-Abner, 10-Tom, 11-Egor, 12-Wissal, 13-Shunyun, 14-Percy, 15-Antonio, 16-Aziz, 17-Biruk, 18-Max, 19-Jason, 20-Riane, 21-Matthew
 
 Continue elimination? <press enter>
 
 Aryton eliminated!
 Remaining survivors: 1-Eli, 2-Nate, 3-Cameron, 4-Mohamed, 5-Adrian, 6-Abner, 7-Tom, 8-Egor, 9-Wissal, 10-Shunyun, 11-Percy, 12-Antonio, 13-Aziz, 14-Biruk, 15-Max, 16-Jason, 17-Riane, 18-Matthew, 19-Brian, 20-Delon
 
 Continue elimination? <press enter>
 
 Cameron eliminated!
 Remaining survivors: 1-Mohamed, 2-Adrian, 3-Abner, 4-Tom, 5-Egor, 6-Wissal, 7-Shunyun, 8-Percy, 9-Antonio, 10-Aziz, 11-Biruk, 12-Max, 13-Jason, 14-Riane, 15-Matthew, 16-Brian, 17-Delon, 18-Eli, 19-Nate
 
 Continue elimination? <press enter>
 
 Abner eliminated!
 Remaining survivors: 1-Tom, 2-Egor, 3-Wissal, 4-Shunyun, 5-Percy, 6-Antonio, 7-Aziz, 8-Biruk, 9-Max, 10-Jason, 11-Riane, 12-Matthew, 13-Brian, 14-Delon, 15-Eli, 16-Nate, 17-Mohamed, 18-Adrian
 
 Continue elimination? <press enter>
 
 Wissal eliminated!
 Remaining survivors: 1-Shunyun, 2-Percy, 3-Antonio, 4-Aziz, 5-Biruk, 6-Max, 7-Jason, 8-Riane, 9-Matthew, 10-Brian, 11-Delon, 12-Eli, 13-Nate, 14-Mohamed, 15-Adrian, 16-Tom, 17-Egor
 
 Continue elimination? <press enter>
 
 Antonio eliminated!
 Remaining survivors: 1-Aziz, 2-Biruk, 3-Max, 4-Jason, 5-Riane, 6-Matthew, 7-Brian, 8-Delon, 9-Eli, 10-Nate, 11-Mohamed, 12-Adrian, 13-Tom, 14-Egor, 15-Shunyun, 16-Percy
 
 Continue elimination? <press enter>
 
 Max eliminated!
 Remaining survivors: 1-Jason, 2-Riane, 3-Matthew, 4-Brian, 5-Delon, 6-Eli, 7-Nate, 8-Mohamed, 9-Adrian, 10-Tom, 11-Egor, 12-Shunyun, 13-Percy, 14-Aziz, 15-Biruk
 
 Continue elimination? <press enter>
 
 Matthew eliminated!
 Remaining survivors: 1-Brian, 2-Delon, 3-Eli, 4-Nate, 5-Mohamed, 6-Adrian, 7-Tom, 8-Egor, 9-Shunyun, 10-Percy, 11-Aziz, 12-Biruk, 13-Jason, 14-Riane
 
 Continue elimination? <press enter>
 
 Eli eliminated!
 Remaining survivors: 1-Nate, 2-Mohamed, 3-Adrian, 4-Tom, 5-Egor, 6-Shunyun, 7-Percy, 8-Aziz, 9-Biruk, 10-Jason, 11-Riane, 12-Brian, 13-Delon
 
 Continue elimination? <press enter>
 
 Adrian eliminated!
 Remaining survivors: 1-Tom, 2-Egor, 3-Shunyun, 4-Percy, 5-Aziz, 6-Biruk, 7-Jason, 8-Riane, 9-Brian, 10-Delon, 11-Nate, 12-Mohamed
 
 Continue elimination? <press enter>
 
 Shunyun eliminated!
 Remaining survivors: 1-Percy, 2-Aziz, 3-Biruk, 4-Jason, 5-Riane, 6-Brian, 7-Delon, 8-Nate, 9-Mohamed, 10-Tom, 11-Egor
 
 Continue elimination? <press enter>
 
 Biruk eliminated!
 Remaining survivors: 1-Jason, 2-Riane, 3-Brian, 4-Delon, 5-Nate, 6-Mohamed, 7-Tom, 8-Egor, 9-Percy, 10-Aziz
 
 Continue elimination? <press enter>
 
 Brian eliminated!
 Remaining survivors: 1-Delon, 2-Nate, 3-Mohamed, 4-Tom, 5-Egor, 6-Percy, 7-Aziz, 8-Jason, 9-Riane
 
 Continue elimination? <press enter>
 
 Mohamed eliminated!
 Remaining survivors: 1-Tom, 2-Egor, 3-Percy, 4-Aziz, 5-Jason, 6-Riane, 7-Delon, 8-Nate
 
 Continue elimination? <press enter>
 
 Percy eliminated!
 Remaining survivors: 1-Aziz, 2-Jason, 3-Riane, 4-Delon, 5-Nate, 6-Tom, 7-Egor
 
 Continue elimination? <press enter>
 
 Riane eliminated!
 Remaining survivors: 1-Delon, 2-Nate, 3-Tom, 4-Egor, 5-Aziz, 6-Jason
 
 Continue elimination? <press enter>
 
 Tom eliminated!
 Remaining survivors: 1-Egor, 2-Aziz, 3-Jason, 4-Delon, 5-Nate
 
 Continue elimination? <press enter>
 
 Jason eliminated!
 Remaining survivors: 1-Delon, 2-Nate, 3-Egor, 4-Aziz
 
 Continue elimination? <press enter>
 
 Egor eliminated!
 Remaining survivors: 1-Aziz, 2-Delon, 3-Nate
 
 Continue elimination? <press enter>
 
 Nate eliminated!
 Remaining survivors: 1-Aziz, 2-Delon
 
 Continue elimination? <press enter>
 
 Aziz eliminated!
 Delon is the last survivor!
 
 
*/