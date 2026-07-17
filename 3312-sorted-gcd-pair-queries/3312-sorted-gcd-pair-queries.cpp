class Solution {
public:
    vector<int> gcdValues(vector<int>& nums, vector<long long>& queries) {
        int mx=*max_element(nums.begin(),nums.end());
        vector<int> freq(mx+1);
        for(int& num:nums) ++freq[num];
         vector<long long> GCD(mx+1);
        for(int i=mx;i>=1;--i)
        {
            long long cnt=0;
            for(int j=i;j<=mx;j+=i)
                cnt+=freq[j];
            GCD[i]=cnt*(cnt-1)/2;
            for(int j=2*i;j<=mx;j+=i)
                GCD[i]-=GCD[j];
        }
        for(int i=1;i<=mx;++i)
        {
            GCD[i]+=GCD[i-1];
        }
        vector<int> ans(queries.size());
        for(int i=0;i<queries.size();++i)
        {
            ans[i]=upper_bound(GCD.begin(),GCD.end(),queries[i])-GCD.begin();
        }
        return ans;
    }
};