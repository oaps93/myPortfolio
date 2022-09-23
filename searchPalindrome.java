public class searchPalindrome {

    public static void main(String[] args){

        String s = new String("aaaaaaaaaaaa");
        int palindromeCount = 0;
        int len = s.length();
        

        for(int i = 0; i < len; i++){
            for ( int j = i+1; j <= len; j++){
                if (isPalindrome(s.substring(i,j))){
                    palindromeCount++;                    
                }
            }
            
        } 

        System.out.println(palindromeCount);
        
    }

    
    private static boolean isPalindrome(String word) {
        int len = word.length();
        int i = 0;
        while(i<len){
        if(word.charAt((i))==word.charAt(len-1-i)){
            i++;
        }
        else return false;
        }
        return true;
    }
    
}
