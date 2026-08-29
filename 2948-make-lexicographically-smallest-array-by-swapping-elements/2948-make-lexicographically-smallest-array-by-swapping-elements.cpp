class Solution {
public:
    vector<int> lexicographicallySmallestArray(vector<int>& nums, int limit) {
        vector<int> coppy=nums;
        sort(coppy.rbegin(),coppy.rend());
        vector<vector<int>> a;
        a.push_back({coppy[0]});
        int group =0;
        unordered_map<int,int> b;
        for(int i=1;i<coppy.size();++i)
        {
            if(abs(coppy[i]-coppy[i-1])<=limit)
            {
                a[group].push_back(coppy[i]);
            }
            else
            {
                ++group;
                a.push_back({coppy[i]});
            }
            b[coppy[i]]=group;
        }
        for(int i=0;i<nums.size();++i)
        {
            nums[i]=a[b[nums[i]]].back();
            a[b[nums[i]]].pop_back();
        }
        return nums;
    }
};