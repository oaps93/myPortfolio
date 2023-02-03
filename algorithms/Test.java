import java.util.*;
class Test{
       

    public static void main (String args[]){
        int[] products = new int[] {1,2,2,1}; 
        // b1 = 1  b2 = 2 i = 0     j 
        System.out.println(maxProductBag(products));
    }
    private static int maxProductBag (int[] products){

        int maxShoppingBag = 0;
        int b1 = -1;
        int b2 = -1;
        
        for(int i = 0; i < products.length; i++){
            for(int j = 0; j < products.length; j++){
                if(b1 == -1){
                    b1 = products[j];
                } else if (b2 == -1 && b1 != products[j]) {
                    b2 = products[j];
                } else if (b1 != -1 && b2 != -1){
                    if(b1 != products[j] && b2 != products[j]){
                        if((j-i) > maxShoppingBag) maxShoppingBag = (j-i);
                        b1 = b2;
                        b2 = -1;
                        i = j-1;
                    } 
                    if((j-i) > maxShoppingBag) maxShoppingBag = (j+1-i); 
                                  
                }
            
            
            }
        }
        return maxShoppingBag;
    }
    
}