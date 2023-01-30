class ValidPalindrome {
    public static void main(String[] args){
        String s = new String("amanaplanacanalpanama");
        System.out.println(isPalindrome(s));
    }




    public static boolean isPalindrome(String s) {
        String cleanString = s.toLowerCase();
       

        cleanString = cleanString.replaceAll("[^a-zA-Z0-9]", "");

        int len = cleanString.length();  // 6
        int j = len-1; // 5
        for (int i = 0 ; i < j; i++){ // 3<2
            
            if(cleanString.charAt(i)!=cleanString.charAt(j)){ // c!=c
                return false;
            }
            j--; // 6-2-2 = 2
            

        }
        return true;            
    }
}