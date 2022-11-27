import java.io.*;
import java.util.Scanner;

public class Suche{
  
  private static String gesucht = new String();
  private static String text;  
  private static String[] atext;
  private static String[] agesucht; 
  private static int[] achar;        //0=_ / 1=word
  
  public static void main(String[]args) throws IOException{
    
    //*************Hier File einlesen***********************************************
    File file = new File("stoerung67.txt");         
    //******************************************************************************
    
    Scanner sc = new Scanner(file, "UTF-8"); 
    gesucht = sc.nextLine();
        
    System.out.println("Ausgewählte Lücke im Text: " + gesucht);
    System.out.println("-------------------------------------------------------");
    
    reading();     //step 1: reads book
  }
  
  public static void reading() throws IOException{
    
    File file = new File("Alice_Im_Wunderland.txt");
    Scanner sc = new Scanner(file, "UTF-8");     //making sure umlauts are recognized as well
    
    while (sc.hasNextLine()) {
      text += sc.nextLine();
      text += " ";
    }
    
    preperation(); //step 2: init of arrays etc
  }
  

  public static void preperation(){
    
    atext = text.split(" ");
    
    agesucht = gesucht.split(" ");
    
    achar = new int[agesucht.length];
    
    for(int i = 0; i < achar.length; i++){
      if(!agesucht[i].equals("_")){
        achar[i] = 1;
      } else {
        achar[i] = 0;  
      } 
    }
     
    deleteMarks();  //step 3: deletes marks from words in the book text
    
  }
  
  public static void deleteMarks(){
    for (int i = 0; i < atext.length; i++) {
      if (atext[i].contains(",")) {
        atext[i] = atext[i].replace(",","");
      } 
      if (atext[i].contains(".")) {
        atext[i] = atext[i].replace(".","");
      }  
      if (atext[i].contains("«")) {
        atext[i] = atext[i].replace("«","");
      }  
      if(atext[i].contains("»")){
        atext[i] = atext[i].replace("»","");
      } 
      if (atext[i].contains(";")) {
        atext[i] = atext[i].replace(";","");
      }
      if (atext[i].contains("?")) {
        atext[i] = atext[i].replace("?","");
      } 
      if (atext[i].contains("(")) {
        atext[i] = atext[i].replace("(","");
      } 
      if (atext[i].contains(")")) {
        atext[i] = atext[i].replace(")","");
      } 
      if (atext[i].contains("_")) {
        atext[i] = atext[i].replace("_","");
      } 
      if (atext[i].contains(":")) {
        atext[i] = atext[i].replace(":","");
      } 
      if (atext[i].contains("!")) {
        atext[i] = atext[i].replace("!","");
      } 
    } 
    for (int i = 0; i < agesucht.length; i++) {
      if (agesucht[i].contains(",")) {
        agesucht[i] = agesucht[i].replace(",","");
      }
      if (agesucht[i].contains(".")) {
        agesucht[i] = agesucht[i].replace(".","");
      }   
    } 
    
    findStart();
  }  
  
  public static void findStart(){
    int count = 0;
    for(int i = 0; i < atext.length; i++){                //looping the book
      if (atext[i].equalsIgnoreCase(agesucht[0])) {       //possible starting point found
        checking(i);                                      //i is possible start index in text
        count++;
      } 
    } 
    
    if (count == 0) {
      System.out.println("Keine Lösung möglich");
    } 
  } 
  
  public static void checking(int j){  //j is possible start index in text
    
    int agesuchtl = agesucht.length;
    
    boolean failed = false;     
    
    String part = new String();
    
    int spacesUsed = 0;   //the amount of available space is determined by the amount of _ in the searched for text
    
    for (int i = 0; i < agesuchtl; i++) {
      if (achar[i]==0){
        spacesUsed ++;
      }
      
      if (agesucht[i].equalsIgnoreCase(atext[i+j]) || achar[i]==0) {
        part += atext[i+j] + " ";
      } else {
        failed = true;
      } 
    }
    
    if (!failed) {
      System.out.println("Lösung gefunden!: " + part);
    }
  } 
}
