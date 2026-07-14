class NumArray {
    int[] nums;
    public NumArray(int[] nums) {
        int s=0;
        for(int i=0;i<nums.length;i++)
        {
            nums[i]+=s;
            s=nums[i];
        }
        this.nums=nums;
    }
    
    public int sumRange(int left, int right) {
        if(left==0)
            return nums[right];
        else
            return nums[right]-nums[left-1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */