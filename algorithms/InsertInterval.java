
import java.util.*;



public class InsertInterval{
    public static void main(String[] args){
        int[][] intervals = new int[][]{{2,3},{7,9}};
        int[] newInterval = new int[]{1,3};

        int[][] newIntervals = insert(intervals, newInterval);
        int lengthInt = intervals.length;

        // for(int i=0; i<lengthInt;i++){
        //     System.out.println(Arrays.toString(newIntervals[i]));
        // }


        

    }

    public static int[][] insert(int[][] intervals, int[] newInterval){
        List<int[]> intervalsList = new ArrayList<>(Arrays.asList(intervals));
        
        int sizeList = intervalsList.size();
        int x = 0;
        while(x < sizeList-1){
            int[] interval = intervalsList.get(x);
            int[] nextInterval = intervalsList.get(x+1);
            if(newInterval[0]>interval[1] && newInterval[1]<nextInterval[0]){
            intervalsList.add(x+1,newInterval);
            }
            else if(newInterval[0]>=interval[0] && newInterval[1]<nextInterval[0] && newInterval[1]>interval[1]){
                interval[1] = newInterval[1];
            }      
            x++;    
        }

        for(int i=0; i<intervalsList.size();i++){
            System.out.println(Arrays.toString(intervalsList.get(i)));
        }
        
        return intervals;

    }
}