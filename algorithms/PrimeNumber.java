// A prime number is a number that is 
//divisible by only two numbers: 1 and itself. So, if any number is divisible by any other number, it is not a prime number.

public class PrimeNumber{

    public static int other = 4;

    public static boolean isPrime(int number){
        int numbers = number-1;
        if(number < 1) return false;
        while( numbers > 1){
            if(number % numbers == 0){
                return false;
            }
            numbers--;
        }
        return true;
    }


    public static void main(String[] args){

        System.out.println(PrimeNumber.isPrime(other));

    }
}