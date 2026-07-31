class Solution {
public:
    int minimumPushes(string word) {
         vector<int> freq(26,0);
        for(char& c:word)
        {
            ++freq[c-'a'];
        }
        sort(freq.rbegin(),freq.rend());
        int press=0;
        for(int i=0;i<26;i++)
        {
            press+=(i/8+1)*freq[i];
        }
        return press;
    }
};