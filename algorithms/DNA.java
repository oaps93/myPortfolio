/*
Write a DNA.java program that determines whether there is a protein in a strand of DNA.

A protein has the following qualities:

It begins with a “start codon”: ATG.
It ends with a “stop codon”: TGA.
In between, each additional codon is a sequence of three nucleotides.
So for example:
*/
import java.util.ArrayList;


public class DNA {
  

  public static void main (String[] args){

      //  -. .-.   .-. .-.   .
    //    \   \ /   \   \ / 
    //   / \   \   / \   \  
    //  ~   `-~ `-`   `-~ `-

    // This is a searching PROTEIN aplication inside a DNA.

    String dna1 = new String("ATGCGATACGCTTGA");
    String dna2 = new String("ATGCGATACGTGA");
    String dna3 = new String("ATTAATATGTACTGA");
    

    ArrayList<Integer> subsProtein = new ArrayList<Integer>();

    //String dna = new String("ATGCGATACGCTTGA");

    
    

    System.out.println("ATGCGATACGCTTGA is a " + isProtein(dna1,subsProtein)+ " protein content " + dna1.substring(subsProtein.get(0),(subsProtein.get(1)+3)));
    System.out.println("ATGCGATACGTGA is a " + isProtein(dna2,subsProtein)+ " protein content " + dna2.substring(subsProtein.get(2),(subsProtein.get(3)+3)));
    System.out.println("ATTAATATGTACTGA is a " + isProtein(dna3,subsProtein)+ " protein content " + dna3.substring(subsProtein.get(4),(subsProtein.get(5)+3)));
    //System.out.println(subsProtein);
    
  }

    public static boolean isProtein (String dna, ArrayList<Integer> subsProtein){
    // A method to find Protein. 
    Integer startATG = dna.indexOf("ATG");
    Integer lastTGA = dna.indexOf("TGA");
    //String protein = new String();

    // Pass by reference the 1st and 2nd condition.
    subsProtein.add(startATG);
    subsProtein.add(lastTGA);
    
    // Meets all conditions ?
    if ( startATG != -1 && lastTGA != -1 && (lastTGA - startATG) % 3 == 0){
      // A protein
      return true;
    }
    else {
       // Not a protein
       return false;
    }

  }
}