class ValidPalindrome {
    public static void main(String[] args){
        String s = new String("abbaba");
        System.out.println(isPalindrome(s));
    }




    public static boolean isPalindrome(String s) {
        String cleanString = s.toLowerCase();
       

        cleanString = cleanString.replaceAll("[^a-zA-Z0-9]", "");

        int len = cleanString.length();  // 4
        int i = 0; // 0 
        int j = len - i - 1; // 3 

        while (i<j){ 
            if(cleanString.charAt(i)!=cleanString.charAt(j)){
                return false;
            }
            i++; // 1 2
            j = j-1; // 2 0
        }
        return true;            
    }
}