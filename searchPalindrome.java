public class searchPalindrome {

    public static void main(String[] args){

        String s = new String("aaa");
        int palindromeCount = 0;
        int len = s.length();
        palindromeCount += len;

              
        
        while(len > 1){
            System.out.println(s);
            if(isPalindrome(s.substring(0,len-1))&&len>2){
                palindromeCount++;
            }
            if(isPalindrome(s.substring(1,len))&&len>2){
                palindromeCount++;
            }
            if(isPalindrome(s)){
                palindromeCount++;
            }
            s = s.substring(1, len-1);
            len = s.length();
            //System.out.println(s);
            
            

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
