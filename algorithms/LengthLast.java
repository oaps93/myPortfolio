import java.util.*;

class LengthLast{
    public static void main(String[] args){
        String line = "      The           new  life         ";
        String[] lineArray = line.split(" ",0);
        String[] lineArray2 = new String[]{"hola","como","estas"};


        System.out.println(Arrays.toString(lineArray));
        System.out.println(Arrays.toString(lineArray2));

    }
}