class Solution {
public:
    vector<int> targetIndices(vector<int>& nums, int target) {
         int small=0;
        int equal=0;
        for(int i=0;i<nums.size();++i)
        {
            if(nums[i]<target)
            {
                ++small;
            }
            else if(nums[i]==target)
            {
                ++equal;
            }
           
        }
         vector<int> ans(equal);
            iota(ans.begin(),ans.end(),small);
            return ans;
    }
};