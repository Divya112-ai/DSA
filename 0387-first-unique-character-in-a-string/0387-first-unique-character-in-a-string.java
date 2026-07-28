class Solution {
    public int firstUniqChar(String s) {
        int[] freq=new int[26];
        char[] str=s.toCharArray();
        for(char ch:str)
        {
            freq[ch-'a']++;
        }
        for(int i=0;i<str.length;i++)
        {
            if(freq[str[i]-'a']==1)
            {
                return i;
            }
        }
        return -1;   
    }
}