import java.util.ArrayList;

public class searchPalindrome {

    public static void main(String[] args){

        String s = new String("aabbaa");
        int palindromeCount = 0;
        int len = s.length();
        ArrayList<String> wordsToTest = new ArrayList<>();
        

        for(int i = 0; i < len; i++){
            for ( int j = i+1; j <= len; j++){
                wordsToTest.add(s.substring(i,j));
            }
        } 

        for(int i = 0; i < wordsToTest.size(); i++){
            if(isPalindrome(wordsToTest.get(i))) palindromeCount++;
        }

        System.out.println(palindromeCount);
        
    }

    
    private static boolean isPalindrome(String word) {
        int len = word.length(); // 6
        int i = 0; // 0
        int j = len-1; // 5
        while(i<j){ // 3<2
        if(word.charAt((i))==word.charAt(j)){ // b == b
            i++;
            j--;
        }
        else return false;
        }
        return true;
    }
    
}
