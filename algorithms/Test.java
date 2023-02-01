import java.util.*;
class Test{

    public int[] array;

    public Test(int j){
        int[] filledArray = new int[j+1];
        for(int i = 0; i <= j; i++){
            filledArray[i] = i;
        }
        this.array = filledArray;
    }
       

    public static void main (String args[]){
        Test test = new Test(20);
        
        System.out.println(Arrays.toString(test.array));

        test.reverse();
        test.reverse();

        System.out.println(Arrays.toString(test.array));
    }

    public void reverse() {
        
        int length = this.array.length;
        int j = length - 1;
        for(int i = 0; i < j ; i++){
            int temp = this.array[i];
            this.array[i] = this.array[j];
            this.array[j] = temp;
            j--;
        }

    }
    
}