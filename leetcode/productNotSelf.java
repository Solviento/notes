// Given an array nums of n integers where n > 1,  
// return an array output such that output[i] is
// equal to the product of all the elements of nums except nums[i].
class productNotSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] ret = new int[nums.length];
        int tmp = 1;
        // nums: [1, 3, 4] | [1, 0] | [2, 5, 6, 10]
        for(int i = 0; i < nums.length; i++){
            ret[i] = tmp;  
            tmp *= nums[i]; 
        }
        // ret: [1, 1, 3] | [1, 1] | [1, 2, 10, 60]
        tmp = 1;
        for(int j = nums.length-1; j > -1; j--){
            ret[j] *= tmp;
            tmp *= nums[j]; // this is important for second step
        }
        return ret;
    }
}