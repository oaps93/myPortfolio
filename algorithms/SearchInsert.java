class SearchInsert{
    public static void main(String[] args){
        int[] nums = new int[]{1,3,5,6};

        System.out.println("-" + searchInsert(nums,-1));
   
        System.out.println("-" + searchInsert(new int[]{1,3,5,6},2));

        System.out.println("-" + searchInsert(nums,4));
        
    }

    public static int searchInsert(int[] nums, int target){
        int right = nums.length;
        int left = 0;

        while(left <= right){
            int mid = Math.floorDiv(right+left,2);
            //System.out.println(mid);
            if(nums[mid]< target){
                left = mid + 1;
            } else if ( nums[mid] > target){
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return left;

    }
}